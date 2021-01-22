package com.csf.basedata.sammgt.common.enums;

/**
 * @author allen.jin
 * @date 2019/12/3
 */

public enum Publisher {
    CSF,
    GB2017,
    SYWG2014;

    public static Publisher getPublisher(String publisher) {
        return Publisher.valueOf(Publisher.class, publisher);
    }

}
