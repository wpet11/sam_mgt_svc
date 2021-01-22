package com.csf.basedata.sammgt.domain.entity.samclient;

import lombok.Data;

/**
 * Created by bob.huang on 2019/12/3
 */
@Data
public class ScmProductMapping {
    private Long id;
    private String csfProductCode;
    private String thirdPartyCode;
    private String publisher;
    private Integer status;
    private String csfProductParentCode;
    private String csfProductName;
    private String csfProductNameEn;
    private String createdBy;
    private String updatedBy;
    private Long treeId;
}
