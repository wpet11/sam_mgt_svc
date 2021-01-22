package com.csf.basedata.sammgt.request;

import lombok.Data;

/**
 * @Classname BatchCreateProductsRequest
 * @Description
 * @Date 2020/1/17 15:24
 * @Creaty eden.fang
 */
@Data
public class BatchCreateProductsRequest {

    private String parentCode;

    private String productCode;
}
