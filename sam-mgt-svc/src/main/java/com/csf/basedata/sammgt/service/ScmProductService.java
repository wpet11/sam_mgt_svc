package com.csf.basedata.sammgt.service;

import com.csf.basedata.sammgt.domain.entity.samclient.ScmProduct;
import com.csf.basedata.sammgt.response.ScmProductTreeResp;

public interface ScmProductService {
    ScmProductTreeResp getScmProductTree();

    Integer updateProduct(ScmProduct request);
}
