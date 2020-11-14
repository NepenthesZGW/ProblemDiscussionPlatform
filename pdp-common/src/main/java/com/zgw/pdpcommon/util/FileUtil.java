package com.zgw.pdpcommon.util;

import com.zgw.pdpcommon.filter.SuffixFilter;
import com.zgw.pdpcommon.filter.impl.ImageSuffixFilter;
import org.slf4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * @author 忘忧症
 * @Distribute
 */
public class FileUtil {

    private static final SuffixFilter imageSuffixFilter=new ImageSuffixFilter();


    public static boolean isImageSuffix(String filename){
        return imageSuffixFilter.accept(filename);
    }


    public static ByteArrayInputStream mapToInputStream(Map<String,String> map){
        StringBuilder  stringBuilder=new StringBuilder();

        map.forEach((o1,o2)->{
            stringBuilder.append(o1).append("=").append(o2).append("\n");
        });
        return new ByteArrayInputStream(stringBuilder.toString().getBytes());
    }


    public static void main(String[] args) {


        Logger asyncLogger = LogUtil.getAsyncLogger(FileUtil.class);
        Logger errorLogger = LogUtil.getErrorLogger();
        Logger warnLogger = LogUtil.getWarnLogger();


            errorLogger.error("哎");
//        warnLogger.warn("啊");
//        asyncLogger.info("哦");
    }
}
