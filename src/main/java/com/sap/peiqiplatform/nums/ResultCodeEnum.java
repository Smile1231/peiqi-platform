package com.sap.peiqiplatform.nums;

import lombok.Getter;

/**
 * @author cy
 * @create 2022-05-18-22:21
 * @Description 接口返回代码规范
 */
@Getter
public enum ResultCodeEnum {

    OK(0,"Verify success"),

    FAILED(-100,"Interface call failed"),

    PARAM_VERIFY_FAILED(-101, "param verify failed"),

    SUCCESS(200, "Interface call succeeded"),

    ERROR(300, "Server internal error"),

    UNAUTHORIZED(401,"The user name or password is incorrect"),

    NOT_FOUND(404,"The service does not exist or is offline"),

    FORBIDDEN(403,"Access denied due to insufficient permissions"),

    TIMEOUT(504,"Visit the timeout"),

    NOT_EXIST(999,"When this code appears, there may be a bug")
    ;


    private Integer code;
    private String msg;

    ResultCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}

