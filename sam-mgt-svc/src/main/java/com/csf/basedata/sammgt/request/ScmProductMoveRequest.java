package com.csf.basedata.sammgt.request;

import lombok.Data;

/**
 * @author allen.jin
 * @date 2020/3/20
 */

@Data
public class ScmProductMoveRequest extends ScmProductBaseRequest {
    private String targetParentCode;
    private String remark;
}
