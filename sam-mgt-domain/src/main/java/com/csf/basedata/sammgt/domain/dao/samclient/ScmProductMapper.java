package com.csf.basedata.sammgt.domain.dao.samclient;

import com.csf.basedata.sammgt.common.SamMgtConstants;
import com.csf.basedata.sammgt.domain.annotations.AuditObjectAnn;
import com.csf.basedata.sammgt.domain.entity.audit.NodeTable;
import com.csf.basedata.sammgt.domain.entity.samclient.ScmProduct;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author michelle.min
 */
@Mapper
@AuditObjectAnn(type = NodeTable.class, name = SamMgtConstants.AUDIT_TYPE_PRODUCT)
public interface ScmProductMapper {

    Integer updateProduct(ScmProduct request);

    List<ScmProduct> getScmProductTree();

}
