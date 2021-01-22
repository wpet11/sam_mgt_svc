package com.csf.basedata.sammgt.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;


/**
 * @author laurence.shi
 */
@Data
public class ScmProductRelationBo {
    @JsonIgnore
    private Long id;
    private String startProduct;
    private String endProduct;
    private String relation;
    @JsonIgnore
    private Long treeId;
    @JsonIgnore
    private Integer status;
    @JsonIgnore
    private Date createdTime;
    @JsonIgnore
    private Date updatedTime;
    @JsonIgnore
    private String createdBy;
    @JsonIgnore
    private String updatedBy;
}
