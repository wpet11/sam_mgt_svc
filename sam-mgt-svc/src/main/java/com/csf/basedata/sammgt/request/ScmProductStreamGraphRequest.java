package com.csf.basedata.sammgt.request;


import com.csf.basedata.sammgt.common.enums.ProductChainBoundEnum;
import lombok.Data;

import java.util.Set;

/**
 * @author michelle.min
 */

@Data
public class ScmProductStreamGraphRequest {
    private String productCode;
    private int preLevel;
    private Integer direction;
    private Set<Integer> weightSet;
    private ProductChainBoundEnum bound;

    public ScmProductStreamGraphRequest() {
    }

    public ScmProductStreamGraphRequest(String productCode, int preLevel, Integer direction, Set<Integer> weightSet, ProductChainBoundEnum bound) {
        this.productCode = productCode;
        this.preLevel = preLevel;
        this.direction = direction;
        this.weightSet = weightSet;
        this.bound = bound;
    }
}
