package com.sap.peiqiplatform.utils.abs;

import com.sap.peiqiplatform.entity.po.DeployAppInvoluntaryRecord;
import com.sap.peiqiplatform.entity.po.DeployAppVoluntaryRecord;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName ExecuteResourceTemplate.java
 * @Description TODO
 * @createTime 2022-08-28  11:10:00
 */
public abstract class ExecuteResourceTemplate {
    public abstract void deployVoluntary(DeployAppVoluntaryRecord deployAppVoluntaryRecord, String logFilePath) throws InterruptedException;

    public abstract void deployInVoluntary(DeployAppInvoluntaryRecord deployAppInvoluntaryRecord,String dailyDeployRelativePath, String logFilePath);
}