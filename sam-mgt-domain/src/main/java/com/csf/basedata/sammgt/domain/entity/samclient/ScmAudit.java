package com.csf.basedata.sammgt.domain.entity.samclient;

import lombok.Data;

import java.util.Date;


/**
 * @author michelle.min
 */
@Data
public class ScmAudit {
    private long id;
    private Long objectId;
    private String fieldName;
    private String beforeValue;
    private String afterValue;
    private Date createdTime;
    private String createdBy;
    private Integer operateType;
    private String objectType;

}
