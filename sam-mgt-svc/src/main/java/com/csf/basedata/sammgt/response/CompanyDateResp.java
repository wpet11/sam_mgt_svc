package com.csf.basedata.sammgt.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CompanyDateResp {
    private String companyName;
    private String companyCode;
    private String productName;
    private String productCode;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startValidDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate failureData;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reportDate;

}
