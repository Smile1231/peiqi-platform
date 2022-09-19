package com.sap.peiqiplatform.nums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName AppNameEnum.java
 * @Description TODO
 * @createTime 2022-08-27  10:29:00
 */
@Getter
public enum AppNameEnum {
    IA_CLIENT_NODE(1,"IAClientNode"),
    SERVER(2,"Server"),
    WORKSPACE(3,"WorkSpace")

    ;
    private final Integer code;
    private final String description;

    AppNameEnum(Integer code, String description){
        this.code = code;
        this.description = description;
    }

    private static final HashMap<Integer, String> appNamesMap = new HashMap<>();

    static {
        Arrays.stream(AppNameEnum.values())
                .forEach(f -> {
                    appNamesMap.put(f.getCode(),f.getDescription());
                });
    }

    public static HashMap<Integer, String> getAppNameHashMap(){
        return appNamesMap;
    }

    public static AppNameEnum getAppNameEnumByName(String appName){
        return Arrays.stream(AppNameEnum.values())
                .filter(f -> Objects.equals(appName,f.getDescription()))
                .findFirst().orElse(null);
    }
}
