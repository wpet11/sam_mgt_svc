package com.csf.basedata.sammgt.request;

import lombok.Data;

/**
 * @Author lei.zhao_ext
 * @Date 2021/1/15
 */
@Data
public class AttriCompanyRequest {
    private String productCode;
    private Integer effective;
    private String companyName;
    private String companyCode;

    public AttriCompanyRequest() {
    }

    public AttriCompanyRequest(String productCode, Integer effective, String companyCode) {
        this.productCode = productCode;
        this.effective = effective;
        this.companyCode = companyCode;
    }
}
