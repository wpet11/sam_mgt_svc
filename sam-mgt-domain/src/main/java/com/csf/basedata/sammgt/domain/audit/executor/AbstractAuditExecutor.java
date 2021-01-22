package com.csf.basedata.sammgt.domain.audit.executor;

import com.csf.basedata.sammgt.domain.audit.builder.AuditMapper;
import com.csf.basedata.sammgt.domain.audit.builder.AuditMapperBuilder;
import com.csf.basedata.sammgt.domain.entity.samclient.ScmAudit;
import com.csf.basedata.sammgt.domain.utils.AuditRuntimeException;
import com.csf.basedata.sammgt.domain.utils.AuditSqlType;
import com.csf.basedata.sammgt.domain.utils.AuditThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.executor.CachingExecutor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.SimpleExecutor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.transaction.SpringManagedTransaction;

import java.sql.SQLException;
import java.util.*;


/**
 * @author michelle.min
 */
@Slf4j
public class AbstractAuditExecutor implements AuditExecutor {
    protected AuditMapperBuilder builder;
    private boolean async;

    public AbstractAuditExecutor(AuditMapperBuilder builder) {
        this.builder = Optional.of(builder).get();
    }

    @Override
    public <E> List<E> query(MappedStatement mappedStatement, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, boolean commit, Executor delegate) throws SQLException {
        List<E> result;
        try {
            if (delegate == null) {
                delegate = createDelegate(mappedStatement);
            }
            result = delegate.query(mappedStatement, parameter, rowBounds, resultHandler);
        } finally {
            closeDelegate(delegate, commit);
        }
        return result;
    }

    private void closeDelegate(Executor delegate, boolean commit) {
        if (delegate != null && commit) {
            try {
                delegate.commit(commit);
                delegate.getTransaction().close();
            } catch (SQLException e) {
                log.error("Commit executor error", e);
            }
        }
    }

    @Override
    public List<?> beforeExecute(Executor delegate) {
        List<Object> result = null;
        AuditMapper auditMapper = null;
        try {
            //修改前查询
            auditMapper = builder.build(AuditSqlType.BEFORE);
            result = query(auditMapper.getMappedStatement(), auditMapper.getParameter(), auditMapper.getRowBounds(), auditMapper.getResultHandler(), true, null);
            log.info("Audit before result, mapper id={}, size={}", auditMapper.getMappedStatement().getId(), result != null ? result.size() : 0);
            builder.setBeforeResult(result);
        } catch (Exception e) {
            log.error("Audit before execute error,mapper info:{},intercepted mapper info:{}", auditMapper != null ? auditMapper.toString() : null, builder.getInterceptedMapper().toString(), e);
        }
        return result;
    }

    @Override
    public List<?> afterExecute() {
        List<Object> result = new ArrayList<>();
        AuditMapper auditMapper = null;
        try {
            if (CollectionUtils.isNotEmpty(builder.getBeforeResult())) {
                auditMapper = builder.build(AuditSqlType.AFTER);
                boolean isExecute = false;
                if (auditMapper.getParameter() instanceof Map) {
                    String key = "list";
                    Object parameter = ((Map) auditMapper.getParameter()).get(key);
                    if (parameter instanceof Collection) {
                        Collection parameterColl = (Collection) parameter;
                        int size = parameterColl.size();
                        int limit = 1000;
                        //in查询分批执行，兼容oracle in查询最大限制1000条
                        if (isExecute = size > limit) {
                            List<Object> parameterList = new ArrayList<>();
                            int i = 0;
                            for (Object v : parameterColl) {
                                i++;
                                parameterList.add(v);
                                if (parameterList.size() == limit || i == size) {
                                    List<Object> resultList = query(auditMapper.getMappedStatement(), Collections.singletonMap(key, parameterList), auditMapper.getRowBounds(), auditMapper.getResultHandler(), true, null);
                                    if (CollectionUtils.isNotEmpty(resultList)) {
                                        result.addAll(resultList);
                                    }
                                    parameterList.clear();
                                }
                            }
                        }
                    }
                }
                if (!isExecute) {
                    result = query(auditMapper.getMappedStatement(), auditMapper.getParameter(), auditMapper.getRowBounds(), auditMapper.getResultHandler(), true, null);
                }
                log.info("Audit after result, mapper id={}, size={}", auditMapper.getMappedStatement().getId(), result != null ? result.size() : 0);
                builder.setAfterResult(result);
            }
        } catch (Exception e) {
            log.error("Audit after execute error,mapper info:{},intercepted mapper info:{}", auditMapper != null ? auditMapper.toString() : null, builder.getInterceptedMapper().toString(), e);
        }
        return result;
    }

