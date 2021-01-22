package com.csf.basedata.sammgt.domain.plugin;

import com.csf.basedata.sammgt.domain.utils.AuditThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;



/**
 * @author allen.jin
 * @date 2020/4/11
 */

@Slf4j
@Configuration
public class AuditObjectPluginCtxHolder {

    /**
     * 审计拦截器是否异步执行
     */
    @Value("${audit.async:false}")
    private boolean auditAsync;
    @Value("${audit.threadpool.corePoolSize:10}")
    private int corePoolSize;
    @Value("${audit.threadpool.maxPoolSize:1000}")
    private int maxPoolSize;
    @Value("${audit.threadpool.keepAliveTime:60}")
    private long keepAliveTime;
    @Value("${audit.threadpool.queueCapacity:100}")
    private int queueCapacity;
    @Value("${audit.before.execute.timeout:3}")
    private long beforeExecuteTimeout;

    /**
     * object audit插入mapper id
     */
    private String objectAuditInsertMapper = "com.csf.basedata.sammgt.domain.dao.samclient.ScmAuditMapper.bulkInsert";
    private String objectClerkInsertMapper = "com.csf.basedata.sammgt.domain.dao.samplatform.ClerkMapper.bulkInsert";


    @Bean("auditObjectPlugin")
    public AuditObjectPlugin createAuditPlugin() {
        log.info("Start create audit plugin...");
        AuditObjectPlugin auditObjectPlugin = new AuditObjectPlugin();
        Properties properties = new Properties();
        properties.put("objectAuditInsertMapper", objectAuditInsertMapper);
        properties.put("auditAsync", auditAsync);
        properties.put("beforeExecuteTimeout", beforeExecuteTimeout);
        //初始化线程池
        AuditThreadUtil.initial(corePoolSize, maxPoolSize, keepAliveTime, queueCapacity);
        auditObjectPlugin.setProperties(properties);
        log.info("Finish create audit plugin...>>>>>>>>>>>>");
        return auditObjectPlugin;
    }

    @Bean("clerkObjectPlugin")
    public ClerkObjectPlugin createClerkPlugin() {
        log.info("Start create clerk plugin...");
        ClerkObjectPlugin clerkObjectPlugin = new ClerkObjectPlugin();
        Properties properties = new Properties();
        properties.put("objectClerkInsertMapper", objectClerkInsertMapper);
        properties.put("auditAsync", auditAsync);
        properties.put("beforeExecuteTimeout", beforeExecuteTimeout);
        //初始化线程池
        AuditThreadUtil.initial(corePoolSize, maxPoolSize, keepAliveTime, queueCapacity);
        clerkObjectPlugin.setProperties(properties);
        log.info("Finish create Clerk plugin...>>>>>>>>>>>");
        return clerkObjectPlugin;
    }
}
