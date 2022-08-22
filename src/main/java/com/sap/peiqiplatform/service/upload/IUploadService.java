package com.sap.peiqiplatform.service.upload;

import com.sap.peiqiplatform.entity.dto.response.UploadRes;
import com.sap.peiqiplatform.entity.vo.ResultVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName IuploadService.java
 * @Description TODO
 * @createTime 2022-08-21  13:47:00
 */
public interface IUploadService {

    ResultVO<UploadRes> uploadFile(MultipartFile file);
}
