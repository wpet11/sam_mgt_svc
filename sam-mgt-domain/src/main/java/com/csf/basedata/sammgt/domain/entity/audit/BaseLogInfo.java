package com.csf.basedata.sammgt.domain.entity.audit;

import lombok.Data;

import java.util.Date;

/**
 * @Classname BaseLogInfo
 * @Description
 * @Date 2019/12/26 15:12
 * @Created by Eden.fang
 */
@Data
public class BaseLogInfo {

    private Long id;

    private Date createdTime;

    private Integer operateType;

    private String operator;
}
