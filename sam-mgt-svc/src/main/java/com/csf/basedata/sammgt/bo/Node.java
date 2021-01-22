package com.csf.basedata.sammgt.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author allen.jin
 * @date 2020/1/6
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Node {
    private String nameZh;
    private String nameEn;
    private String productCode;
    private int dsCnt;
    private int usCnt;

    public Node(String nameZh, String nameEn, String productCode) {
        this.nameZh = nameZh;
        this.nameEn = nameEn;
        this.productCode = productCode;
    }

}
