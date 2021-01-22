package com.csf.basedata.sammgt.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;


/**
 * @author laurence.shi
 */
@Data
public class ScmProductTreeBo {
    private Long id;
    private String name;
    private String description;
    private Integer status;
    private Integer viewType;
    @JsonIgnore
    private Date createdTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updatedTime;
    @JsonIgnore
    private String createdBy;
    private String updatedBy;
    @JsonIgnore
    private Date csfIgnoreTime;
}
