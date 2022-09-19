package com.sap.peiqiplatform.service.download;

import com.sap.peiqiplatform.check.DBResultCheck;
import com.sap.peiqiplatform.constant.CommonConstants;
import com.sap.peiqiplatform.constant.HttpConstants;
import com.sap.peiqiplatform.entity.po.UploadFileInfo;
import com.sap.peiqiplatform.mapper.UploadFileInfoMapper;
import com.sap.peiqiplatform.nums.FileTypeEnum;
import com.sap.peiqiplatform.utils.DirectoryUtil;
import com.sap.peiqiplatform.utils.HttpUtil;
import com.sap.peiqiplatform.utils.LOGUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName DownloadServiceImpl.java
 * @Description TODO
 * @createTime 2022-08-26  13:06:00
 */
@Slf4j
@Service
public class DownloadServiceImpl implements IDownloadService{
    @Autowired
    private UploadFileInfoMapper uploadFileInfoMapper;

    @Override
    public ResponseEntity<FileSystemResource> getExampleFile() {
        List<UploadFileInfo> uploadFileList = uploadFileInfoMapper.getUploadFileByType(FileTypeEnum.EXAMPLE.getCode());
        DBResultCheck.checkGetExampleFile(uploadFileList);

        UploadFileInfo mtarFileInfo = uploadFileList.get(CommonConstants.ZERO);
        String exampleRelativePath = DirectoryUtil.getRelativePathByFileUrl(mtarFileInfo.getFileUrl());

        LOGUtil.printLog(log,String.format("download mtar example file , the relative path is %s",exampleRelativePath));

        File file = new File(exampleRelativePath);
        HttpHeaders headers = HttpUtil.getInputStreamHeader(file);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(HttpConstants.APPLICATION_OCTET_STREAM))
                .body(new FileSystemResource(file));
    }
}
