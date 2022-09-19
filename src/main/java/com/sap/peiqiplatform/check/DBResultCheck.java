package com.sap.peiqiplatform.check;

import com.sap.peiqiplatform.constant.CommonConstants;
import com.sap.peiqiplatform.entity.po.UploadFileInfo;
import com.sap.peiqiplatform.exception.APIException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName SelectResultCheck.java
 * @Description TODO
 * @createTime 2022-08-26  13:19:00
 */
@Slf4j
public class DBResultCheck {
    public static void checkGetExampleFile(List list) {
        if (!Objects.equals(list.size(), CommonConstants.ONE)){
            log.error("get example file list error , the example file num is {}",list.size());
            throw new APIException("get example file list error , the num is multi or zero.");
        }
    }
}
