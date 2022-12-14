package com.sap.peiqiplatform.service.voluntary.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.sap.peiqiplatform.check.FileVerify;
import com.sap.peiqiplatform.entity.bo.ShellProperties;
import com.sap.peiqiplatform.entity.dto.request.VoluntaryDeployEntity;
import com.sap.peiqiplatform.entity.dto.response.UploadRes;
import com.sap.peiqiplatform.entity.po.DeployAppLog;
import com.sap.peiqiplatform.entity.po.DeployAppVoluntaryRecord;
import com.sap.peiqiplatform.mapper.DeployAppLogMapper;
import com.sap.peiqiplatform.mapper.DeployAppVoluntaryRecordMapper;
import com.sap.peiqiplatform.nums.DeployStatusEnum;
import com.sap.peiqiplatform.nums.LogsEnum;
import com.sap.peiqiplatform.nums.UploadModeEnum;
import com.sap.peiqiplatform.service.voluntary.IVoluntarySubmitDeployService;
import com.sap.peiqiplatform.utils.DirectoryUtil;
import com.sap.peiqiplatform.utils.ExecuteShellUtil;
import com.sap.peiqiplatform.utils.LOGUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.sap.peiqiplatform.constant.CommonConstants.*;
import static com.sap.peiqiplatform.nums.LogsEnum.START_INSERT_INTO_DEPLOY_INFO;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName SubmitDeployServiceImpl.java
 * @Description TODO
 * @createTime 2022-08-22  09:38:00
 */
@Slf4j
@Service
public class VoluntarySubmitDeployServiceImpl implements IVoluntarySubmitDeployService {
    @Autowired
    private DeployAppVoluntaryRecordMapper deployAppVoluntaryRecordMapper;
    @Autowired
    private ExecuteShellUtil executeShellUtil;
    @Autowired
    private ShellProperties shellProperties;
    @Autowired
    private DeployAppLogMapper deployAppLogMapper;

    @Override
    public void submitDeployAction(VoluntaryDeployEntity voluntaryDeployEntity) throws InterruptedException {
        FileVerify.checkFileListNum(voluntaryDeployEntity);

        UploadRes extensionInfo = voluntaryDeployEntity.getExtensionFileList().get(ZERO);
        UploadRes mtarInfo = voluntaryDeployEntity.getMtarFileList().get(ZERO);

        String extensionFileUrl = extensionInfo.getFileUrl();
        String mtarFileUrl = mtarInfo.getFileUrl();
        FileVerify.checkFileExist(extensionFileUrl);
        FileVerify.checkFileExist(mtarFileUrl);

        LOGUtil.printLog(log, START_INSERT_INTO_DEPLOY_INFO.getLogDescription());

        DeployAppVoluntaryRecord voluntaryRecord = new DeployAppVoluntaryRecord();
        voluntaryRecord.setMtarUploadFlag(UploadModeEnum.VOLUNTARY_UPLOAD.getCode());
        voluntaryRecord.setMtarFileUid(mtarInfo.getFileUid());
        voluntaryRecord.setExtensionFileUid(extensionInfo.fileUid);
        voluntaryRecord.setApiEndPoint(voluntaryDeployEntity.getApiEndPoint());
        voluntaryRecord.setOrgName(voluntaryDeployEntity.getOrgName());
        voluntaryRecord.setSpaceName(voluntaryDeployEntity.getSpaceName());
        voluntaryRecord.setDeployAccount(DEFAULT_DEPLOY_ACCOUNT);
        voluntaryRecord.setCreateTime(new Date());

        int id = deployAppVoluntaryRecordMapper.insertSelective(voluntaryRecord);

        String uidDir = IdUtil.simpleUUID();
        String dailyDeployRelativePath = DirectoryUtil.getRealDeployPathByUid(uidDir);
        DirectoryUtil.checkDir(dailyDeployRelativePath);

        String logFilePath = DirectoryUtil.getAllPathByPathAndName(dailyDeployRelativePath, shellProperties.getLogFileName());
        // core logic
        executeShellUtil.deployVoluntary(voluntaryRecord,logFilePath);

        LOGUtil.printLog(log, LogsEnum.START_INSERT_DEPLOY_LOG_INFO.getLogDescription());
        DeployAppLog deployAppLog = new DeployAppLog();
        deployAppLog.setDeployRecordId(id);
        deployAppLog.setStatus(DeployStatusEnum.SUCCESS.getCode());
        deployAppLog.setLogInfo(DeployStatusEnum.SUCCESS.getDescription());
        deployAppLog.setLogFilePath(logFilePath);
        // cal time minus
        long deployTakeTime = DateUtil.between(voluntaryRecord.getCreateTime(), new Date(), DateUnit.MINUTE);
        deployAppLog.setTakeTime(String.valueOf(deployTakeTime));

        deployAppLogMapper.insertSelective(deployAppLog);

        LOGUtil.printLog(log,LogsEnum.END_DEPLOY_VOLUNTARY.getLogDescription());
    }
}
