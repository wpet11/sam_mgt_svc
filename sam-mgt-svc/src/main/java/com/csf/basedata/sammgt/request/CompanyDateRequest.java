package com.csf.basedata.sammgt.request;

import lombok.Data;

import java.util.Date;

@Data
public class CompanyDateRequest {
    private Integer id;
    private Date startValidDate;
    private Date failureData;
    private Date publishDate;
    private Date reportDate;
}
