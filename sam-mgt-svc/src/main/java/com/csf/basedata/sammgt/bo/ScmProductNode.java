package com.csf.basedata.sammgt.bo;

import lombok.Data;

import java.util.List;

/**
 * @Classname ScmProductNode
 * @Description
 * @Date 2020/3/12 14:06
 * @Created by eden.fang
 */
@Data
public class ScmProductNode extends ProductNode {
    private List<String> mapProductCodes;
    private String source;
    private boolean deleteMapped;
    private boolean mulMapped;
    private Integer versionId;
    private long objectId;
    private Long treeId;
    private Integer sequence;

    public ScmProductNode() {
        super();
    }

}
