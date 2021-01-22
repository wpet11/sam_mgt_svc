package com.csf.basedata.sammgt.domain.entity.audit;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @Classname LogRequest
 * @Description
 * @Date 2019/12/26 14:32
 * @Created by Eden.fang
 */
@Data
public class LogRequest {

    private Date startDate;

    private Date endDate;

    private Integer operationType;

    private String operator;

    private String objectType;

    private List<Long> list;

    private Integer start;

    private Integer pageSize;

    public LogRequest(Date startDate, Date endDate, Integer operationType,
                      String operator, String objectType, List<Long> list){
        this.startDate = startDate;
        this.endDate = endDate;
        this.operationType = operationType;
        this.operator = operator;
        if (StringUtils.isEmpty(this.operator)){
            this.operator = null;
        }
        this.objectType = objectType;
        this.list = list;
    }
}
