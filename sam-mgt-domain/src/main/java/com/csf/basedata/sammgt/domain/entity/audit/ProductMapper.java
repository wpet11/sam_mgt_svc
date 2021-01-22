package com.csf.basedata.sammgt.domain.entity.audit;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 * @author michelle.min
 */
@Getter
@Setter
public class ProductMapper {
    private long id;
   // @AuditLeftJoin(columnName = "id", tableName = "dict_industry_product", conditionPrimary = "third_party_code", conditionRelated = "code", conditionWhere = "$temp.status=1")
    private String productId;
    //@AuditLeftJoin(columnName = "version_id", tableName = "dict_industry_product", conditionPrimary = "third_party_code", conditionRelated = "code", conditionWhere = "$temp.status=1")
    private Integer productVersionId;
    private String thirdPartyCode;
    private String publisher;
    private Integer status;
    private String createdBy;
    private String updatedBy;
    private Date createdTime;
    private Date updatedTime;
}
