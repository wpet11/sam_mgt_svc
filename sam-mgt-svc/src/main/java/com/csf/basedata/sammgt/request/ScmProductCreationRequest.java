package com.csf.basedata.sammgt.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

/**
 * Created by bob.huang on 2019/12/2
 */

@Data
public class ScmProductCreationRequest {

    @JsonProperty("isRoot")
    private boolean isRoot;
    @JsonProperty("isBlank")
    private boolean isBlank;
    private String name;
    private String publisher;
    private String parent;
    private Set<String> mappings;
    private String remark;
    private String desc;
    private Long treeId;
}
