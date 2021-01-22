package com.csf.basedata.sammgt.bo;

import com.csf.basedata.sammgt.common.enums.Publisher;
import lombok.Data;

/**
 * @author allen.jin
 * @date 2020/1/7
 */

@Data
public class ScmProductMapping {
    private String csfProductCode;
    private String thirdPartyCode;
    private Publisher publisher;
    private Integer status;
    private String csfProductParentCode;
    private String csfProductName;
    private String csfProductNameEn;
    private Long treeId;
}
