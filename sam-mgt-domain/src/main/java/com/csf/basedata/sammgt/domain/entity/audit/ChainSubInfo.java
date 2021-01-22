package com.csf.basedata.sammgt.domain.entity.audit;

import lombok.Data;

/**
 * @Classname ChainSubInfo
 * @Description
 * @Date 2019/12/26 15:09
 * @Created by Eden.fang
 */
@Data
public class ChainSubInfo {

    private String upstreamCode;

    private String downstreamCode;

    private Integer direction;

    private Integer upStreamWeight;

    private Integer downStreamWeight;

    private String downstreamType;

    private String upstreamType;

    private String upStreamNameZh;

    private String downStreamNameZh;

    private String upStreamNameEn;

    private String downStreamNameEn;

}
