package com.sap.peiqiplatform.service.voluntary;

import com.sap.peiqiplatform.entity.dto.request.VoluntaryDeployEntity;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName ISubmitDeployService.java
 * @Description TODO
 * @createTime 2022-08-22  09:36:00
 */
public interface IVoluntarySubmitDeployService {

    void submitDeployAction(VoluntaryDeployEntity voluntaryDeployEntity);
}
