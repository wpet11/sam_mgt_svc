package com.csf.basedata.sammgt.bo;

import lombok.Data;

import java.util.List;


/**
 * @author michelle.min
 */
@Data
public class ScmProductStreamTreeBo {
    private String code;
    private ScmProductStreamBo chain;
    private List<ScmProductStreamTreeBo> children;

    public ScmProductStreamTreeBo() {
    }

    public ScmProductStreamTreeBo(String code, ScmProductStreamBo chain, List<ScmProductStreamTreeBo> children) {
        this.code = code;
        this.chain = chain;
        this.children = children;
    }
}
