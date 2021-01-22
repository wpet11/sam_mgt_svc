package com.csf.basedata.sammgt.response;

import lombok.Data;

import java.util.List;

/**
 * @author allen.jin
 * @date 2020/3/24
 */

@Data
public class InitInfoResp {
    /**
     * 初始化状态 1：初始化完成 0：没有初始化
     */
    private Integer initStatus;
    /**
     * 可以进行初始化的产品类型
     */
    private List<String> publishers;

    private Long treeId;
}
