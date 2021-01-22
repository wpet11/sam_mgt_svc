package com.csf.basedata.sammgt.domain.audit.builder;

import com.csf.basedata.sammgt.common.enums.AuditOperateTypeEnum;
import com.csf.basedata.sammgt.common.enums.DBStatusEnum;
import com.csf.basedata.sammgt.domain.annotations.AuditLeftJoin;
import com.csf.basedata.sammgt.domain.annotations.AuditObjectAnn;
import com.csf.basedata.sammgt.domain.entity.samclient.ScmAudit;
import com.csf.basedata.sammgt.domain.utils.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.util.*;


/**
 * @author michelle.min
 */
@Getter
public class AuditMapperBuilder {
    /**
     * 被审计插件拦截的mapper
     */
    private AuditMapper interceptedMapper;
    private MappedStatement auditMappedStatement;
    private Object auditParameter;
    private BoundSql auditBoundSql;
    private AuditObjectAnn auditObjectAnn;
    private AuditMapper target;
    /**
     * {@link AuditSqlType}before执行返回结果
     */
    @Setter
    private List<Object> beforeResult;
    /**
     * {@link AuditSqlType}after执行返回结果
     */
    @Setter
    private List<Object> afterResult;
    /**
     * 插入审计对象mapper id{@link com.csf.basedata.sammgt.domain.config.SamClientDbConfig}
     */
    private String objectAuditInsertMapper;
    private AuditSqlType auditSqlType;

    public AuditMapperBuilder(AuditMapper interceptedMapper, String objectAuditInsertMapper) throws Exception {
        this.interceptedMapper = Optional.of(interceptedMapper).get();
        this.auditMappedStatement = Optional.of(interceptedMapper.getMappedStatement()).get();
        this.auditParameter = Optional.of(interceptedMapper.getParameter()).get();
        this.auditBoundSql = Optional.of(interceptedMapper.getMappedStatement().getBoundSql(this.auditParameter)).get();
        this.objectAuditInsertMapper = objectAuditInsertMapper;
        setAnnotation();
    }

    public void setAnnotation() throws Exception {
        String mapperId = auditMappedStatement.getId();
        int methodNameIndex = mapperId.lastIndexOf(".");
        //获取mapper interface及method
        String mapperInterfaceName = mapperId.substring(0, methodNameIndex);
        String mapperMethodName = mapperId.substring(methodNameIndex - 1, mapperId.length());
        Class mapperInterface = Class.forName(mapperInterfaceName);
        this.auditObjectAnn = (AuditObjectAnn) mapperInterface.getAnnotation(AuditObjectAnn.class);
        //如果mapper interface没有使用审计对象注解，不进行审计操作
        if (auditObjectAnn == null) {
            throw new AuditObjectNotFoundException("Audit object not found!");
        }
        Class auditObjectType = auditObjectAnn.type();
        Field[] fields = auditObjectType.getDeclaredFields();
    }

    private AuditMapperBuilder mappedStatement() {
        MappedStatement mappedStatement;
        String id = buildId();
        if (this.auditSqlType.equals(AuditSqlType.INSERT)) {
            //插入审计对象mapper已定义
            mappedStatement = this.auditMappedStatement.getConfiguration().getMappedStatement(id);
        } else {
            //如果mapper已被构建过，直接返回
            if (this.auditMappedStatement.getConfiguration().hasStatement(id)) {
                mappedStatement = this.auditMappedStatement.getConfiguration().getMappedStatement(id);
            } else {
                List<ResultMap> resultMapList = new ArrayList<>();
                //返回结果类型resultMap
                ResultMap resultMap = new ResultMap.Builder(this.auditMappedStatement.getConfiguration(), id + "-Inline", auditObjectAnn.type(), new ArrayList<>())
                        .build();
                resultMapList.add(resultMap);
                mappedStatement = new MappedStatement.Builder(this.auditMappedStatement.getConfiguration(), id, buildSqlSource(), buildSqlCommandType())
                        .resultMaps(resultMapList)
                        .build();
                this.auditMappedStatement.getConfiguration().addMappedStatement(mappedStatement);
            }
        }
        this.target.setMappedStatement(mappedStatement);
        return this;
    }

