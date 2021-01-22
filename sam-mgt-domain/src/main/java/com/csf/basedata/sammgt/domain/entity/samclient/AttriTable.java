package com.csf.basedata.sammgt.domain.entity.samclient;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class AttriTable {
    private long id;
    private String productCode;
    private String companyCode;
    private String companyName;
    private Date startValidDate;
    private Date failureDate;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String createdBy;
    private String updatedBy;
    private Date publishDate;
    private Date reportDate;
    private Date entryDate;
}
