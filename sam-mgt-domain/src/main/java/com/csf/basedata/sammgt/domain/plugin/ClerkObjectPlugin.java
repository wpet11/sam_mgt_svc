package com.csf.basedata.sammgt.domain.plugin;

import com.csf.basedata.sammgt.domain.audit.builder.AuditMapper;
import com.csf.basedata.sammgt.domain.audit.builder.AuditMapperBuilder;
import com.csf.basedata.sammgt.domain.audit.executor.AbstractAuditExecutor;
import com.csf.basedata.sammgt.domain.audit.executor.AuditExecutor;
import com.csf.basedata.sammgt.domain.config.AuditOpenConfig;
import com.csf.basedata.sammgt.domain.utils.AuditExecutorThreadLocal;
import com.csf.basedata.sammgt.domain.utils.AuditObjectNotFoundException;
import com.csf.basedata.sammgt.domain.utils.AuditThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Slf4j
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
})
public class ClerkObjectPlugin implements Interceptor {
    private Properties properties;

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getFromProperty(String key) {
        return properties != null ? properties.getProperty(key) : null;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object returnObject;
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = invocation.getArgs()[1];
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Executor executor = (Executor) invocation.getTarget();
        AuditExecutor auditExecutor = null;
        AuditMapper auditMapper = new AuditMapper(mappedStatement, parameter);
        try {
            if (Boolean.TRUE.equals(AuditOpenConfig.auditOn)) {
                auditExecutor = createAuditExecutor(auditMapper);
            }
        } catch (Exception e) {
            log.error("Create audit executor error, mapper info:{}", auditMapper.toString(), e);
        }
        Future<?> future = null;
        if (auditExecutor != null) {
            try {
                final AuditExecutor auditExecutorFinal = auditExecutor;
                //异步启动新的事务查询
                future = AuditThreadUtil.submit(() -> auditExecutorFinal.beforeExecute(executor));
//                auditExecutorFinal.beforeExecute(executor);
            } catch (Exception e) {
                log.error("Before audit executor error, mapper info:{}", auditMapper.toString(), e);
            }
        }

        returnObject = invocation.proceed();

        if (future != null) {
            try {
                future.get(MapUtils.getInteger(properties, "beforeExecuteTimeout"), TimeUnit.MINUTES);
            } catch (Exception e) {
                // maybe  TimeoutException
                log.error("Before audit executor error, mapper info:{}", auditMapper.toString(), e);
            }
        }

        if (auditExecutor != null) {
            try {
                callAuditExecutor(auditExecutor, parameter, mappedStatement);
            } catch (Exception e) {
                log.error("Create audit executor error, mapper info:{}", auditMapper.toString(), e);
            }
        }
        return returnObject;
    }

    private void callAuditExecutor(AuditExecutor auditExecutor, Object parameter, MappedStatement mappedStatement) {
        auditExecutor.setAsync(Boolean.getBoolean(getFromProperty("auditAsync")));
        //将插入返回结果设置为beforeResult，批量插入参数默认为list，从list中获取插入操作返回id
        if (SqlCommandType.INSERT.equals(mappedStatement.getSqlCommandType())) {
            if (parameter instanceof Map) {
                parameter = ((Map) parameter).get("list");
            }
            if (parameter instanceof Collection) {
                auditExecutor.setBeforeResult(new ArrayList<>((Collection<Object>) parameter));
            } else if (parameter != null) {
                auditExecutor.setBeforeResult(Collections.singletonList(parameter));
            }
        }
        if (AuditExecutorThreadLocal.get() != null) {
            //如果spring Transactional事务开始前放入审计执行器集合，则在集合中添加审计执行器
            AuditExecutorThreadLocal.add(auditExecutor);
        } else {
            auditExecutor.execute();
        }
    }

    private AuditExecutor createAuditExecutor(AuditMapper auditMapper) throws Exception {
        AuditExecutor auditExecutor = null;
        try {
            AuditMapperBuilder builder = new AuditMapperBuilder(auditMapper, getFromProperty("objectClerkInsertMapper"));
            switch (auditMapper.getMappedStatement().getSqlCommandType()) {
                case UPDATE:
                    auditExecutor = new UpdateAuditExecutor(builder);
                    break;
                case INSERT:
                    auditExecutor = new InsertAuditExecutor(builder);
                    break;
                default:
                    break;
            }
        } catch (AuditObjectNotFoundException e) {
            log.debug("Not fount audit object annotation", e);
        }
        return auditExecutor;
    }
    public static class InsertAuditExecutor extends AbstractAuditExecutor {

        public InsertAuditExecutor(AuditMapperBuilder builder) {
            super(builder);
        }

        @Override
        public List beforeExecute(Executor delegate) {
            return null;
        }
    }

    public static class UpdateAuditExecutor extends AbstractAuditExecutor {

        public UpdateAuditExecutor(AuditMapperBuilder builder) {
            super(builder);
        }
    }
}
