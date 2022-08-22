package com.sap.peiqiplatform.service.upload.impl;

import cn.hutool.core.util.IdUtil;
import com.sap.peiqiplatform.check.FileVerify;
import com.sap.peiqiplatform.entity.bo.ParamCheckVo;
import com.sap.peiqiplatform.entity.dto.response.UploadRes;
import com.sap.peiqiplatform.entity.po.UploadFileInfo;
import com.sap.peiqiplatform.entity.vo.ResultVO;
import com.sap.peiqiplatform.mapper.UploadFileInfoMapper;
import com.sap.peiqiplatform.nums.FileTypeEnum;
import com.sap.peiqiplatform.nums.ResultCodeEnum;
import com.sap.peiqiplatform.service.upload.IUploadService;
import com.sap.peiqiplatform.utils.DirectoryUtil;
import com.sap.peiqiplatform.utils.FileUtil;
import com.sap.peiqiplatform.utils.LOGUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

import static com.sap.peiqiplatform.constant.DirectoryConstants.*;
import static com.sap.peiqiplatform.nums.LogsEnum.INSERT_DATA_SUCCESSFULLY;
import static com.sap.peiqiplatform.nums.LogsEnum.START_BUILD_RESPONSE_BODY;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName UploadServiceImpl.java
 * @Description TODO
 * @createTime 2022-08-21  13:48:00
 */
@Slf4j
@Service
public class UploadServiceImpl implements IUploadService {

    @Autowired
    private UploadFileInfoMapper uploadFileInfoMapper;

    @Override
    public ResultVO<UploadRes> uploadFile(MultipartFile file) {
        String fileSuffix = FileUtil.getFileSuffix(Objects.requireNonNull(file.getOriginalFilename()));
        ParamCheckVo paramCheckVo = FileVerify.checkUploadFile(file);
        if (paramCheckVo.isMistaken()){
            return new ResultVO<>(ResultCodeEnum.FAILED.getCode(),paramCheckVo.getCheckMsg(),null);
        }
        if(Objects.equals(fileSuffix, MTAEXT)){
            return uploadMTAEXTFile(file,fileSuffix);
        }
        return uploadMTARFile(file,fileSuffix);
    }

    private ResultVO<UploadRes> uploadMTAEXTFile(MultipartFile file,String fileSuffix){
        //origin file name
        String originalFilename = file.getOriginalFilename();
        //generate uid for upload file
        String fileUid = IdUtil.simpleUUID();
        String fileUidName = FileUtil.jointNewFileName(fileUid, fileSuffix);
        LOGUtil.printLog(log,String.format("the new fileUidName is %s", fileUidName));
        String downFileUrl = "";
        try {
            // get upload file path
            String uploadFilePath = DirectoryUtil.getDailyMtaExtRelativeFilePath();
            DirectoryUtil.checkDir(uploadFilePath);
            File uploadFile = new File(uploadFilePath + SLASH + fileUidName);
            //upload
            Files.copy(file.getInputStream(),uploadFile.toPath());
            // get File Url
            downFileUrl = DirectoryUtil.getMtaExtDownloadFileUrl(fileUidName);
        } catch (IOException e) {
            log.error("transfer file error , the log is {}", e.getMessage());
            return new ResultVO<UploadRes>(ResultCodeEnum.ERROR);
        }
        insertUploadFileAction(fileSuffix,fileUid,originalFilename,downFileUrl);
        return buildUploadRes(fileUid,originalFilename,downFileUrl);
    }

    private ResultVO<UploadRes> uploadMTARFile(MultipartFile file,String fileSuffix){
        //origin file name
        String originalFilename = file.getOriginalFilename();
        //generate uid for upload file
        String fileUid = IdUtil.simpleUUID();
        String fileUidName = FileUtil.jointNewFileName(fileUid, fileSuffix);
        LOGUtil.printLog(log,String.format("the new fileUidName is %s", fileUidName));
        String downFileUrl = "";
        try {
            // get upload file path
            String uploadFilePath = DirectoryUtil.getDailyMtarRelativeFilePath();
            DirectoryUtil.checkDir(uploadFilePath);
            File uploadFile = new File(uploadFilePath + SLASH + fileUidName);
            //upload
            Files.copy(file.getInputStream(),uploadFile.toPath());
            // get File Url
            downFileUrl = DirectoryUtil.getMtarDownloadFileUrl(fileUidName);
        } catch (IOException e) {
            log.error("transfer file error , the log is {}", e.getMessage());
            return new ResultVO<UploadRes>(ResultCodeEnum.ERROR);
        }
        insertUploadFileAction(fileSuffix,fileUid,originalFilename,downFileUrl);
        return buildUploadRes(fileUid,originalFilename,downFileUrl);
    }


    private void insertUploadFileAction(String fileSuffix,String fileUid,String originalFilename,String downFileUrl){
        UploadFileInfo uploadFileInfo = new UploadFileInfo();
        uploadFileInfo.setFileType(FileTypeEnum.getFileTypeByDes(fileSuffix).getCode());
        uploadFileInfo.setFileUid(fileUid);
        uploadFileInfo.setFileName(originalFilename);
        uploadFileInfo.setFileUrl(downFileUrl);
        uploadFileInfoMapper.insertSelective(uploadFileInfo);
        LOGUtil.printLog(log,INSERT_DATA_SUCCESSFULLY.getLogDescription());
    }

    public ResultVO<UploadRes> buildUploadRes(String fileUid,String originalFilename,String downFileUrl){
        LOGUtil.printLog(log,START_BUILD_RESPONSE_BODY.getLogDescription());
        UploadRes uploadRes = new UploadRes();
        uploadRes.setFileUid(fileUid);
        uploadRes.setFileName(originalFilename);
        uploadRes.setFileUrl(downFileUrl);
        return new ResultVO<>(uploadRes);
    }


}
