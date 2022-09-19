package com.sap.peiqiplatform.check;

import cn.hutool.core.util.ObjectUtil;
import com.sap.peiqiplatform.exception.APIException;
import com.sap.peiqiplatform.nums.AppNameEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName ParamCheck.java
 * @Description TODO
 * @createTime 2022-08-27  14:57:00
 */
@Slf4j
public class ParamCheck {
    public static void checkAppNameExist(String appName){
        AppNameEnum appNameEnumByName = AppNameEnum.getAppNameEnumByName(appName);
        if (ObjectUtil.isEmpty(appNameEnumByName)){
            throw new APIException("application name is not exist , please check .");
        }
    }
}
