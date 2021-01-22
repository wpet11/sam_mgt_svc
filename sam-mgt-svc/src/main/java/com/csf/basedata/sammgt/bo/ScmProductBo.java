package com.csf.basedata.sammgt.bo;


import lombok.Data;

import java.time.LocalDateTime;


/**
 * @author michelle.min
 */
@Data
public class ScmProductBo {
    private long id;
    private Integer versionId;
    private String code;
    private String publisher;
    private Integer status;
    private Integer level;
    private String parent;
    private String ancestors;
    private String nameZh;
    private String nameEn;
    private Long treeId;
    private Integer sequence;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String createdBy;
    private String updatedBy;
    private String source;
    private String descZh;
    private String descEn;
    private String remark;
}
