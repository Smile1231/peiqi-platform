package com.sap.peiqiplatform.utils;

import org.slf4j.Logger;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName LOGUTIL.java
 * @Description TODO log format util
 * @createTime 2022-08-21  13:28:00
 */
public class LOGUtil {
    public static void printLog(Logger logger,String str){
        logger.info("###### {} #####" , str);
    }

}
