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

    START_INSERT_INTO_DEPLOY_INFO("start insert into deploy record")

    ;


    private final String logDescription;
}
