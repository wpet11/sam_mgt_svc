package com.csf.basedata.sammgt.controller;

import com.csf.basedata.sammgt.domain.entity.clerk.Clerk;
import com.csf.basedata.sammgt.service.ClerkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/client/clerk")
public class ClerkController {

    @Resource
    private ClerkService clerkService;

    /**
     * 查询所有职员信息
     */
    @GetMapping
    public ResponseEntity getClerk() {
        return ResponseEntity.ok(clerkService.getClerk());
    }

    @PutMapping
    public ResponseEntity updateClerk(@RequestBody Clerk Clerk){
        return ResponseEntity.ok(clerkService.updateClerk(Clerk));
    }
}
