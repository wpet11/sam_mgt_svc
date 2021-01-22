package com.csf.basedata.sammgt.response;

import com.csf.basedata.sammgt.bo.CSVFilePath;
import lombok.Data;

/**
 * @author allen.jin
 * @date 2020/1/10
 */

@Data
public class DownLoadSamResp {
    CSVFilePath paths;
}
