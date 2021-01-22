package com.csf.basedata.sammgt.bo;

import lombok.Data;

/**
 * @author allen.jin
 * @date 2020/3/16
 */

@Data
public class CsfProductInfo extends ScmProductInfo {
    private boolean existAdd; //是否新增
    private boolean existRename; //是否更名
    private boolean existMove; //是否移动
    private boolean existUnMap; //是否没有映射
}
