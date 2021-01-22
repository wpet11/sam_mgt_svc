package com.csf.basedata.sammgt.request;

import lombok.Data;

import java.util.List;

/**
 * @Classname ScmProductBatchMoveRequest
 * @Description
 * @Date 2020/3/27 9:33
 * @Created by eden.fang
 */
@Data
public class ScmProductBatchMoveRequest {

    private String targetParentCode;

    private List<ScmProductMoveBaseRequest> products;
}
