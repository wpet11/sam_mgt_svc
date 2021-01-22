package com.csf.basedata.sammgt.domain.utils;


/**
 * 审计sql类型
 *
 * @author michelle.min
 */
public enum AuditSqlType {
    /**
     * 修改前查询，获取修改前结果
     */
    BEFORE,
    /**
     * 修改后查询，获取修改后最新结果
     */
    AFTER,
    /**
     * 插入审计对象
     */
    INSERT,
}
