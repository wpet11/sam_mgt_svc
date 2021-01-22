package com.csf.basedata.sammgt.domain.entity.audit;

import lombok.Data;

import java.util.Date;


/**
 * @author michelle.min
 */
@Data
public class ProductStream {
    private long id;
    private String upstreamCode;
    private String downstreamCode;
    private Integer weight;
    private Integer direction;
    private String upstreamType;
    private String downstreamType;
    private Integer status;
    private String updatedBy;
    private String createdBy;
    private Date createdTime;
    private Date updatedTime;
    private String remark;
}
