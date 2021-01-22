package com.csf.basedata.sammgt.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author allen.jin
 * @date 2019/12/23
 */

@Data
public class ScmProductUpdatedResp {
    private String publisher;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime csfLastUpdatedTime;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime clientLastUpdatedTime;
}
