package com.csf.basedata.sammgt.domain.entity.samclient;

import lombok.Data;

import java.util.Date;

/**
 * @author laurence.shi
 */
@Data
public class ScmProductTree {
    private Long id;
    private String name;
    private String description;
    private Integer status;
    private Integer viewType;
    private Date createdTime;
    private Date updatedTime;
    private String createdBy;
    private String updatedBy;
    private Date csfIgnoreTime;
}
