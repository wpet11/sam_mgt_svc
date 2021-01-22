package com.csf.basedata.sammgt.response;

import com.csf.basedata.sammgt.bo.ScmProductBaseInfo;
import lombok.Data;

import java.util.List;

/**
 * Created by bob.huang on 2019/12/3
 */
@Data
public class ScmProductCreationResp {

    private String code;
    private String name;
    private List<ScmProductBaseInfo> mappings;
    private boolean mapped;

    @Data
    public static class CsfNode {
        private String code;
        private String name;
        private Integer level;
    }
}
