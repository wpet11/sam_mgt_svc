package com.csf.basedata.sammgt.domain.config;

import com.csf.basedata.sammgt.domain.plugin.ClerkObjectPlugin;
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
@MapperScan(basePackages = {"com.csf.basedata.sammgt.domain.dao.samplatform"},
        sqlSessionFactoryRef = "samPlatformSqlSessionFactory",
        sqlSessionTemplateRef = "samPlatformSqlSessionTemplate")
@Slf4j
public class SamPlatformDbConfig {

    @Resource(name = "samPlatformDataSource")
    private DataSource samPlatformDataSource;

    /**
     * 是否开启审计拦截器
     */
    @Value("${audit.open:false}")
    private boolean auditOpen;
    @Resource(name = "clerkObjectPlugin")
    private ClerkObjectPlugin clerkObjectPlugin;

    @Bean("samPlatformSqlSessionFactory")
    public SqlSessionFactory samPlatformSqlSessionFactory() {
        SqlSessionFactory sessionFactory = null;
        try {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(samPlatformDataSource);
            //配置审计插件
            if (auditOpen) {
                bean.setPlugins(clerkObjectPlugin);
            }
            bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                    .getResources("classpath*:com/csf/basedata/sammgt/domain/dao/samplatform/*.xml"));
            sessionFactory = bean.getObject();
            sessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
        } catch (Exception e) {
            log.error("Create SamPlatfromSqlSessionFactory Exception.", e);
        }
        return sessionFactory;
    }

    @Bean("samPlatformSqlSessionTemplate")
    SqlSessionTemplate samPlatformSqlSessionTemplate() {
        return new SqlSessionTemplate(samPlatformSqlSessionFactory());
    }
}
