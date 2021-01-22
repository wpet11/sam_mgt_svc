package com.csf.basedata.sammgt.response;

import com.csf.basedata.sammgt.bo.CompanyBo;
import lombok.Data;

import java.util.List;

/**
 * @Author lei.zhao_ext
 * @Date 2021/1/15
 */
@Data
public class AttriTableCompanyQueryResp {
    private List<CompanyBo> companies;
}
