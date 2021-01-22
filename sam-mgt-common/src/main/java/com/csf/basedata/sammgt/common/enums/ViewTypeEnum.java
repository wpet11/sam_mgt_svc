package com.csf.basedata.sammgt.common.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author laurence.shi
 */
@Getter
public enum ViewTypeEnum {
    /**
     * 横向
     */
    ACLINIC(1),
    /**
     * 纵向
     */
    VERTICAL(2);

    ViewTypeEnum(int type) {
        this.type = type;
    }

    private int type;

    public static boolean isInclude(int type) {
        return Arrays.stream(ViewTypeEnum.values()).anyMatch(x -> x.getType() == type);
    }
}
