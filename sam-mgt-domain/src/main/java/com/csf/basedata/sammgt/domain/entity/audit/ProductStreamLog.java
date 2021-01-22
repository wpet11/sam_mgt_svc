package com.csf.basedata.sammgt.domain.entity.audit;

import lombok.Data;

/**
 * @Classname ProductStreamLog
 * @Description
 * @Date 2019/12/26 15:10
 * @Created by Eden.fang
 */
@Data
public class ProductStreamLog extends BaseLogInfo{

    private ChainSubInfo beforeInfo;

    private ChainSubInfo afterInfo;

}
