package com.sap.peiqiplatform.service.involuntary.impl;

import com.sap.peiqiplatform.entity.dto.request.InvoluntaryDeployEntity;
import com.sap.peiqiplatform.entity.dto.response.AppNameResponse;
import com.sap.peiqiplatform.service.involuntary.IInvoluntaryService;

import java.util.List;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName TestInterface.java
 * @Description TODO
 * @createTime 2022-09-12  09:23:00
 */
public class TestInterface implements IInvoluntaryService {
    @Override
    public AppNameResponse getApplicationName() {
        return null;
    }

    @Override
    public List<String> getVersionByAppName(String appName) {
        return null;
    }

    @Override
    public void submitDeployInvoluntary(InvoluntaryDeployEntity involuntaryDeployEntity) {

    }
}
