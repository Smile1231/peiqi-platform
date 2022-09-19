package com.sap.peiqiplatform.service.download;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName IDownloadService.java
 * @Description TODO
 * @createTime 2022-08-26  13:06:00
 */
public interface IDownloadService {
    ResponseEntity<FileSystemResource> getExampleFile();
}
