package com.csf.basedata.sammgt.domain.entity.samclient;

import lombok.Data;

/**
 * @author allen.jin
 * @date 2020/3/16
 */

@Data
public class ProductMapping {
    private String code;
    private String nameZh;
    private String descZh;
    private String csfProductCode;
    private String csfProductName;
    private Integer level;
}
