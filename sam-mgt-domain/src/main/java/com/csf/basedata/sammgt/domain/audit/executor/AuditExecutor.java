package com.csf.basedata.sammgt.domain.audit.executor;


import com.csf.basedata.sammgt.domain.audit.builder.AuditMapperBuilder;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.SQLException;
import java.util.List;

/**
 * 审计执行器
 *
 * @author michelle.min
 */
public interface AuditExecutor {


    /**
     * 被审计插件拦截sql执行前查询操作，获取修改前结果
     */
    List<?> beforeExecute(Executor executor);

    /**
     * 被审计插件拦截sql执行后查询操作，获取修改后结果
     */
    List<?> afterExecute();

    void execute();

    /**
     * 审计对象插入操作
     */
    int insert();

    <E> List<E> query(MappedStatement mappedStatement, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, boolean commit, Executor delegate) throws SQLException;

    int update(MappedStatement mappedStatement, Object parameter, boolean commit, Executor delegate) throws SQLException;

    void setBeforeResult(List<Object> beforeResult);

    void setAsync(boolean async);

    AuditMapperBuilder getBuilder();

}
