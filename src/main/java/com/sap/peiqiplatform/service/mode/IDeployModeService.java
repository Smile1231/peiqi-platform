package com.sap.peiqiplatform.service.mode;

import com.sap.peiqiplatform.entity.dto.response.DeployModeRes;

import java.util.List;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName DeployModeService.java
 * @Description TODO
 * @createTime 2022-08-20  10:26:00
 */
public interface IDeployModeService {

    List<DeployModeRes> getDeployModes();
}
