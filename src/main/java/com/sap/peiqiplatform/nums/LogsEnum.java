package com.sap.peiqiplatform.nums;

import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName LogsEnum.java
 * @Description TODO
 * @createTime 2022-08-21  13:38:00
 */
@Getter
@AllArgsConstructor
public enum LogsEnum {

    START_GET_DEPLOY_MODE("start get deploy mode "),
    START_UPLOAD_FILE("start upload file "),
    INSERT_DATA_SUCCESSFULLY("insert data successfully!"),
    START_BUILD_RESPONSE_BODY("start build response body! "),
    START_VOLUNTARY_DEPLOY("start voluntary deploy ..."),
    START_INSERT_INTO_DEPLOY_INFO("start insert into deploy record"),
    START_INSERT_DEPLOY_LOG_INFO("start insert into deploy log ."),
    START_EXECUTE_DEPLOY_VOLUNTARY("start execute deploy voluntary."),
    END_DEPLOY_VOLUNTARY("end deploy voluntary ."),
    START_DOWNLOAD_MTAR_EXAMPLE_FILE("start download mtar example file ."),
    START_GET_ALL_APPLICATION_NAMES("start to get all application names ."),
    START_GET_APP_VERSION_INFO("start get application version info list"),
    START_INVOLUNTARY_DEPLOY("start inVoluntary deploy ..."),
    END_DEPLOY_INVOLUNTARY("end deploy involuntary ."),

    START_EXECUTE_DEPLOY_INVOLUNTARY("start execute deploy involuntary.")


    ;


    private final String logDescription;
}
