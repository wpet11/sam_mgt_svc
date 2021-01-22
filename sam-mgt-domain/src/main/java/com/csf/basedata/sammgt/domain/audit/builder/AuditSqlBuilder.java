package com.csf.basedata.sammgt.domain.audit.builder;


import com.csf.basedata.sammgt.domain.annotations.AuditLeftJoin;
import com.csf.basedata.sammgt.domain.annotations.AuditObjectAnn;
import com.csf.basedata.sammgt.domain.utils.AuditRuntimeException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.xmltags.*;

import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 审计sql构建
 *
 * @author michelle.min
 */
public class AuditSqlBuilder {
    private AuditSqlMetaObject auditSqlMetaObject;
    private AuditObjectAnn auditObjectAnn;
    private boolean isCamel;
    private MappedStatement mappedStatement;
    private BoundSql boundSql;
    private final static String UPDATE_REGEX = "(update|UPDATE)(\\s+)([a-zA-Z_0-9\\.]+)(\\s+[a-zA-Z_0-9]*){0,1}(\\s+)(set|SET)(.+)(where|WHERE)(\\s+)(.+)";
    private static final String INSERT_REGEX = "(INSERT|insert)(\\s+)(INTO|into)(\\s+)([a-zA-Z_0-9\\.]+)(\\s*)(.+)";

    public AuditSqlBuilder(AuditObjectAnn auditObjectAnn, boolean isCamel, MappedStatement mappedStatement, BoundSql boundSql) {
        this.auditObjectAnn = Optional.of(auditObjectAnn).get();
        this.isCamel = isCamel;
        this.mappedStatement = mappedStatement;
        this.boundSql = boundSql;
        try {
            setSqlMetaObject();
        } catch (Exception e) {
            throw new AuditRuntimeException("Audit sql parse error", e);
        }
    }

