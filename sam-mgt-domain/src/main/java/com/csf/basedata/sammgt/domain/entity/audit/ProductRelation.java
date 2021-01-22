package com.csf.basedata.sammgt.domain.entity.audit;

import lombok.Data;

import java.util.Date;

/**
 * @author laurence.shi
 */
@Data
public class ProductRelation {
    private Long id;
    private String startNode;
    private String endNode;
    private String relation;
    private Long treeId;
    private Integer status;
    private Date createdTime;
    private Date updatedTime;
    private String createdBy;
    private String updatedBy;
}
