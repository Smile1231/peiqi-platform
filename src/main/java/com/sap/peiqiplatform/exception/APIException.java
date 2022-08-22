package com.sap.peiqiplatform.exception;

import com.sap.peiqiplatform.nums.ResultCodeEnum;
import lombok.Getter;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName APIException.java
 * @Description TODO
 * @createTime 2022-08-22  10:12:00
 */
@Getter
public class APIException extends RuntimeException{
    private Integer code;
    private String msg;

    public APIException() {
        this(ResultCodeEnum.FAILED.getCode(), ResultCodeEnum.FAILED.getMsg());
    }

    public APIException(String msg) {
        this(ResultCodeEnum.FAILED.getCode(), msg);
    }
    public APIException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

}
