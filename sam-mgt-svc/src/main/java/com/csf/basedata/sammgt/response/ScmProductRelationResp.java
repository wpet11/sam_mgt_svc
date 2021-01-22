package com.csf.basedata.sammgt.response;

import com.csf.basedata.sammgt.bo.ScmProductRelationBo;
import lombok.Data;

import java.util.List;

/**
 * Created by laurence.shi
 */
@Data
public class ScmProductRelationResp {
    private List<ScmProductRelationBo> list;
}
