package com.sap.peiqiplatform.controller.voluntary;

import com.sap.peiqiplatform.entity.dto.request.VoluntaryDeployEntity;
import com.sap.peiqiplatform.entity.vo.ResultVO;
import com.sap.peiqiplatform.nums.ResultCodeEnum;
import com.sap.peiqiplatform.service.voluntary.IVoluntarySubmitDeployService;
import com.sap.peiqiplatform.utils.LOGUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.sap.peiqiplatform.nums.LogsEnum.START_VOLUNTARY_DEPLOY;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName SubmitDeployController.java
 * @Description TODO
 * @createTime 2022-08-22  08:51:00
 */
@Slf4j
@RestController
@RequestMapping("/voluntary")
public class SubmitDeployController {

    @Autowired
    private IVoluntarySubmitDeployService submitDeployService;

    @PostMapping("/submit")
    public ResultVO<Object> submitVoluntaryDeploy(@RequestBody  @Valid VoluntaryDeployEntity voluntaryDeployEntity) throws InterruptedException {
        LOGUtil.printLog(log,START_VOLUNTARY_DEPLOY.getLogDescription());
        submitDeployService.submitDeployAction(voluntaryDeployEntity);
        return new ResultVO<>(ResultCodeEnum.SUCCESS);
    }
}
