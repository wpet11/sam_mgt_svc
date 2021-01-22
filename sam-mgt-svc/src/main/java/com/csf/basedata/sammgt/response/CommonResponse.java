package com.csf.basedata.sammgt.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author eric.yao
 * @date 2021/1/15
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {

    private T data;

}
