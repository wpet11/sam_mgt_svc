package com.csf.basedata.sammgt.bo;

import lombok.Data;

/**
 * @Author lei.zhao_ext
 * @Date 2021/1/15
 */
@Data
public class CompanyBo {
    private Long id;
    private String companyName;
    private String companyCode;
    private String productCode;
    private  boolean operation;
}
