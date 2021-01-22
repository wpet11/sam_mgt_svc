package com.csf.basedata.sammgt.bo;

import lombok.Data;

/**
 * @author allen.jin
 * @date 2020/1/10
 */

@Data
public class ScmInitCount {
    private Integer productsCount;
    private Integer mappingsCount;
    private Integer streamsCount;
    private Integer initInfoCount;
    private Integer productTreesCount;
}
