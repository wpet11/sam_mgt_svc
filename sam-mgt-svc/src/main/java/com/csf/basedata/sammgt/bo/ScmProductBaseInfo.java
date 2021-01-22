package com.csf.basedata.sammgt.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author allen.jin
 * @date 2019/12/19
 */

@Data
public class ScmProductBaseInfo {
    private String productCode;
    @JsonProperty("name")
    private String nameZh;
    @JsonIgnore
    private String nameEn;
    private Integer level;
    @JsonIgnore
    private LocalDateTime lastUpdatedTime;
}