    private String appendLeftJoinSql(StringBuilder sqlSb) {
        Class auditObjectType = auditObjectAnn.type();
        StringBuilder leftJoinSb = new StringBuilder();
        Field[] fields = auditObjectType.getDeclaredFields();
        //Create temple table name
        String primaryTempTableName = getPrimaryTempTableName(fields);
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            AuditLeftJoin ann;
            String name = field.getName();
            String columnName = isCamel ? field.getName().replaceAll("[A-Z]", "_$0").toLowerCase() : field.getName();
            if ((ann = field.getAnnotation(AuditLeftJoin.class)) != null) {
                //Create left join temporary table name
                String leftJoinTempTableName = primaryTempTableName + i;
                String[] columns = ann.columnName().split(",");
                if (columns.length > 1) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("CONCAT(");
                    for (int k = 0; k < columns.length; k++) {
                        sb.append(leftJoinTempTableName).append(".").append(columns[k].trim());
                        if (k < columns.length - 1) {
                            sb.append(",");
                            sb.append("','");
                            sb.append(",");
                        }
                    }
                    sb.append(")");
                    sqlSb.append(sb.toString());
                } else {
                    sqlSb.append(leftJoinTempTableName).append(".").append(ann.columnName());
                }
                leftJoinSb.append(" left join ").append(ann.tableName()).append(" ").append(leftJoinTempTableName).append(" on ");
                String[] primaryColumns = ann.conditionPrimary().split(",");
                String[] relatedColumns = ann.conditionRelated().split(",");
                for (int j = 0; j < primaryColumns.length; j++) {
                    leftJoinSb.append(primaryTempTableName).append(".").append(primaryColumns[j]).append("=").append(leftJoinTempTableName).append(".").append(relatedColumns[j]);
                    if (j < primaryColumns.length - 1) {
                        leftJoinSb.append(" and ");
                    }
                }
                if (StringUtils.isNotBlank(ann.conditionWhere())) {
                    //Replace $temp to leftJoinTempTableName
                    leftJoinSb.append(" and ").append(ann.conditionWhere().replace("$temp", leftJoinTempTableName));
                }
            } else {
                if (StringUtils.isNotBlank(primaryTempTableName)) {
                    sqlSb.append(primaryTempTableName).append(".");
                }
                sqlSb.append(columnName);
            }
            sqlSb.append(" as ").append(name);
            if (i < fields.length - 1) {
                sqlSb.append(",");
            }
        }
        sqlSb.append(" from ").append(this.auditSqlMetaObject.getTableName()).append(" ").append(primaryTempTableName).append(leftJoinSb);
        return primaryTempTableName;
    }

    /**
     * 如果存在left join，创建临时表名
     */
    private String getPrimaryTempTableName(Field[] fields) {
        String primaryTempTableName = this.auditSqlMetaObject.getTempTableName();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            AuditLeftJoin ann;
            if ((ann = field.getAnnotation(AuditLeftJoin.class)) != null) {
                primaryTempTableName = "tmp";
                if (StringUtils.isNotBlank(this.auditSqlMetaObject.getTempTableName())) {
                    primaryTempTableName = primaryTempTableName + "_" + this.auditSqlMetaObject.getTempTableName();
                }
                break;
            }
        }
        return primaryTempTableName;
    }

    public SqlSource buildBeforeSqlSource() {
        StringBuilder sqlSb = new StringBuilder("select ");
        String tempTableName = appendLeftJoinSql(sqlSb);
        boolean isDynamicSql = CollectionUtils.isNotEmpty(this.auditSqlMetaObject.getWhereSqlNodes()) && StringUtils.isNoneBlank(this.auditSqlMetaObject.getOriginalWhereClause());
        if (StringUtils.isNotBlank(tempTableName) && !tempTableName.equals(this.auditSqlMetaObject.getTempTableName())) {
            sqlSb.append(" where ");
            sqlSb.append(tempTableName);
            sqlSb.append(".");
            //Create subquery clause, if there is AuditLeftJoin
            sqlSb.append("id in (select id from ")
                    .append(this.auditSqlMetaObject.getTableName())
                    .append(this.auditSqlMetaObject.getTempTableName())
                    .append(" ");
            if (isDynamicSql) {
                sqlSb.append(this.auditSqlMetaObject.getOriginalWhereClause());
                if (CollectionUtils.isNotEmpty(this.auditSqlMetaObject.getWhereSqlNodes())) {
                    this.auditSqlMetaObject.getWhereSqlNodes().add(new StaticTextSqlNode(" )"));
                }
            } else {
                sqlSb.append(" where ");
                sqlSb.append(this.auditSqlMetaObject.getWhereClause()).append(" )");
            }
        } else {
            if (isDynamicSql) {
                sqlSb.append(" ").append(this.auditSqlMetaObject.getOriginalWhereClause());
            } else {
                sqlSb.append(" where ");
                sqlSb.append(this.auditSqlMetaObject.getWhereClause());
            }
        }
        SqlSource sqlSource;
        if (isDynamicSql) {
            List<SqlNode> sqlNodeList = new ArrayList<>();
            sqlNodeList.add(new StaticTextSqlNode(sqlSb.toString()));
            sqlNodeList.addAll(this.auditSqlMetaObject.getWhereSqlNodes());
            sqlSource = new DynamicSqlSource(this.mappedStatement.getConfiguration(), new MixedSqlNode(sqlNodeList));
        } else {
            //根据where子句参数占位符获取参数mapping
            List<ParameterMapping> parameterMappingList = new ArrayList<>();
            List<ParameterMapping> auditParameterMappingList = this.boundSql.getParameterMappings();
            String whereClause = this.auditSqlMetaObject.getWhereClause();
            int parameterCount = StringUtils.isNoneBlank(whereClause) ? (int) whereClause.chars().filter(ch -> ch == '?').count() : 0;
            if (parameterCount > 0 && auditParameterMappingList.size() > parameterCount) {
                parameterMappingList = auditParameterMappingList.subList(auditParameterMappingList.size() - parameterCount, auditParameterMappingList.size());
            }
            sqlSource = new StaticSqlSource(this.mappedStatement.getConfiguration(), sqlSb.toString(), parameterMappingList);
        }
        return sqlSource;
    }

    public SqlSource buildAfterSqlSource() {
        StringBuilder sqlSb = new StringBuilder("select ");
        String tempTableName = appendLeftJoinSql(sqlSb);
        sqlSb.append(" where ");
        if (StringUtils.isNotBlank(tempTableName)) {
            sqlSb.append(tempTableName);
            sqlSb.append(".");
        }
        //修改后结果查询sql使用id in条件
        sqlSb.append("id in ");
        List<SqlNode> sqlNodeList = new ArrayList<>();
        sqlNodeList.add(new StaticTextSqlNode(sqlSb.toString()));
        sqlNodeList.add(new ForEachSqlNode(this.mappedStatement.getConfiguration(),
                new MixedSqlNode(Collections.singletonList(new StaticTextSqlNode(" #{item} "))),
                "list", "index", "item", "(", ")", ","));
        return new DynamicSqlSource(this.mappedStatement.getConfiguration(), new MixedSqlNode(sqlNodeList));
    }

    /**
     * 解析被审计插件拦截sql
     */
    private void setSqlMetaObject() throws Exception {
        AuditSqlParser auditSqlParser = null;
        switch (this.mappedStatement.getSqlCommandType()) {
            case UPDATE:
                Map<String, Integer> groupIndexMap = new HashMap<>(3);
                groupIndexMap.put("tableName", 3);
                groupIndexMap.put("tempTableName", 4);
                groupIndexMap.put("whereClause", 10);
                auditSqlParser = new AbstractAuditSqlParser(Pattern.compile(UPDATE_REGEX), groupIndexMap);
                this.auditSqlMetaObject = auditSqlParser.parse(this.mappedStatement.getSqlSource(), this.boundSql.getSql());
                break;
            case INSERT:
                auditSqlParser = new AbstractAuditSqlParser(Pattern.compile(INSERT_REGEX), Collections.singletonMap("tableName", 5));
                this.auditSqlMetaObject = auditSqlParser.parse(this.boundSql.getSql());
                break;
            default:
                break;
        }
    }
}
