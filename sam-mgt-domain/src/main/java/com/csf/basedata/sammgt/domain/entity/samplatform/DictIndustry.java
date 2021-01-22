package com.csf.basedata.sammgt.domain.entity.samplatform;

import lombok.Data;

import java.util.Date;

/**
 * @author allen.jin
 * @date 2019/12/5
 */

@Data
public class DictIndustry {
    private String code;
    private String name;
    private String nameEn;
    private String parent;
    private String ancestors;
    private Date updatedTime;
}
