package com.csf.basedata.sammgt.domain.entity.samclient;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ScmCompany {
    private long id;
    private String productCode;
    private String companyCode;
    private String companyName;
    private LocalDate startValidDate;
    private LocalDate failureDate;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String createdBy;
    private String updatedBy;
    private LocalDate publishDate;
    private LocalDate reportDate;
    private LocalDate entryDate;
}
