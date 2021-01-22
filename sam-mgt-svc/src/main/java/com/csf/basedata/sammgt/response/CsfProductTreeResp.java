package com.csf.basedata.sammgt.response;

import com.csf.basedata.sammgt.bo.CsfProductNode;
import lombok.Data;

import java.util.List;

/**
 * @Classname CsfProductTreeResp
 * @Description
 * @Date 2020/3/4 15:25
 * @Created by eden.fang
 */

@Data
public class CsfProductTreeResp<T extends CsfProductNode> {
    private Integer addCount;
    private Integer renameCount;
    private Integer moveCount;
    private Integer unMapCount;
    private List<T> productNodes;
}
