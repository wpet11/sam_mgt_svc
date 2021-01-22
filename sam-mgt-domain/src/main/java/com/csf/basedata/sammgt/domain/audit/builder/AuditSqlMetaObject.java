package com.csf.basedata.sammgt.domain.audit.builder;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.scripting.xmltags.SqlNode;

import java.util.List;


/**
 * sql元数据
 *
 * @author michelle.min
 */
@Getter
@Setter
public class AuditSqlMetaObject {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 临时表名
     */
    private String tempTableName;
    /**
     * where子句
     */
    private String whereClause;
    /**
     * sql类型
     */
    private SqlCommandType sqlCommandType;

    private String originalWhereClause;

    private List<SqlNode> whereSqlNodes;
}
