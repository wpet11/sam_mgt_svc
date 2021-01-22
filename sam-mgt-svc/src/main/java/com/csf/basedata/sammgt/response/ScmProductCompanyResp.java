package com.csf.basedata.sammgt.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author eric.yao
 * @date 2021/1/15
 **/
@Data
public class ScmProductCompanyResp {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String companyCode;

    private String companyName;

    private String productCode;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startValidDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate failureDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reportDate;
}
