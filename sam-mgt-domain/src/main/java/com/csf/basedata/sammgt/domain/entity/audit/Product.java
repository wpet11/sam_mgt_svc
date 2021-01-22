package com.csf.basedata.sammgt.domain.entity.audit;

import lombok.Data;

import java.util.Date;

/**
 * @author michelle.min
 */
@Data
public class Product{
    private long id;
    private Integer versionId;
    private String code;
    private Integer status;
    private Integer level;
    private String parent;
    private String nameZh;
    private String nameEn;
    private Long treeId;
    private Integer sequence;
    //@AuditLeftJoin(columnName = "csf_product_code,csf_product_name,csf_product_name_en", tableName = "scm_product_mapping", conditionPrimary = "code", conditionRelated = "third_party_code", conditionWhere = "$temp.status=1")
    private String mappers;
    private String updatedBy;
    private String createdBy;
    private Date createdTime;
    private Date updatedTime;
    private String remark;
    private String descZh;
    private String descEn;
}
