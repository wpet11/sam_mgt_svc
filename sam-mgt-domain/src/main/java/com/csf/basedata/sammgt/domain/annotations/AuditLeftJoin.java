package com.csf.basedata.sammgt.domain.annotations;


import java.lang.annotation.*;


/**
 * 审计对象左连接注解
 *
 * @author michelle.min
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuditLeftJoin {
    /**
     * 关联表名
     */
    String tableName() default "";

    /**
     * 查询关联表列名
     */
    String columnName() default "";

    /**
     * 关联主表列
     */
    String conditionPrimary() default "";
    /**
     * 关联表列
     */
    String conditionRelated() default "";
    /**
     * 过滤条件
     */
    String conditionWhere() default "";
}
