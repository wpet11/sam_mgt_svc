package com.csf.basedata.sammgt.response;

import lombok.Data;

/**
 * @author allen.jin
 * @date 2020/3/16
 */

@Data
public class ScmProductMappingTreeResp extends ScmProductTreeResp {
    private int delMappedCnt;
    private int mulMappedCnt;
}
