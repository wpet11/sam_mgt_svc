package com.csf.basedata.sammgt.domain.config;

import com.csf.basedata.sammgt.domain.plugin.AuditObjectPlugin;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;


/**
 * @author michelle.min
 */
@ConditionalOnProperty(prefix = "platform.ds", name = "enable", havingValue = "true")
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.csf.basedata.sammgt.domain.dao.samclient"},
        sqlSessionFactoryRef = "samClientSqlSessionFactory",
        sqlSessionTemplateRef = "samClientSqlSessionTemplate")
@Slf4j
public class SamClientDbConfig {
    /**
     * 是否开启审计拦截器
     */
    @Value("${audit.open:false}")
    private boolean auditOpen;

    @Resource
    private AuditObjectPlugin auditObjectPlugin;

    @Resource(name = "samClientDataSource")
    private DataSource samClientDataSource;

    @Bean
    public SqlSessionFactory samClientSqlSessionFactory() {
        SqlSessionFactory sessionFactory = null;
        try {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
           bean.setDataSource(samClientDataSource);
           //配置审计插件
            if (auditOpen) {
               bean.setPlugins(auditObjectPlugin);
            }
            bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                    .getResources("classpath*:com/csf/basedata/sammgt/domain/dao/samclient/*.xml"));
            sessionFactory = bean.getObject();
            sessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
        } catch (Exception e) {
            log.error("", e);
        }
        return sessionFactory;
    }

    @Bean
    public SqlSessionTemplate samClientSqlSessionTemplate() {
        return new SqlSessionTemplate(samClientSqlSessionFactory());
    }

}
