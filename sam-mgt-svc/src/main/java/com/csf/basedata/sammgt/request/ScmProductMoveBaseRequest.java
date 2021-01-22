package com.csf.basedata.sammgt.request;

import lombok.Data;

/**
 * @Classname ScmProductMoveBaseRequest
 * @Description
 * @Date 2020/3/27 9:34
 * @Created by eden.fang
 */
@Data
public class ScmProductMoveBaseRequest extends ScmProductBaseRequest {

    private String parentCode;

}
