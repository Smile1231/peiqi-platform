package com.sap.peiqiplatform.controller.uploadfile;

import com.sap.peiqiplatform.entity.dto.response.UploadRes;
import com.sap.peiqiplatform.entity.vo.ResultVO;
import com.sap.peiqiplatform.nums.LogsEnum;
import com.sap.peiqiplatform.service.upload.IUploadService;
import com.sap.peiqiplatform.utils.LOGUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName UploadController.java
 * @Description TODO
 * @createTime 2022-08-21  13:24:00
 */
@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private IUploadService uploadService;

    @PostMapping("/uploadFile")
    public ResultVO<UploadRes> uploadFile(MultipartFile file){
        LOGUtil.printLog(log, LogsEnum.START_UPLOAD_FILE.getLogDescription());
        return uploadService.uploadFile(file);
    }
}
