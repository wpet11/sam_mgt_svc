package com.csf.basedata.sammgt.request;

import lombok.Data;

import java.util.Set;

/**
 * @Classname ScmProductUpdateRequest
 * @Description
 * @Date 2020/3/20 13:49
 * @Created by eden.fang
 */

@Data
public class ScmProductUpdateRequest {

    private String code;

    private Integer versionId;

    private String name;

    private String parent;

    private Set<String> mappings;

    private String remark;

    private String desc;

    private String publisher;

}