    @Override
    public void execute() {
        if (async) {
            AuditThreadUtil.submit(() -> {
                afterExecute();
                insert();
                return null;
            });
        } else {
            afterExecute();
            insert();
        }
    }

    @Override
    public int insert() {
        int result = 0;
        AuditMapper auditMapper = null;
        try {
            if (CollectionUtils.isNotEmpty(builder.getAfterResult())) {
                auditMapper = builder.build(AuditSqlType.INSERT);
                MappedStatement mappedStatement = auditMapper.getMappedStatement();
                int size = auditMapper.getParameter() != null ? (auditMapper.getParameter() instanceof Collection ? ((Collection) auditMapper.getParameter()).size() : 1) : 0;
                log.info("Audit insert result, mapper id={}, size={}", auditMapper.getMappedStatement().getId(), size);
                update(mappedStatement, auditMapper.getParameter(), true, null);
            }
        } catch (Exception e) {
            log.error("Audit insert error,mapper info:{},intercepted mapper info:{}", auditMapper != null ? auditMapper.toString() : null, builder.getInterceptedMapper().toString(), e);
        }
        return result;
    }

    @Override
    public int update(MappedStatement mappedStatement, Object parameter, boolean commit, Executor delegate) throws SQLException {
        int result = 0;
        try {
            if (delegate == null) {
                delegate = createDelegate(mappedStatement);
            }
            result = delegate.update(mappedStatement, parameter);
            if (result <= 0) {
                throw new AuditRuntimeException("Audit insert fail!");
            }
        } finally {
            closeDelegate(delegate, commit);
        }
        return result;
    }

    @Override
    public void setBeforeResult(List<Object> beforeResult) {
        builder.setBeforeResult(beforeResult);
    }

    @Override
    public void setAsync(boolean async) {
        this.async = async;
    }

    @Override
    public AuditMapperBuilder getBuilder() {
        return builder;
    }

    private static Executor createDelegate(MappedStatement mappedStatement) {
        return new CachingExecutor(new SimpleExecutor(mappedStatement.getConfiguration(), new SpringManagedTransaction(mappedStatement.getConfiguration().getEnvironment().getDataSource())));
    }

    /**
     * 批量执行，将多个执行器afterExecute结果合并，使用insert批量插入到审计对象表中
     */
    public static void executeBatch(List<AuditExecutor> executorList) {
        if (CollectionUtils.isNotEmpty(executorList)) {
            for (AuditExecutor executor : executorList) {
                executor.afterExecute();
            }
            AuditMapper auditMapper = null;
            List<ScmAudit> parameterList = new ArrayList<>();
            AuditExecutor auditExecutor = null;
            for (AuditExecutor executor : executorList) {
                auditExecutor = executor;
                AuditMapperBuilder builder = executor.getBuilder();
                try {
                    auditMapper = builder.build(AuditSqlType.INSERT);
                    if (auditMapper.getParameter() != null) {
                        Map<String, List<ScmAudit>> parameterMap = (Map<String, List<ScmAudit>>) auditMapper.getParameter();
                        List<ScmAudit> list;
                        if ((list = parameterMap.get("list")) != null) {
                            parameterList.addAll(list);
                        }
                    }
                } catch (Exception e) {
                    log.error("Audit batch execute error,mapper info:{},intercepted mapper info:{}", auditMapper != null ? auditMapper.toString() : null,
                            builder != null ? builder.getInterceptedMapper().toString() : null, e);
                }
            }
            if (auditMapper != null) {
                try {
                    log.info("Audit batch insert, mapper id={}, size={}", auditMapper.getMappedStatement().getId(), parameterList.size());
                    auditExecutor.update(auditMapper.getMappedStatement(), Collections.singletonMap("list", parameterList), true, null);
                } catch (Exception e) {
                    log.error("Audit batch execute error,mapper info:{},intercepted mapper info:{}", auditMapper.toString(), e);
                }
            }
        }
    }

}
