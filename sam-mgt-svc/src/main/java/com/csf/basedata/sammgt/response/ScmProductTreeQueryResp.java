package com.csf.basedata.sammgt.response;

import com.csf.basedata.sammgt.bo.ScmProductTreeBo;
import lombok.Data;

import java.util.List;

/**
 * Created by laurence.shi
 */
@Data
public class ScmProductTreeQueryResp {
    private List<ScmProductTreeBo> list;
    private int count;
}
