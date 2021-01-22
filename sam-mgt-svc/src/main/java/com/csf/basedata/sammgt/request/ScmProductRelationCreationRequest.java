package com.csf.basedata.sammgt.request;

import com.csf.basedata.sammgt.bo.ScmProductRelationBo;
import lombok.Data;

import java.util.List;

/**
 * Created by laurence.shi
 */
@Data
public class ScmProductRelationCreationRequest extends ScmProductRelationBaseRequest {
    private List<ScmProductRelationBo> relations;
    private String parent;
}
