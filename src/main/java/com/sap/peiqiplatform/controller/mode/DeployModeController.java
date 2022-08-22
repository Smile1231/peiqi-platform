package com.sap.peiqiplatform.controller.mode;

import com.sap.peiqiplatform.entity.dto.response.DeployModeRes;
import com.sap.peiqiplatform.entity.vo.ResultVO;
import com.sap.peiqiplatform.nums.LogsEnum;
import com.sap.peiqiplatform.service.mode.IDeployModeService;
import com.sap.peiqiplatform.utils.LOGUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName DeployModeController.java
 * @Description TODO
 * @createTime 2022-08-20  09:40:00
 */
@Slf4j
@RequestMapping("/mode")
@RestController
public class DeployModeController {

    @Autowired
    private IDeployModeService deployModeService;

    @GetMapping("/getModeCategory")
    public ResultVO<List<DeployModeRes>> getDeployMode(){
        LOGUtil.printLog( log, LogsEnum.START_GET_DEPLOY_MODE.getLogDescription());
        List<DeployModeRes> modeResList = deployModeService.getDeployModes();
        return new ResultVO<>(modeResList);
    }
}
