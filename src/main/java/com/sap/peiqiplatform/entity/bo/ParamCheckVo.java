package com.sap.peiqiplatform.entity.bo;

import com.sap.peiqiplatform.nums.ResultCodeEnum;
import lombok.Data;
import lombok.Getter;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName ParamCheckVo.java
 * @Description TODO
 * @createTime 2022-08-20  10:21:00
 */
@Data
public class ParamCheckVo {
    //false 验证通过
    private boolean mistaken;
    private String checkMsg;

    public static ParamCheckVo checkPass(){
        ParamCheckVo paramCheckVo = new ParamCheckVo();
        paramCheckVo.setMistaken(false);
        paramCheckVo.setCheckMsg(ResultCodeEnum.OK.getMsg());
        return paramCheckVo;
    }
}
