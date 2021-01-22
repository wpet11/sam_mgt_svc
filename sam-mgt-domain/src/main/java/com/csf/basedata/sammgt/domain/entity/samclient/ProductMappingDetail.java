package com.csf.basedata.sammgt.domain.entity.samclient;

import lombok.Getter;
import lombok.Setter;

/**
 * @Classname ClientProductMapping
 * @Description
 * @Date 2020/3/20 14:39
 * @Created by eden.fang
 */
@Getter
@Setter
public class ProductMappingDetail {

    private Long id;

    private String code;

    private String source;

    private Integer versionId;

    private String parent;

    private Integer level;

    private String nameZh;

    private String csfProductCode;

    private String ancestors;

    private String descZh;

    private Long treeId;

    private Integer sequence;
}
