package com.csf.basedata.sammgt.domain.audit.builder;

import com.csf.basedata.sammgt.domain.utils.AuditRuntimeException;
import com.csf.basedata.sammgt.domain.utils.ReflectionUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.StaticTextSqlNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * sql解析器
 *
 * @author michelle.min
 */
public class AbstractAuditSqlParser implements AuditSqlParser {
    protected Pattern pattern;
    /**
     * sql元数据{@link AuditSqlMetaObject}对应的匹配组索引
     */
    protected Map<String, Integer> groupIndexMap;

    public AbstractAuditSqlParser() {
    }

    public AbstractAuditSqlParser(Pattern pattern, Map<String, Integer> groupIndexMap) {
        this.pattern = Optional.of(pattern).get();
        if (MapUtils.isNotEmpty(groupIndexMap)) {
            this.groupIndexMap = groupIndexMap;
        } else {
            throw new AuditRuntimeException("Group index map is empty");
        }
    }

    @Override
    public AuditSqlMetaObject parse(String sql) {
        AuditSqlMetaObject metaObject = null;
        if (StringUtils.isNotBlank(sql)) {
            Matcher matcher = pattern.matcher(sql.replace("\n", " "));
            if (matcher.matches()) {
                metaObject = new AuditSqlMetaObject();
                metaObject.setSqlCommandType(SqlCommandType.UPDATE);
                String tableName;
                if (StringUtils.isBlank(tableName = StringUtils.trimToEmpty(matcher.group(groupIndexMap.get("tableName"))))) {
                    throw new AuditRuntimeException("Can't parse audit sql table name,input sql=" + sql);
                }
                metaObject.setTableName(tableName);
                Integer index;
                //Default value is empty
                metaObject.setTempTableName(StringUtils.EMPTY);
                if ((index = groupIndexMap.get("tempTableName")) != null) {
                    metaObject.setTempTableName(StringUtils.trimToEmpty(matcher.group(index)));
                }
                //Default value is empty
                metaObject.setWhereClause(StringUtils.EMPTY);
                if ((index = groupIndexMap.get("whereClause")) != null) {
                    metaObject.setWhereClause(StringUtils.trimToEmpty(matcher.group(index)));
                }
            } else {
                throw new AuditRuntimeException("Can't parse audit sql,input sql=" + sql);
            }
        }
        return metaObject;
    }

    @Override
    public AuditSqlMetaObject parse(SqlSource sqlSource, String sql) throws Exception {
        AuditSqlMetaObject metaObject = parse(sql);
        if (sqlSource instanceof DynamicSqlSource) {
            List<SqlNode> whereSqlNodeList = new ArrayList<>();
            DynamicSqlSource dynamicSqlSource = (DynamicSqlSource) sqlSource;
            MixedSqlNode sqlNode = ReflectionUtil.getFieldValue("rootSqlNode", dynamicSqlSource, MixedSqlNode.class);
            if (sqlNode != null) {
                String originalWhereClause = null;
                Object contentsValue = ReflectionUtil.getFieldValue("contents", sqlNode);
                List<SqlNode> sqlNodeList;
                if (contentsValue != null && CollectionUtils.isNotEmpty(sqlNodeList = (List<SqlNode>) contentsValue)) {
                    for (SqlNode s : sqlNodeList) {
                        if (StringUtils.isNotBlank(originalWhereClause)) {
                            whereSqlNodeList.add(s);
                        } else if (s instanceof StaticTextSqlNode) {
                            String text = ReflectionUtil.getFieldString("text", s);
                            int whereIndex;
                            if (StringUtils.isNotBlank(text) && (whereIndex = text.toUpperCase().lastIndexOf("WHERE")) != -1) {
                                originalWhereClause = text.substring(whereIndex, text.length());
                            }
                        }
                    }
                }
                if (StringUtils.isBlank(originalWhereClause)) {
                    throw new AuditRuntimeException("Audit parse sql error,sql={}" + sql);
                }
                metaObject.setOriginalWhereClause(originalWhereClause);
                metaObject.setWhereSqlNodes(whereSqlNodeList);
            }
        }
        return metaObject;
    }


}
