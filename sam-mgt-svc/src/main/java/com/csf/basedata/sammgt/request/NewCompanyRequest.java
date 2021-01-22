package com.csf.basedata.sammgt.request;

import lombok.Data;

import java.util.Date;

/**
 * @author eric.yao
 * @date 2021/1/15
 **/
@Data
public class NewCompanyRequest {

    private String companyCode;

    private String companyName;

    /**
     * 起始有效日期
     */
    private Date startValidDate;

    /**
     * 终止有效日期
     */
    private Date failureDate;

    /**
     * 公布日期
     */
    private Date publishDate;

    /**
     * 报表日期
     */
    private Date reportDate;

}