    private SqlCommandType buildSqlCommandType() {
        switch (this.auditSqlType) {
            case AFTER:
            case BEFORE:
                return SqlCommandType.SELECT;
            default:
                return null;
        }
    }

    /**
     * 构建审计sql
     */
    private SqlSource buildSqlSource() {
        AuditSqlBuilder sqlBuilder = new AuditSqlBuilder(auditObjectAnn, this.auditMappedStatement.getConfiguration().isMapUnderscoreToCamelCase(), this.auditMappedStatement, this.auditBoundSql);
        SqlSource sqlSource = null;
        switch (this.auditSqlType) {
            case BEFORE:
                sqlSource = sqlBuilder.buildBeforeSqlSource();
                break;
            case AFTER:
                sqlSource = sqlBuilder.buildAfterSqlSource();
                break;
            default:
                break;
        }
        return sqlSource;
    }

    /**
     * 构建审计mapper id
     */
    private String buildId() {
        switch (this.auditSqlType) {
            case INSERT:
                return objectAuditInsertMapper;
            case AFTER:
                return this.auditMappedStatement.getId() + ".afterAudit";
            case BEFORE:
                return this.auditMappedStatement.getId() + ".beforeAudit";
            default:
                throw new AuditRuntimeException("Audit mapper id is null!");
        }
    }

    /**
     * 构建审计sql参数
     */
    private AuditMapperBuilder parameter() throws Exception {
        switch (this.auditSqlType) {
            case INSERT:
                this.target.setParameter(Collections.singletonMap("list", convertToObjectAudit()));
                break;
            case AFTER:
                this.target.setParameter(Collections.singletonMap("list", convertToObjectIdList()));
                break;
            case BEFORE:
                setBeforeParameter();
                break;
            default:
                break;
        }
        return this;
    }

    private void setBeforeParameter() {
        this.target.setParameter(this.auditParameter);
        List<ParameterMapping> parameterMappingList = this.target.getMappedStatement().getBoundSql(this.target.getParameter()).getParameterMappings();
        if (CollectionUtils.isNotEmpty(parameterMappingList)) {
            Map<String, Object> queryParameterMap = new HashMap<>();
            //如果参数是map类型，根据参数mapping获取参数
            Map<String, Object> parameterMap = this.auditParameter instanceof Map ? (Map) this.auditParameter : new HashMap<>();
            for (ParameterMapping parameterMapping : parameterMappingList) {
                String key = parameterMapping.getProperty();
                if (parameterMap.containsKey(key)) {
                    queryParameterMap.put(key, parameterMap.get(key));
                } else if (this.auditBoundSql.getAdditionalParameter(key) != null) {
                    queryParameterMap.put(key, this.auditBoundSql.getAdditionalParameter(key));
                }
            }
            if (parameterMap.containsKey("list")) {
                queryParameterMap.put("list", parameterMap.get("list"));
            }
            if (MapUtils.isNotEmpty(queryParameterMap)) {
                this.target.setParameter(queryParameterMap);
            }
        }
    }

    /**
     * 获取id参数集合
     */
    private Set<Object> convertToObjectIdList() throws NoSuchFieldException, IllegalAccessException {
        Set<Object> parameterList = new HashSet<>();
        if (CollectionUtils.isNotEmpty(beforeResult)) {
            for (Object object : beforeResult) {
                Field field = object.getClass().getDeclaredField("id");
                field.setAccessible(true);
                Object id = field.get(object);
                if (id != null) {
                    parameterList.add(id);
                }
            }
        }
        return parameterList;
    }

