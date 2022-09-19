package com.sap.peiqiplatform.entity.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.peiqiplatform.entity.dto.request.base.DeployEntity;
import com.sap.peiqiplatform.entity.dto.response.UploadRes;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName VoluntaryDeployEntity.java
 * @Description TODO
 * @createTime 2022-08-22  09:01:00
 */
@Data
public class VoluntaryDeployEntity extends DeployEntity {

    @NotNull(message = "matr file can not be null")
    @JsonProperty("mtarFileList")
    private ArrayList<UploadRes> mtarFileList;

}
