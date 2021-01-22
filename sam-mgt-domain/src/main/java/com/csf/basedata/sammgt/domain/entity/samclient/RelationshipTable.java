package com.csf.basedata.sammgt.domain.entity.samclient;

import lombok.Data;

import java.util.Date;

@Data
public class RelationshipTable {
    private long id;
    private String headNodeCode;
    private String tailNodeCode;
    private Integer weight;
    private Integer relationship;
    private String headType;
    private String tailType;
    private Date createdTime;
    private Date updatedTime;
    private String createdBy;
    private String updatedBy;
    private Integer status;
}
