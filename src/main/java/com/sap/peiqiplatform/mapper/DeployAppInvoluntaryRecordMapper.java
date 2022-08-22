package com.sap.peiqiplatform.mapper;

import com.sap.peiqiplatform.entity.po.DeployAppInvoluntaryRecord;

public interface DeployAppInvoluntaryRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DeployAppInvoluntaryRecord record);

    int insertSelective(DeployAppInvoluntaryRecord record);

    DeployAppInvoluntaryRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeployAppInvoluntaryRecord record);

    int updateByPrimaryKey(DeployAppInvoluntaryRecord record);
}