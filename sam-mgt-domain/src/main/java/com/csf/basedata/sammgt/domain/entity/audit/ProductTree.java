package com.csf.basedata.sammgt.domain.entity.audit;

import lombok.Data;

import java.util.Date;

/**
 * @author laurence.shi
 */
@Data
public class ProductTree {
    private Long id;
    private String name;
    private String description;
    private Integer status;
    private Date createdTime;
    private Date updatedTime;
    private String createdBy;
    private String updatedBy;
}
