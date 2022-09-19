package com.sap.peiqiplatform.entity.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.peiqiplatform.entity.dto.request.base.DeployEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName InvoluntaryDeployEntity.java
 * @Description TODO
 * @createTime 2022-08-27  16:00:00
 */
@Data
public class InvoluntaryDeployEntity extends DeployEntity {
    @JsonProperty("applicationName")
    @NotBlank(message = "application name can not be null")
    private String applicationName;

    @JsonProperty("appVersion")
    @NotBlank(message = "app version can not be null")
    private String appVersion;

    @JsonProperty("mtaExtText")
    private String mtaExtText;
}
