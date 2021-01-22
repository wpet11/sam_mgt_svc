package com.csf.basedata.sammgt.domain.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author eric.yao
 * @date 2021/1/13
 **/
public class JacksonUtils {

    public static ObjectMapper getObjectMapper(){
        return ObjectMapperHolder.mapper;
    }

    private static class ObjectMapperHolder{
        static ObjectMapper mapper = new ObjectMapper();
        static {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        }
    }

}
