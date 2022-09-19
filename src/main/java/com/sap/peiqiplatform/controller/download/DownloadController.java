package com.sap.peiqiplatform.controller.download;

import com.sap.peiqiplatform.nums.LogsEnum;
import com.sap.peiqiplatform.service.download.IDownloadService;
import com.sap.peiqiplatform.utils.LOGUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName DownloadController.java
 * @Description TODO
 * @createTime 2022-08-26  13:02:00
 */
@Slf4j
@RestController
@RequestMapping("/download")
public class DownloadController {
    @Autowired
    private IDownloadService downloadService;

    @PostMapping("/downloadMtar")
    public ResponseEntity<FileSystemResource> downloadMtarFile(){
        LOGUtil.printLog(log, LogsEnum.START_DOWNLOAD_MTAR_EXAMPLE_FILE.getLogDescription());
        return downloadService.getExampleFile();
    }
}
