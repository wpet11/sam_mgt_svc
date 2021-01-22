package com.csf.basedata.sammgt.domain.audit.builder;


import org.apache.ibatis.mapping.SqlSource;

/**
 * sql解析器
 *
 * @author michelle.min
 */
public interface AuditSqlParser {
    AuditSqlMetaObject parse(String sql);

    AuditSqlMetaObject parse(SqlSource sqlSource, String sql) throws Exception;
}
