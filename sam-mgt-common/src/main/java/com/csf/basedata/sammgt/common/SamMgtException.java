package com.csf.basedata.sammgt.common;

import com.csf.basedata.sammgt.common.enums.ErrorEnum;
import lombok.Data;

/**
 * @author allen.jin
 * @date 2020/3/20
 */

@Data
public class SamMgtException extends RuntimeException {

    private ErrorEnum errorEnum;

    public SamMgtException(ErrorEnum errorEnum) {
        super(errorEnum.getErrorMessage());
        this.errorEnum = errorEnum;
    }

    public SamMgtException(ErrorEnum errorEnum, Object ... params) {
        super(errorEnum.format(params));
        this.errorEnum = errorEnum;
    }

}
