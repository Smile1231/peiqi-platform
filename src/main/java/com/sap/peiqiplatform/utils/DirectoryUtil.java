package com.sap.peiqiplatform.utils;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

import static com.sap.peiqiplatform.constant.DirectoryConstants.*;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName DirectoryUtil.java
 * @Description TODO
 * @createTime 2022-08-20  00:15:00
 */
@Slf4j
public class DirectoryUtil {
    // /Users/jinmao/Documents/IDEASpace/Peiqi-platform
    public static String getProjectDir() {
        return System.getProperty("user.dir");
    }
    // /Users/jinmao/Documents/IDEASpace/Peiqi-platform/src/main/resources/upload/
    public static String getUploadPath(){
        return getProjectDir() + SLASH + RESOURCE_LOCAL_PATH + SLASH + UPLOAD_PATH;
    }
    // src/main/resources/upload/
    public static String getRelativeFilePath(){
        return RESOURCE_LOCAL_PATH + SLASH + UPLOAD_PATH;
    }
    // src/main/resources/upload/mtar/
    public static String getMtarRelativeFilePath(){
        return getRelativeFilePath() + MTAR_PATH;
    }
    // src/main/resources/upload/mtaext/
    public static String getMtaExtRelativeFilePath(){
        return getRelativeFilePath() + MTAEXT_PATH;
    }
    // upload/mtar/2022-08-21/file.txt
    public static String getMtarDownloadFileUrl(String fileUidName){
        return UPLOAD_PATH + MTAR_PATH + DateUtil.today() + SLASH + fileUidName;
    }
    // upload/mtaext/2022-08-21/file.txt
    public static String getMtaExtDownloadFileUrl(String fileUidName){
        return UPLOAD_PATH + MTAEXT_PATH + DateUtil.today() + SLASH + fileUidName;
    }
    // src/main/resources/upload/mtar/2022-08-21
    public static String getDailyMtarRelativeFilePath(){
        return getMtarRelativeFilePath() + DateUtil.today();
    }
    // src/main/resources/upload/mtaext/2022-08-21
    public static String getDailyMtaExtRelativeFilePath(){
        return getMtaExtRelativeFilePath() + DateUtil.today();
    }
    //  src/main/resources/ fileUrl
    public static String getRelativePathByFileUrl(String fileUrl){
        return RESOURCE_LOCAL_PATH+ SLASH + fileUrl;
    }
    // src/main/resources/deploy/
    public static String getDeployPath(){
        return RESOURCE_LOCAL_PATH + SLASH + DEPLOY_PATH;
    }
    // src/main/resources/deploy/2022-08-22
    public static String getDailyDeployRelativePath(){
        return getDeployPath() + DateUtil.today();
    }
    // src/main/resources/deploy/2022-08-22/32hj2n1j23n2j1k32n132j1
    public static String getRealDeployPathByUid(String uidDir){
        return getDailyDeployRelativePath() + SLASH + uidDir;
    }
    // src/main/resources/deploy/2022-08-22/32hj2n1j23n2j1k32n132j1/log.txt
    public static String getAllPathByPathAndName(String realUidPath, String fileName){
        return realUidPath + SLASH + fileName;
    }

    public static void checkDir(String directory){
        File folder = new File(directory);
        if (!folder.exists() && !folder.isDirectory()) {
            folder.mkdirs();
            LOGUtil.printLog(log,"make directory ... ");
        } else {
            LOGUtil.printLog(log,"directory is exist");
        }
    }

}
