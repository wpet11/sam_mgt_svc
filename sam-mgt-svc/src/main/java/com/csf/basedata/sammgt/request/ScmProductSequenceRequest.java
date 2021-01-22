package com.csf.basedata.sammgt.request;

import com.csf.basedata.sammgt.bo.ScmProductSequenceBo;
import lombok.Data;

import java.util.List;

/**
 * @author laurence.shi
 */
@Data
public class ScmProductSequenceRequest {
    private List<ScmProductSequenceBo> products;
}
