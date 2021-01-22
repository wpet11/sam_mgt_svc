package com.csf.basedata.sammgt.domain.entity.samclient;

import lombok.Data;

import java.util.Date;

/**
 * @author laurence.shi
 */
@Data
public class ScmProductRelation {
    private Long id;
    private String startProduct;
    private String endProduct;
    private String relation;
    private Long treeId;
    private Integer status;
    private Date createdTime;
    private Date updatedTime;
    private String createdBy;
    private String updatedBy;
}
