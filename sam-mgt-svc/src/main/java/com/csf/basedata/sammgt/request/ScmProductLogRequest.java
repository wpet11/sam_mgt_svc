package com.csf.basedata.sammgt.request;

import lombok.Data;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Classname ScmProductLogRequest
 * @Description
 * @Date 2019/12/25 10:43
 * @Created by Eden.fang
 */
@Data
public class ScmProductLogRequest {

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;

    private Integer operationType;

    private String productCode;

    private String userName;

    private Integer pageSize = 20;

    private Integer pageNo = 1;

    public void check() {
        if (this.getStartDate() == null) {
            this.setStartDate(DateUtils.addDays(new Date(), -14));
        }

        if (this.getEndDate() == null) {
            this.setEndDate(new Date());
        } else {
            this.setEndDate(DateUtils.addDays(this.getEndDate(), 1));
        }
    }

}
