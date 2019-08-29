package com.zhengjianbin.generatecode.util;

/**
 * Created by zhengjianbin on 2019/8/29.
 */
public class StringUtils {

    public static String convertJavaField(String fieldName){
        if(!fieldName.contains("_")){
            return fieldName;
        }
        return  "";
    }

}
