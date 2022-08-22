package com.sap.peiqiplatform.entity.vo;

import com.baomidou.mybatisplus.extension.api.R;
import com.sap.peiqiplatform.entity.builder.Builder;
import com.sap.peiqiplatform.nums.ResultCodeEnum;
import lombok.Data;
import org.checkerframework.common.returnsreceiver.qual.This;

import java.util.Objects;

/**
 * @author cy
 * @create 2022-05-18-22:18
 * @Description 统一消息返回体
 */
@Data
public class ResultVO<T> {

    /**
     * 状态码，比如200代表响应成功
     */
    private final Integer code;
    /**
     * 响应信息，用来说明响应情况
     */
    private final String msg;
    /**
     * 响应的具体数据
     */
    private final T data;

    //构造返回 --无参
    public ResultVO(ResultCodeEnum code){
        this(code,null);
    }

    //构造返回成功参数--有参
    public ResultVO(T data) {
        this(ResultCodeEnum.SUCCESS, data);
    }

    public ResultVO(ResultCodeEnum resultCodeEnum, T data) {
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMsg();
        this.data = data;
    }

    public ResultVO(Integer code, String msg , T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

//    public static ResultVOBuilder builder() {
//        return new ResultVOBuilder();
//    }
//    public static class ResultVOBuilder{
//        private Integer code;
//        private String msg;
//        private Object data;
//
//        public ResultVOBuilder codeAndMsg(final ResultCodeEnum resultCodeEnum){
//            this.code = resultCodeEnum.getCode();
//            this.msg = resultCodeEnum.getMsg();
//            return this;
//        }
//        public ResultVOBuilder data(final Object data){
//            this.data = data;
//            return this;
//        }
//        public ResultVO<Object> build(){
//            return new ResultVO<Object>(this.code,this.msg,this.data);
//        }
//
//        public ResultVOBuilder buildResultWithSuccessAndData(Object data){
//            this.code = ResultCodeEnum.SUCCESS.getCode();
//            this.msg = ResultCodeEnum.SUCCESS.getMsg();
//            this.data = data;
//            return this;
//        }
//
//        public ResultVOBuilder buildResultWithResultEnumAndData(final ResultCodeEnum resultCodeEnum, Object data){
//            this.code = resultCodeEnum.getCode();
//            this.msg = resultCodeEnum.getMsg();
//            this.data = data;
//            return this;
//        }
//
//        @Override
//        public String toString() {
//            return "ResultVO.ResultVOBuilder{" +
//                    "code=" + this.code +
//                    ", msg='" + this.msg + '\'' +
//                    ", data=" + this.data +
//                    '}';
//        }
//    }

}

