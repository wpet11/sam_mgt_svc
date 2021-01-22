package com.csf.basedata.sammgt.bo;

import com.csf.basedata.sammgt.common.enums.Publisher;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @Description: A Class to discribe the attributes about products. And Used In Writing Data Into CSV File.
 * @author allen.jin
 * @date 2019/12/3
 */

@Data
public class ScmProductInfo extends ScmProductBaseInfo {
    private String parentCode;
    @JsonIgnore
    private String ancestors;
    private String source;
    private String descZh;
    private String descEn;
    @JsonIgnore
    private long id;
    @JsonIgnore
    private Integer versionId;
    private Publisher publisher;
    private Long treeId;
    private Integer sequence;
}
