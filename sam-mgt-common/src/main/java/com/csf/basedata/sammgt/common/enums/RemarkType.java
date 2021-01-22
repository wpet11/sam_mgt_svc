package com.csf.basedata.sammgt.common.enums;

import static com.csf.basedata.sammgt.common.SamMgtConstants.AUDIT_TYPE_PRODUCT;
import static com.csf.basedata.sammgt.common.SamMgtConstants.AUDIT_TYPE_PRODUCT_CHAIN;

/**
 * @Classname RemarkType
 * @Description
 * @Date 2020/2/13 16:30
 * @Created by eden.fang
 */
public enum RemarkType {

    PRODUCT(AUDIT_TYPE_PRODUCT, 1),
    PRODUCT_CHAIN(AUDIT_TYPE_PRODUCT_CHAIN, 2);

    RemarkType(String name, Integer type) {
        this.name = name;
        this.type = type;
    }

    private String name;

    private Integer type;

    public String getName() {
        return name;
    }

    public Integer getType() {
        return type;
    }

    public static RemarkType getRemarkTypeByType(Integer type) {
        if (type == null) {
            return null;
        }

        for (RemarkType remarkType : RemarkType.values()) {
            if (remarkType.getType().intValue() == type.intValue()) {
                return remarkType;
            }
        }
        return null;
    }
}
