package com.csf.basedata.sammgt.common.enums;

import lombok.Getter;


/**
 * @author michelle.min
 */
public enum ProductChainTypeEnum {
    A("生产设备"),
    D("销售渠道"),
    E("生产环境"),
    F("辅助设备"),
    M("生产原料"),
    P("产品/业务"),
    R("辅助原料"),
    T("技术服务"),
    UDF("暂未定义");

    @Getter
    private String name;

    ProductChainTypeEnum(String name) {
        this.name = name;
    }

    public static boolean isExist(String relationType) {
        for (ProductChainTypeEnum chainTypeEnum : ProductChainTypeEnum.values()) {
            if (chainTypeEnum.name().equals(relationType)) {
                return true;
            }
        }
        return false;
    }

}
