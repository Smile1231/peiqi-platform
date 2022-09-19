package com.sap.peiqiplatform.config;

import com.alibaba.fastjson.JSONObject;
import com.sap.peiqiplatform.entity.vo.ResultVO;
import com.sap.peiqiplatform.nums.ResultCodeEnum;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName GlobalExceptionHandler.java
 * @Description TODO
 * @createTime 2022-08-22  09:23:00
 */
@RestControllerAdvice(basePackages = {"com.sap.peiqiplatform.service","com.sap.peiqiplatform.check","com.sap.peiqiplatform.controller"})
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVO<Object> exceptionHandler(Exception e) {

        if (e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) e;
            // 从异常对象中拿到ObjectError对象
            ObjectError objectError = methodArgumentNotValidException.getBindingResult().getAllErrors().get(0);
            // 然后提取错误提示信息进行返回
            return new ResultVO<>(ResultCodeEnum.FAILED, objectError.getDefaultMessage());
        }
        String msg = e.getMessage();
        if (msg == null || msg.equals("")) {
            msg = ResultCodeEnum.ERROR.getMsg();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", msg);
        return new ResultVO<>(ResultCodeEnum.FAILED,jsonObject);
    }
}
