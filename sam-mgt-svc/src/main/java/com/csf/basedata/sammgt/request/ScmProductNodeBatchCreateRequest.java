package com.csf.basedata.sammgt.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname ScmProductNodeBatchCreateRequest
 * @Description
 * @Date 2020/1/15 10:55
 * @Created by eden.fang
 */
@Data
public class ScmProductNodeBatchCreateRequest {

    private String targetParentCode;

    private String publisher;

    private Long treeId;

    private List<BatchCreateProductsRequest> csfProducts = new ArrayList<>();

}
