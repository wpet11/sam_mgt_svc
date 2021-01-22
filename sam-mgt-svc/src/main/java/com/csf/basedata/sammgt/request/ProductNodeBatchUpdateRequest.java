package com.csf.basedata.sammgt.request;

import lombok.Data;

/**
 * @Classname ProductNodeBatchUpdateRequest
 * @Description
 * @Date 2020/1/14 10:23
 * @Created by eden.fang
 */
@Data
public class ProductNodeBatchUpdateRequest extends ProductNodeBatchBaseRequest {

    private String parent;

}
