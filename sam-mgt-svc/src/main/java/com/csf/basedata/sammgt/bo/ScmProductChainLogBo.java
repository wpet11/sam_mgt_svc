package com.csf.basedata.sammgt.bo;

import com.csf.basedata.sammgt.domain.entity.audit.ChainSubInfo;
import lombok.Data;

import java.util.Date;

/**
 * @Classname ScmProductChainLogBo
 * @Description
 * @Date 2019/12/26 15:14
 * @Created by Eden.fang
 */
@Data
public class ScmProductChainLogBo {

    private Date createdTime;

    private Integer operateType;

    private String operator;

    private ChainSubInfo beforeInfo;

    private ChainSubInfo afterInfo;

    private Long objectId;
}
