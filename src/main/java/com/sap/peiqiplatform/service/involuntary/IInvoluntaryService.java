package com.sap.peiqiplatform.service.involuntary;

import com.sap.peiqiplatform.entity.dto.request.InvoluntaryDeployEntity;
import com.sap.peiqiplatform.entity.dto.response.AppNameResponse;

import java.util.List;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName IInvoluntaryService.java
 * @Description TODO
 * @createTime 2022-08-27  10:53:00
 */
public interface IInvoluntaryService {
    AppNameResponse getApplicationName();

    List<String> getVersionByAppName(String appName);

    void submitDeployInvoluntary(InvoluntaryDeployEntity involuntaryDeployEntity);
}
