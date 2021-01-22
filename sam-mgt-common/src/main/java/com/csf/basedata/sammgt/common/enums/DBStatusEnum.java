package com.csf.basedata.sammgt.common.enums;

import lombok.Getter;

/**
 * Created by bob.huang on 2019/12/5
 */
@Getter
public enum DBStatusEnum {

    INVALID(0), VALID(1),PUBLISH(2);

    private Integer value;

    DBStatusEnum(Integer value) {
        this.value = value;
    }
}
