package com.zhengjianbin.generatecode.util;

import java.io.File;

/**
 * Created by zhengjianbin on 2019/8/29.
 */
public class FileUtils {

    public static void checkAndMkdir(String path){
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
    }

}
