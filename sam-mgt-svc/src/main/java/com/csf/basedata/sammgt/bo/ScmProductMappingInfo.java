package com.csf.basedata.sammgt.bo;

import lombok.Data;

import java.util.Set;

/**
 * @author allen.jin
 * @date 2020/3/16
 */

@Data
public class ScmProductMappingInfo {
    private String name;
    private String productCode;
    private String desc;
    private Set<ScmProductBaseInfo> csfProductCodes;
}
