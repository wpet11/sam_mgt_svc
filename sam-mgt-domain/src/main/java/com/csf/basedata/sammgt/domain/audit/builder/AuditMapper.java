package com.csf.basedata.sammgt.domain.audit.builder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;


/**
 * 审计操作mapper
 * @author michelle.min
 */
@Getter
@Setter
@ToString
public class AuditMapper {
    /**
     * mapper基本信息
     */
    private MappedStatement mappedStatement;
    /**
     * 执行参数
     */
    private Object parameter;
    /**
     * 返回结果处理器
     */
    private ResultHandler resultHandler;
    /**
     * mybatis内置分页
     */
    private RowBounds rowBounds;

    public AuditMapper() {

    }

    public AuditMapper(MappedStatement mappedStatement, Object parameter) {
        this.mappedStatement = mappedStatement;
        this.parameter = parameter;
    }
}


