package com.csf.basedata.sammgt.domain.entity.audit;

import lombok.Data;

/**
 * @Classname LogNodeInfo
 * @Description
 * @Date 2019/12/26 11:05
 * @Created by eden.fang
 */
@Data
public class LogNodeInfo {

    private String code;

    private String nameZh;

    private String nameEn;

    public LogNodeInfo(){

    }

    public LogNodeInfo(String code){
       this.code = code;
    }

    public LogNodeInfo(String code, String nameZh){
        this.code = code;
        this.nameZh = nameZh;
    }

    public LogNodeInfo(String code, String nameZh, String nameEn){
        this.code = code;
        this.nameZh = nameZh;
        this.nameEn = nameEn;
    }


}
