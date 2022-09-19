package com.sap.peiqiplatform.utils;

import org.springframework.http.HttpHeaders;

import java.io.File;
import java.util.Date;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName HttpUtil.java
 * @Description TODO
 * @createTime 2022-08-26  14:58:00
 */
public class HttpUtil {
    public static HttpHeaders getInputStreamHeader(File file){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));

        return headers;
    }
}
