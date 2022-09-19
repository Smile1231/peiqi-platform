package com.sap.peiqiplatform.controller.involuntary;

import com.sap.peiqiplatform.entity.dto.request.InvoluntaryDeployEntity;
import com.sap.peiqiplatform.entity.dto.response.AppNameResponse;
import com.sap.peiqiplatform.entity.vo.ResultVO;
import com.sap.peiqiplatform.nums.LogsEnum;
import com.sap.peiqiplatform.nums.ResultCodeEnum;
import com.sap.peiqiplatform.service.involuntary.IInvoluntaryService;
import com.sap.peiqiplatform.utils.LOGUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName InvoluntaryController.java
 * @Description TODO
 * @createTime 2022-08-27  10:12:00
 */
@Slf4j
@RestController
@RequestMapping("/involuntary")
public class InvoluntaryController {
    @Autowired
    private IInvoluntaryService involuntaryService;

    @GetMapping("/getAllAppName")
    public ResultVO<AppNameResponse> getApplicationNameController(){
        LOGUtil.printLog(log, LogsEnum.START_GET_ALL_APPLICATION_NAMES.getLogDescription());
        AppNameResponse appNameResponse = involuntaryService.getApplicationName();
        return new ResultVO<>(appNameResponse);
    }

    @GetMapping("/getVersion/{appName}")
    public ResultVO<List<String>> getApplicationVersionController(@PathVariable("appName") String appName){
        LOGUtil.printLog(log,LogsEnum.START_GET_APP_VERSION_INFO.getLogDescription());
        return new ResultVO<>(involuntaryService.getVersionByAppName(appName));
    }

    @PostMapping("/submit")
    public ResultVO<Object> submitInvoluntaryDeployController(@RequestBody @Valid InvoluntaryDeployEntity involuntaryDeployEntity){
        LOGUtil.printLog(log,LogsEnum.START_INVOLUNTARY_DEPLOY.getLogDescription());
        involuntaryService.submitDeployInvoluntary(involuntaryDeployEntity);
        return new ResultVO<>(ResultCodeEnum.SUCCESS);
    }

}
