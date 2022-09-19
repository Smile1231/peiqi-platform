package com.sap.peiqiplatform.entity.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.peiqiplatform.entity.bo.ApplicationProperties;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName AppNameResponse.java
 * @Description TODO
 * @createTime 2022-08-27  10:54:00
 */
@Builder
public class AppNameResponse {
    @JsonProperty("appNameList")
    private List<ApplicationProperties> appNameList;
}

