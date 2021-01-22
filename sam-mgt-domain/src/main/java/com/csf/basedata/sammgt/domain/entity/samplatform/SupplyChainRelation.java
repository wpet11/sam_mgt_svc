package com.csf.basedata.sammgt.domain.entity.samplatform;


import lombok.Getter;
import lombok.Setter;

/**
 * @author michelle.min
 */
@Getter
@Setter
public class SupplyChainRelation {
    private String primaryCode;
    private String relatedCode;
    private String primaryType;
    private String relatedType;
    private Integer relationship;
    private Integer importance;
}
