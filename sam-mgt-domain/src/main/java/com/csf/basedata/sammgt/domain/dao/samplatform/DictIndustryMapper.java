package com.csf.basedata.sammgt.domain.dao.samplatform;

import com.csf.basedata.sammgt.common.SamMgtConstants;
import com.csf.basedata.sammgt.domain.annotations.AuditObjectAnn;
import com.csf.basedata.sammgt.domain.entity.clerk.Clerk;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author allen.jin
 * @date 2019/12/5
 */

@Mapper
@AuditObjectAnn(type = Clerk.class, name = SamMgtConstants.AUDIT_TYPE_PRODUCT)
public interface DictIndustryMapper {

    List<Clerk> getAllClerk();

    Integer updateClerk(Clerk clerk);
}
