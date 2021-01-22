package com.csf.basedata.sammgt.service.impl;

import com.csf.basedata.sammgt.domain.dao.samclient.ScmProductMapper;
import com.csf.basedata.sammgt.domain.entity.samclient.ScmProduct;
import com.csf.basedata.sammgt.response.ScmProductTreeResp;
import com.csf.basedata.sammgt.service.ScmProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class ScmProductServiceImpl implements ScmProductService {
    @Resource
    private ScmProductMapper scmProductMapper;

    @Override
    public ScmProductTreeResp getScmProductTree() {
        List<ScmProduct> allProducts = scmProductMapper.getScmProductTree();
        ScmProductTreeResp resp = new ScmProductTreeResp();
        resp.setProductNodes(allProducts);
        return resp;
    }

    @Override
    public Integer updateProduct(ScmProduct request) {
        Integer n = scmProductMapper.updateProduct(request);
        return n;
    }

}
