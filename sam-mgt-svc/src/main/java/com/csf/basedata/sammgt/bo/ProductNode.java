package com.csf.basedata.sammgt.bo;

import lombok.Data;

/**
 * @author allen.jin
 * @date 2019/12/3
 */

@Data
public class ProductNode {
    private String productCode;
    private String name;
    private String parentCode;
    private Integer level;

}
