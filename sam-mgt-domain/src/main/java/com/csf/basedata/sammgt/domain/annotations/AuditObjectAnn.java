package com.csf.basedata.sammgt.domain.annotations;

import java.lang.annotation.*;


/**
 * 审计对象注解
 *
 * @author michelle.min
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuditObjectAnn {
    /**
     * 审计对象类型
     */
    Class<?> type() default Object.class;

    /**
     * 审计对象类型名称
     */
    String name() default "";
}
