package com.sap.peiqiplatform.service.involuntary.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.sap.peiqiplatform.check.FileVerify;
import com.sap.peiqiplatform.check.ParamCheck;
import com.sap.peiqiplatform.constant.CommonConstants;
import com.sap.peiqiplatform.entity.bo.ApplicationProperties;
import com.sap.peiqiplatform.entity.bo.ShellProperties;
import com.sap.peiqiplatform.entity.dto.request.InvoluntaryDeployEntity;
import com.sap.peiqiplatform.entity.dto.response.AppNameResponse;
import com.sap.peiqiplatform.entity.dto.response.UploadRes;
import com.sap.peiqiplatform.entity.po.DeployAppInvoluntaryRecord;
import com.sap.peiqiplatform.entity.po.DeployAppLog;
import com.sap.peiqiplatform.exception.APIException;
import com.sap.peiqiplatform.mapper.DeployAppInvoluntaryRecordMapper;
import com.sap.peiqiplatform.mapper.DeployAppLogMapper;
import com.sap.peiqiplatform.nums.AppNameEnum;
import com.sap.peiqiplatform.nums.DeployStatusEnum;
import com.sap.peiqiplatform.nums.LogsEnum;
import com.sap.peiqiplatform.nums.UploadModeEnum;
import com.sap.peiqiplatform.service.involuntary.IInvoluntaryService;
import com.sap.peiqiplatform.utils.DirectoryUtil;
import com.sap.peiqiplatform.utils.ExecuteShellUtil;
import com.sap.peiqiplatform.utils.GetInternetResourceUtil;
import com.sap.peiqiplatform.utils.LOGUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.sap.peiqiplatform.nums.LogsEnum.START_INSERT_INTO_DEPLOY_INFO;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName InvoluntaryServiceImpl.java
 * @Description TODO
 * @createTime 2022-08-27  11:01:00
 */
@Slf4j
@Service
public class InvoluntaryServiceImpl implements IInvoluntaryService {

    @Autowired
    private GetInternetResourceUtil getInternetResourceUtil;
    @Autowired
    private DeployAppInvoluntaryRecordMapper deployAppInvoluntaryRecordMapper;
    @Autowired
    private ShellProperties shellProperties;
    @Autowired
    private ExecuteShellUtil executeShellUtil;
    @Autowired
    private DeployAppLogMapper deployAppLogMapper;

    @Override
    public AppNameResponse getApplicationName() {
        HashMap<Integer, String> appNameHashMap = AppNameEnum.getAppNameHashMap();
        ArrayList<ApplicationProperties> responseArrayList = new ArrayList<>();

        for (Map.Entry<Integer, String> entry : appNameHashMap.entrySet()) {
            ApplicationProperties applicationProperties = new ApplicationProperties(entry.getKey(), entry.getValue());
            responseArrayList.add(applicationProperties);
        }
        return AppNameResponse.builder().appNameList(responseArrayList).build();
    }

    @Override
    public List<String> getVersionByAppName(String appName) {
        ParamCheck.checkAppNameExist(appName);
        if (Objects.equals(AppNameEnum.SERVER.getDescription(),appName)){
            return getInternetResourceUtil.getServerInfo();
        }else if (Objects.equals(AppNameEnum.IA_CLIENT_NODE.getDescription(),appName)){
            return getInternetResourceUtil.getICAClientNodeInfo();
        }else if (Objects.equals(AppNameEnum.WORKSPACE.getDescription(),appName)){
            return getInternetResourceUtil.getWorkSpaceInfo();
        }
        throw new APIException("appName not found , plz check .");
    }

    @Override
    public void submitDeployInvoluntary(InvoluntaryDeployEntity involuntaryDeployEntity) {
        FileVerify.checkFileListNum(involuntaryDeployEntity);

        UploadRes extensionFileInfo = involuntaryDeployEntity.getExtensionFileList().get(CommonConstants.ZERO);

        String fileUrl = extensionFileInfo.getFileUrl();
        FileVerify.checkFileExist(fileUrl);

        LOGUtil.printLog(log, START_INSERT_INTO_DEPLOY_INFO.getLogDescription());

        DeployAppInvoluntaryRecord deployAppInvoluntaryRecord = new DeployAppInvoluntaryRecord();

        deployAppInvoluntaryRecord.setMtarUploadFlag(UploadModeEnum.INVOLUNTARY_UPLOAD.getCode());
        deployAppInvoluntaryRecord.setApplicationName(involuntaryDeployEntity.getApplicationName());
        deployAppInvoluntaryRecord.setApiEndPoint(involuntaryDeployEntity.getApiEndPoint());
        deployAppInvoluntaryRecord.setOrgName(involuntaryDeployEntity.getOrgName());
        deployAppInvoluntaryRecord.setSpaceName(involuntaryDeployEntity.getSpaceName());
        deployAppInvoluntaryRecord.setAppVersion(involuntaryDeployEntity.getAppVersion());
        deployAppInvoluntaryRecord.setExtensionFileUid(extensionFileInfo.getFileUid());
        deployAppInvoluntaryRecord.setDeployAccount(CommonConstants.DEFAULT_DEPLOY_ACCOUNT_PASSWORD);
        deployAppInvoluntaryRecord.setCreateTime(new Date());

        int id = deployAppInvoluntaryRecordMapper.insertSelective(deployAppInvoluntaryRecord);

        String uidDir = IdUtil.simpleUUID();
        String dailyDeployRelativePath = DirectoryUtil.getRealDeployPathByUid(uidDir);
        DirectoryUtil.checkDir(dailyDeployRelativePath);

        String logFilePath = DirectoryUtil.getAllPathByPathAndName(dailyDeployRelativePath, shellProperties.getLogFileName());
        // core logic
        executeShellUtil.deployInVoluntary(deployAppInvoluntaryRecord,dailyDeployRelativePath,logFilePath);

        LOGUtil.printLog(log, LogsEnum.START_INSERT_DEPLOY_LOG_INFO.getLogDescription());
        DeployAppLog deployAppLog = new DeployAppLog();
        deployAppLog.setDeployRecordId(id);
        deployAppLog.setStatus(DeployStatusEnum.SUCCESS.getCode());
        deployAppLog.setLogInfo(DeployStatusEnum.SUCCESS.getDescription());
        deployAppLog.setLogFilePath(logFilePath);
        // cal time minus
        long deployTakeTime = DateUtil.between(deployAppInvoluntaryRecord.getCreateTime(), new Date(), DateUnit.MINUTE);
        deployAppLog.setTakeTime(String.valueOf(deployTakeTime));

        deployAppLogMapper.insertSelective(deployAppLog);

        LOGUtil.printLog(log,LogsEnum.END_DEPLOY_INVOLUNTARY.getLogDescription());
    }
}
