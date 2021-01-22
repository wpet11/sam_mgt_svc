package com.csf.basedata.sammgt.bo;

import com.csf.basedata.sammgt.common.enums.Publisher;
import lombok.Data;

/**
 * @author allen.jin
 * @date 2019/12/23
 */

@Data
public class ScmProductStreamInfo {
    private String upstreamCode;
    private String downStreamCode;
    private Integer weight;
    private Integer direction;
    private String upstreamType;
    private String downstreamType;
    private Publisher publisher;
}
