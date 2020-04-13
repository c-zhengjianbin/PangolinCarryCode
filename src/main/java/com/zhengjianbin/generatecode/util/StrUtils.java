package com.zhengjianbin.generatecode.util;

import com.zhengjianbin.generatecode.exception.PangolinCarryException;

/**
 * Created by zhengjianbin on 2019/9/12.
 */
public class StrUtils {

    /**
     * @author : zhengjianbin
     * @version: 1.0
     * @time : 2019/9/12 - 1:50 PM
     * @Param :
     * @function : 将Mysql 表名转换为Java 类名，例如：admin_user convert AdminUser
     */
    public static String tableConvertJavaClassName(String tableName){
        System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder();
        if(!tableName.contains("_")){
            char[] chars = tableName.toCharArray();
            chars[0] = Character.toUpperCase(tableName.charAt(0));
            stringBuilder.append(chars);
        }else{
            String[] split = tableName.split("_");
            for(String splitStr : split){
                char[] chars = splitStr.toCharArray();
                chars[0] = Character.toUpperCase(splitStr.charAt(0));
                stringBuilder.append(chars);
            }
        }
        return stringBuilder.toString();
    }

    /**
     * @author : zhengjianbin
     * @version: 1.0
     * @time : 2019/9/12 - 2:29 PM
     * @Param :
     * @function : 将类名转换为变量名，例如：AdminUser convert adminUser
     */
    public static String javaClassNameConvertVariate(String clsName){
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = clsName.toCharArray();
        chars[0] = Character.toLowerCase(clsName.charAt(0));
        stringBuilder.append(chars);
        return stringBuilder.toString();
    }

}
