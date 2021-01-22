package com.csf.basedata.sammgt.domain.dao.samplatform;

import com.csf.basedata.sammgt.domain.entity.audit.LogRequest;
import com.csf.basedata.sammgt.domain.entity.samclient.ScmAudit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClerkMapper {
    void bulkInsert(@Param("list") List<ScmAudit> list);

    List<ScmAudit> findScmAuditList(LogRequest request);
}
