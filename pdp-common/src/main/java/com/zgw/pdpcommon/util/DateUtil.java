package com.zgw.pdpcommon.util;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author 忘忧症
 * @Distribute
 */
public class DateUtil {

    private static final DateTimeFormatter FORMAT_NINETEEN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static String getCurrentDate(){
       return FORMAT_NINETEEN.format(LocalDateTime.now());
    }

    public static String getCurrentDateTime(){
        return FORMAT_DATE.format(LocalDateTime.now());
    }

}
