package com.csf.basedata.sammgt.domain.entity.audit;

import lombok.Data;

import java.util.Date;

/**
 * @author eric.yao
 * @date 2021/1/13
 **/
@Data
public class NodeTable {
    private long id;
    private Integer versionId;
    private String code;
    private Integer status;
    private Integer level;
    private String source;
    private String parent;
    private int isLeaf;
    private String name;
    private Integer sequence;
    private String updatedBy;
    private String createdBy;
    private Date createdTime;
    private Date updatedTime;
    private String remark;
}
