package com.csf.basedata.sammgt.service.impl;

import com.csf.basedata.sammgt.domain.dao.samplatform.DictIndustryMapper;
import com.csf.basedata.sammgt.domain.entity.clerk.Clerk;
import com.csf.basedata.sammgt.service.ClerkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class ClerkServiceImpl implements ClerkService {
    @Resource
    private DictIndustryMapper dictIndustryMapper;


    @Override
    public List<Clerk> getClerk() {
        List<Clerk> list = dictIndustryMapper.getAllClerk();
        return list;
    }

    @Override
    public Integer updateClerk(Clerk clerk) {
        Integer n = dictIndustryMapper.updateClerk(clerk);
        return n;
    }
}
