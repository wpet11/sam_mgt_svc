package com.csf.basedata.sammgt.bo;

import lombok.Data;

import java.util.List;

/**
 * @author allen.jin
 * @date 2020/3/9
 */

@Data
public class ProductAttr {
    private String name;
    private String desc;
    private String productCode;
    private List<ScmProductBaseInfo> mapInfos;
}
