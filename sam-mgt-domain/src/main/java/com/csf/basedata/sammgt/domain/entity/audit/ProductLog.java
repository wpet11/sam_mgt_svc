package com.csf.basedata.sammgt.domain.entity.audit;

import lombok.Data;

/**
 * @Classname ProductLog
 * @Description
 * @Date 2019/12/25 11:46
 * @Created by Eden.fang
 */
@Data
public class ProductLog extends BaseLogInfo {

    private ProductSubInfo beforeInfo;

    private ProductSubInfo afterInfo;

}
