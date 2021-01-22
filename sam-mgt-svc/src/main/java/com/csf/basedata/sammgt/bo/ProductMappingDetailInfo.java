package com.csf.basedata.sammgt.bo;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * @Classname ProductMappingDetailInfo
 * @Description
 * @Date 2020/3/20 14:56
 * @Created by eden.fang
 */
@Getter
@Setter
public class ProductMappingDetailInfo {
    private Long id;
    private Integer versionId;
    private String source;
    private String parentCode;
    private Integer level;
    private String name;
    private String productCode;
    private Set<String> csfProductCodes = new HashSet<>();
    private Long treeId;
    private Integer sequence;
}
