package com.csf.basedata.sammgt.bo;

import lombok.Data;

/**
 * Created by bob.huang on 2019/12/3
 */
@Data
public class ScmProductMappingBo {

    private Long id;
    private String csfProductCode;
    private String csfProductCodeName;
    private String csfProductCodeNameEn;
    private String csfProductParentCode;
    private String thirdPartyCode;
    private String publisher;
    private Integer status;
    private String createdBy;
    private String updatedBy;
    private Long treeId;
}
