package com.csf.basedata.sammgt.bo;

import com.csf.basedata.sammgt.domain.entity.audit.ProductSubInfo;
import lombok.Data;

import java.util.Date;

/**
 * @Classname ScmProductLogBo
 * @Description
 * @Date 2019/12/25 13:35
 * @Created by Eden.fang
 */
@Data
public class ScmProductLogBo {

    private Date createTime;

    private String operator;

    private Integer operatType;

    private ProductSubInfo beforeInfo;

    private ProductSubInfo afterInfo;

    private Long objectId;

}
