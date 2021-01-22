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


/**
 * 审计日志插件
 *
 * @author michelle.min
 */
@Slf4j
@Intercepts({
         //拦截Executor接口中，参数类型为MappedStatement，Object的update方法
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
})
public class AuditObjectPlugin implements Interceptor {
    private Properties properties;

    //把代理对象property通过setProperties方法注入给拦截器
    //当初始化当前的Interceptor时就会执行
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
        //对应的xml文件中的一条sql语句
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        //执行参数
        Object parameter = invocation.getArgs()[1];
        //获取sql语句
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Executor executor = (Executor) invocation.getTarget();
        AuditExecutor auditExecutor = null;
        //组装条件
        //自定义的审计操作mapper，包含ResultHandler返回结果处理器
        AuditMapper auditMapper = new AuditMapper(mappedStatement, parameter);
        try {
            //读取配置文件，判断是否开启日志审计
            if (Boolean.TRUE.equals(AuditOpenConfig.auditOn)) {
                //是新增或编辑操作则构建AbstractAuditExecutor对象，不是则返回为空
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

        if (future != null){
            try {
                future.get(MapUtils.getInteger(properties, "beforeExecuteTimeout"), TimeUnit.MINUTES);
            }catch (Exception e){
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
            //被审计插件拦截的mapper
            //读取对应mapper的xml文件路径
            AuditMapperBuilder builder = new AuditMapperBuilder(auditMapper, getFromProperty("objectAuditInsertMapper"));
            //判断是否是新增或修改操作
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
