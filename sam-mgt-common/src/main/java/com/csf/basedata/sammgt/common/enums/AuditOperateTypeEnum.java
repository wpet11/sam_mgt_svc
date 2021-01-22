package com.csf.basedata.sammgt.common.enums;


import lombok.Getter;

/**
 * @author michelle.min
 */
public enum AuditOperateTypeEnum {
    INSERT(1),
    UPDATE(2),
    DELETE(3);

    @Getter
    private int type;

    AuditOperateTypeEnum(int type) {
        this.type = type;
    }
}
