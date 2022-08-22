package com.sap.peiqiplatform.entity.dto.response;

import lombok.Data;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName UploadRes.java
 * @Description TODO
 * @createTime 2022-08-21  13:35:00
 */
@Data
public class UploadRes {
    public String fileUid;
    public String fileName;
    public String fileUrl;
}
