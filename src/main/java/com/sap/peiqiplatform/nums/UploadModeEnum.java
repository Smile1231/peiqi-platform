package com.sap.peiqiplatform.nums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName UploadModeEnums.java
 * @Description TODO
 * @createTime 2022-08-19  17:22:00
 */
@Getter
@AllArgsConstructor
public enum UploadModeEnum {

    INVOLUNTARY_UPLOAD(0,"Involuntary upload"),
    VOLUNTARY_UPLOAD(1,"voluntary upload")

    ;

    private final Integer code;
    private final String description;

    public static UploadModeEnum getUploadArrEnumsByCode(Integer mtarUploadFlag){
        return Arrays.stream(UploadModeEnum.values())
                .filter(f -> Objects.equals(f.getCode(),mtarUploadFlag))
                .findFirst().orElse(null);
    }
}
