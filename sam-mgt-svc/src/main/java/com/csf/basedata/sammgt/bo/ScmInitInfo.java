package com.csf.basedata.sammgt.bo;

import com.csf.basedata.sammgt.common.enums.Publisher;
import lombok.Data;

import java.util.List;

/**
 * @author allen.jin
 * @date 2020/1/10
 */

@Data
public class ScmInitInfo {
    private ScmInitCount deleteCount;
    private ScmInitCount insertCount;
    private List<String> errors;
    private Publisher publisher;
}
