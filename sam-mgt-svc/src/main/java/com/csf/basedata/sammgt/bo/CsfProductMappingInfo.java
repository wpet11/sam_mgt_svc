package com.csf.basedata.sammgt.bo;

import lombok.Data;

/**
 * @author allen.jin
 * @date 2020/3/16
 */

@Data
public class CsfProductMappingInfo {
    private String csfProductCode;
    private String clientProductCode;
    private String clientProductName;
    private String clientProductDesc;
    private Integer level;
}
