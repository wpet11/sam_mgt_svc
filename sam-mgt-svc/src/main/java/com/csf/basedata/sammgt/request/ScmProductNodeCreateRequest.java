package com.csf.basedata.sammgt.request;

import lombok.Data;

/**
 * @Classname ScmProductNodeCreateRequest
 * @Description
 * @Date 2020/3/27 11:03
 * @Created by laurence.shi
 */
@Data
public class ScmProductNodeCreateRequest {
    private String productCode;
    private String parentCode;
    private String name;
    private String remark;
    private String publisher;
    private Long treeId;
}
