package com.sap.peiqiplatform.utils;

import com.sap.peiqiplatform.entity.bo.ShellProperties;
import com.sap.peiqiplatform.entity.po.DeployAppInvoluntaryRecord;
import com.sap.peiqiplatform.entity.po.DeployAppVoluntaryRecord;
import com.sap.peiqiplatform.entity.po.UploadFileInfo;
import com.sap.peiqiplatform.exception.APIException;
import com.sap.peiqiplatform.mapper.UploadFileInfoMapper;
import com.sap.peiqiplatform.nums.LogsEnum;
import com.sap.peiqiplatform.utils.abs.ExecuteResourceTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.sap.peiqiplatform.constant.CommonConstants.DEFAULT_DEPLOY_ACCOUNT_PASSWORD;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName ExecuteShellUtil.java
 * @Description TODO
 * @createTime 2022-08-22  23:52:00
 */
@Slf4j
@Component
public class ExecuteShellUtil extends ExecuteResourceTemplate {

    final String CHMOD_777 = "chmod 777 ";
    final String POINT_SLASH = "./";
    @Autowired
    private ShellProperties shellProperties;
    @Autowired
    private UploadFileInfoMapper uploadFileInfoMapper;
    @Autowired
    private GetInternetResourceUtil getInternetResourceUtil;

    @Override
    public void deployVoluntary(DeployAppVoluntaryRecord deployAppVoluntaryRecord, String logFilePath) throws InterruptedException {
        LOGUtil.printLog(log, LogsEnum.START_EXECUTE_DEPLOY_VOLUNTARY.getLogDescription());

        String deployDirectory = shellProperties.getDeployFileDirectory();
        UploadFileInfo mtarFileInfo = uploadFileInfoMapper.getUploadFileByUid(deployAppVoluntaryRecord.getMtarFileUid());
        UploadFileInfo extensionFileInfo = uploadFileInfoMapper.getUploadFileByUid(deployAppVoluntaryRecord.getExtensionFileUid());
        Process process = null;
        try {
            ProcessBuilder pb = new ProcessBuilder();
            pb.directory(new File(DirectoryUtil.getRelativePathByFileUrl(deployDirectory)));
            pb.command(CHMOD_777 + shellProperties.getDeployFileName());
            pb.command(POINT_SLASH + shellProperties.getDeployFileName()
                    , deployAppVoluntaryRecord.getApiEndPoint()
                    , deployAppVoluntaryRecord.getOrgName()
                    , deployAppVoluntaryRecord.getSpaceName()
                    , deployAppVoluntaryRecord.getDeployAccount()
                    , DEFAULT_DEPLOY_ACCOUNT_PASSWORD
                    , mtarFileInfo.getFileUrl()
                    , extensionFileInfo.getFileUrl()
            );
            pb.redirectErrorStream(true);
            process = pb.start();
            writeToLocal(logFilePath,process.getInputStream());
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        int runningStatus = 0;
        try {
            runningStatus = process.waitFor();
        } catch (InterruptedException e) {
            log.error("###### run shell zip script occurs error, error is {} ######", e.getMessage());
            throw new InterruptedException(e.getMessage());
        }
        if(runningStatus != 0) {
            log.error("###### run shell zip script failed.###### ");
            throw new APIException("deploy action failed ... ");
        }else {
            log.info("###### run deploy action success. ######");
        }
    }

    @Override
    public void deployInVoluntary(DeployAppInvoluntaryRecord deployAppInvoluntaryRecord, String dailyDeployRelativePath, String logFilePath) {
        LOGUtil.printLog(log,LogsEnum.START_EXECUTE_DEPLOY_INVOLUNTARY.getLogDescription());
        //download mtar file
        getInternetResourceUtil.downloadFileByAppNameAndVersion(deployAppInvoluntaryRecord.getApplicationName()
                ,deployAppInvoluntaryRecord.getAppVersion()
                , dailyDeployRelativePath
        );


        String deployFileDirectory = shellProperties.getDeployFileDirectory();
        UploadFileInfo extensionFileInfo = uploadFileInfoMapper.getUploadFileByUid(deployAppInvoluntaryRecord.getExtensionFileUid());

    }


    private void writeToLocal(String destination, InputStream input)
            throws IOException {
        int index;
        byte[] bytes = new byte[1024];
        FileOutputStream downloadFile = new FileOutputStream(destination);
        while ((index = input.read(bytes)) != -1) {
            downloadFile.write(bytes, 0, index);
            downloadFile.flush();
        }
        downloadFile.close();
        input.close();
    }
}
