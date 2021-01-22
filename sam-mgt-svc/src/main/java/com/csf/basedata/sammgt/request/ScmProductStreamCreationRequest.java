package com.csf.basedata.sammgt.request;

import lombok.Data;

/**
 * Created by bob.huang on 2019/12/6
 */
@Data
public class ScmProductStreamCreationRequest {

    private String productCode;
    private String target;
    private Integer direction;
    private RelationMetadata upRelation;
    private RelationMetadata downRelation;
    private String remark;
    private String publisher;

    @Data
    public static class RelationMetadata {
        private String relationType;
        private Integer weight;
    }
}
