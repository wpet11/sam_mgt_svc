package com.csf.basedata.sammgt.domain.dao.samclient;

import com.csf.basedata.sammgt.domain.entity.audit.LogRequest;
import com.csf.basedata.sammgt.domain.entity.samclient.ScmAudit;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author michelle.min
 */
public interface ScmAuditMapper {
    void bulkInsert(@Param("list") List<ScmAudit> list);

    List<ScmAudit> findScmAuditList(LogRequest request);
}
