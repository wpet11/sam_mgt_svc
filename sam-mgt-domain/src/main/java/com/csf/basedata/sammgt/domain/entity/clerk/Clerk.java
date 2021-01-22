package com.csf.basedata.sammgt.domain.entity.clerk;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Clerk {
    private Integer id;
    private String name;
    private Integer age;
    private LocalDate createdTime;
    private Integer status;
    private String updatedBy;
    private String createdBy;
}
