package com.sap.peiqiplatform.nums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName FileTypeEnum.java
 * @Description TODO
 * @createTime 2022-08-19  22:15:00
 */
@Getter
@AllArgsConstructor
public enum FileTypeEnum {

    MTAR(0,"mtar"),
    EXTENSION(1,"mtaext"),
    EXAMPLE(8,"example")
    ;

    private final Integer code;
    private final String description;

    public static FileTypeEnum getFileTypeByCode(Integer fileType){
        return Arrays.stream(FileTypeEnum.values())
                .filter(f -> Objects.equals(fileType,f.getCode()))
                .findFirst().orElse(null);
    }

    public static FileTypeEnum getFileTypeByDes(String description){
        return Arrays.stream(FileTypeEnum.values())
                .filter(f -> Objects.equals(description,f.getDescription()))
                .findFirst().orElse(null);
    }
}
