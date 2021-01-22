package com.csf.basedata.sammgt.bo;

import lombok.Data;

import java.util.Date;


/**
 * @author michelle.min
 */
@Data
public class ScmProductStreamBo {
    private long id;
    private String upstreamCode;
    private String downstreamCode;
    private Integer weight;
    private Integer direction;
    private String upstreamType;
    private String downstreamType;
    private Date createdTime;
    private Date updatedTime;
    private String createdBy;
    private String updatedBy;
    private Integer status;
    private String remark;
    private String publisher;
}
