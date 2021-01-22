package com.csf.basedata.sammgt.request;

import lombok.Data;

import java.util.List;

/**
 * @author eric.yao
 * @date 2021/1/15
 **/
@Data
public class NewScmProductTreeCompanyRequest {

    private String productCode;

    private List<NewCompanyRequest> companies;

}
