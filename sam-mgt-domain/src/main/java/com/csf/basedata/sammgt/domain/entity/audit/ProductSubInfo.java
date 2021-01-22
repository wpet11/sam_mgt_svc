package com.csf.basedata.sammgt.domain.entity.audit;

import lombok.Data;

import java.util.Set;

/**
 * @Classname ProductSubInfo
 * @Description
 * @Date 2019/12/26 17:37
 * @Created by Eden.fang
 */
@Data
public class ProductSubInfo {

    private String code;

    private String nameZh;

    private String nameEn;

    private String parent;

    private String parentNameZh;

    private String parentnameEn;

    private Set<LogNodeInfo> mapperInfo;

    private String desc;

    private Long treeId;
}
