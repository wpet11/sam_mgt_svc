package com.csf.basedata.sammgt.controller;

import com.csf.basedata.sammgt.domain.entity.samclient.ScmProduct;
import com.csf.basedata.sammgt.service.ScmProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/client/product")
public class ScmProductController {

    @Resource
    private ScmProductService scmProductService;

    /**
     * @Description: 客户产品树
     */
    @GetMapping
    public ResponseEntity getAllProduct() {
        return ResponseEntity.ok(scmProductService.getScmProductTree());
    }

    /**
     *编辑产品
     */
    @PutMapping
    public ResponseEntity updateProduct(@RequestBody ScmProduct request) {
        return ResponseEntity.ok(scmProductService.updateProduct(request));
    }
}
