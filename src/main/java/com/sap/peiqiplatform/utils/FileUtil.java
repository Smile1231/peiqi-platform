package com.sap.peiqiplatform.utils;

import com.sap.peiqiplatform.constant.CommonConstants;

import java.util.Objects;

import static com.sap.peiqiplatform.constant.DirectoryConstants.*;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName FileUtil.java
 * @Description TODO
 * @createTime 2022-08-21  14:27:00
 */
public class FileUtil {

    public static String getFileSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf(".") + CommonConstants.ONE);
    }
    public static Long byte2MB(long size){
        return  size / 1024 / 1024;
    }

    public static String jointNewFileName(String fileUid , String fileSuffix){
        return fileUid + POINT + fileSuffix;
    }

}
