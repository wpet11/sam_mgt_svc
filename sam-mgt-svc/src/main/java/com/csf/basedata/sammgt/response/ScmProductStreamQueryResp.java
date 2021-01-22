package com.csf.basedata.sammgt.response;

import com.csf.basedata.sammgt.bo.Node;
import lombok.Data;

/**
 * Created by bob.huang on 2019/12/9
 */
@Data
public class ScmProductStreamQueryResp {

    private Node nodeA;
    private Node nodeB;
    private Integer direction;
    private String relationType;
    private Integer weight;
}
