package com.csf.basedata.sammgt.bo;

import lombok.Data;

/**
 * @author allen.jin
 * @date 2019/12/10
 */

@Data
public class CsfProductNode extends ProductNode {
    private boolean existAdd;
    private boolean existRename;
    private boolean existMove;
    private boolean existUnMap;

    public CsfProductNode() {
        super();
    }

}
