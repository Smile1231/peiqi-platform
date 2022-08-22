package com.sap.peiqiplatform.nums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName DeployStatusEnum.java
 * @Description TODO
 * @createTime 2022-08-19  17:49:00
 */
@Getter
@AllArgsConstructor
public enum DeployStatusEnum {

    SUCCESS(0,"success"),
    FAILED(1,"failed")

    ;

    private final Integer code;
    private final String description;

    public static DeployStatusEnum getDeployStatusEnumByStatus(Integer status){
        return Arrays.stream(DeployStatusEnum.values())
                .filter(f -> Objects.equals(f.getCode(),status))
                .findFirst().orElse(null);
    }
}
