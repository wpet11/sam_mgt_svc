package com.csf.basedata.sammgt.request;

import lombok.Data;

import java.util.Set;

/**
 * @Classname ProductNodeBatchBaseRequest
 * @Description
 * @Date 2020/1/13 14:42
 * @Created by eden.fang
 */
@Data
public class ScmProductBatchBaseRequest {

    private Set<ScmProductBaseRequest> products;

    private String remark;

    private Long treeId;
}
