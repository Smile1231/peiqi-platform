package com.sap.peiqiplatform.service.mode.impl;

import com.sap.peiqiplatform.entity.dto.response.DeployModeRes;
import com.sap.peiqiplatform.nums.UploadModeEnum;
import com.sap.peiqiplatform.service.mode.IDeployModeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName DeployModeServiceImpl.java
 * @Description TODO
 * @createTime 2022-08-20  10:29:00
 */
@Service
public class DeployModeServiceImpl implements IDeployModeService {
    @Override
    public List<DeployModeRes> getDeployModes() {
        UploadModeEnum[] uploadModeEnum = UploadModeEnum.values();
        ArrayList<DeployModeRes> modeResList = new ArrayList<>();
        Arrays.stream(uploadModeEnum)
                .forEach(f -> {
                    DeployModeRes modeRes = new DeployModeRes();
                            modeRes.setId(f.getCode());
                            modeRes.setModeDescription(f.getDescription());
                    modeResList.add(modeRes);
                });
        return modeResList;
    }
}
