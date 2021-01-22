package com.csf.basedata.sammgt.domain.entity.samclient;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author allen.jin
 * @date 2019/12/23
 */

@Data
public class ScmInit {
    private Long id;
    private String publisher;
    private Integer status;
    private LocalDateTime initStartTime;
    private LocalDateTime initEndTime;
    private String createdBy;
    private String updatedBy;
    private Long treeId;
}