    /**
     * 获取插入审计对象集合
     */
    private List<ScmAudit> convertToObjectAudit() throws Exception {
        ObjectMapper mapper = JacksonUtils.getObjectMapper();
        List<ScmAudit> scmAuditList = new ArrayList<>();
        Map<Long, Object> beforeMap = new HashMap<>();
        //insert操作修改前结果默认为null
        if (!SqlCommandType.INSERT.equals(this.auditMappedStatement.getSqlCommandType())) {
            beforeMap = convertResultToMap(beforeResult);
        }
        Map<Long, Object> afterMap = convertResultToMap(afterResult);
        for (Map.Entry<Long, Object> entity : afterMap.entrySet()) {
            String statusKey = "status";
            Object before = beforeMap.get(entity.getKey());
            Object after = entity.getValue();
            Integer beforeStatus = before != null ? ReflectionUtil.getFieldInteger(statusKey, before) : null;
            Integer afterStatus = ReflectionUtil.getFieldInteger(statusKey, after);
            boolean isDelete = DBStatusEnum.VALID.getValue().equals(beforeStatus)
                    && DBStatusEnum.INVALID.getValue().equals(afterStatus);
            ScmAudit scmAudit = new ScmAudit();
            scmAudit.setFieldName("table");
            scmAudit.setObjectId(entity.getKey());
            if (before != null) {
                scmAudit.setBeforeValue(mapper.writeValueAsString(before));
            }

            if (entity.getValue() != null) {
                scmAudit.setAfterValue(mapper.writeValueAsString(entity.getValue()));
            }
            //设置审计对象类型名称，如果没有审计对象类型名称，则使用审计对象类型simpleName
            scmAudit.setObjectType(StringUtils.isNoneBlank(auditObjectAnn.name()) ? auditObjectAnn.name() : auditObjectAnn.type().getSimpleName());
            switch (this.auditMappedStatement.getSqlCommandType()) {
                case INSERT:
                    scmAudit.setOperateType(AuditOperateTypeEnum.INSERT.getType());
                    scmAudit.setCreatedBy(ReflectionUtil.getFieldString("createdBy", after));
                    break;
                case UPDATE:
                    scmAudit.setOperateType(isDelete ? AuditOperateTypeEnum.DELETE.getType() : AuditOperateTypeEnum.UPDATE.getType());
                    scmAudit.setCreatedBy(ReflectionUtil.getFieldString("updatedBy", after));
                    break;
                default:
                    break;
            }
            scmAuditList.add(scmAudit);
        }
        return scmAuditList;
    }

    public static Map<Long, Object> convertResultToMap(List<Object> resultList) throws NoSuchFieldException, IllegalAccessException {
        if (CollectionUtils.isEmpty(resultList)) {
            return Collections.EMPTY_MAP;
        }

        Map<Long, Object> resultMap = new HashMap<>(resultList.size());
        Object first = resultList.get(0);
        Field[] fields = first.getClass().getDeclaredFields();
        //查找有AuditLeftJoin的字段名称
        List<String> list = new ArrayList<>(2);
        for (Field field : fields) {
            if (field.getAnnotation(AuditLeftJoin.class) != null) {
                list.add(field.getName());
            }
        }

        for (Object object : resultList) {
            Long id = ReflectionUtil.getFieldLong("id", object);
            if (id != null) {
                if (CollectionUtils.isEmpty(list)) {
                    resultMap.put(id, object);
                } else {
                    Object old = resultMap.get(id);
                    if (old != null) {
                        //将多条记录合并成一条记录
                        for (String name : list) {
                            Field oldField = old.getClass().getDeclaredField(name);
                            if (!oldField.getType().getSimpleName().equals("String")) {
                                continue;
                            }
                            Object oldValue = ReflectionUtil.getFieldValue(oldField, old);
                            Field newField = object.getClass().getDeclaredField(name);
                            Object newValue = ReflectionUtil.getFieldValue(newField, object);
                            String joinValue = ((String) oldValue) + ";" + ((String) newValue);
                            newField.setAccessible(true);
                            newField.set(object, joinValue);
                        }
                        resultMap.put(id, object);
                    } else {
                        resultMap.put(id, object);
                    }
                }
            }
        }
        return resultMap;
    }

    private AuditMapperBuilder resultHandler() {
        //默认为null
        return this;
    }

    private AuditMapperBuilder rowBounds() {
        //使用默认分页
        this.target.setRowBounds(RowBounds.DEFAULT);
        return this;
    }

    public AuditMapper build(AuditSqlType auditSqlType) throws Exception {
        this.target = new AuditMapper();
        this.auditSqlType = auditSqlType;
        //构建AuditMapperBuilder
        mappedStatement();
        //构建审计sql参数，判断是INSERT，AFTER，BEFORE哪一个
        parameter();
        resultHandler();
        //分页
        rowBounds();
        return this.target;
    }

}


