package com.csf.basedata.sammgt.common.enums;

/**
 * @author allen.jin
 * @date 2019/12/10
 */

public enum  DataStatusEnum {
    R("Rename"),
    A("ADD"),
    MV("MOVE"),
    UM("Unmap");

    private String desc;

    DataStatusEnum(String desc) {
        this.desc = desc;
    }
}
