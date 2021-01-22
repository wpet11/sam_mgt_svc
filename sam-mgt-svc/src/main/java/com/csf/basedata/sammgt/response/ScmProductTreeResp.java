package com.csf.basedata.sammgt.response;

import lombok.Data;

import java.util.List;

/**
 * @author allen.jin
 * @date 2019/12/3
 */

@Data
public class ScmProductTreeResp<T> {
    private List<T> productNodes;
}
