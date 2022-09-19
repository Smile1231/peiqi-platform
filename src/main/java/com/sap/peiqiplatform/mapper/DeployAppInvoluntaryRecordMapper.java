package com.sap.peiqiplatform.mapper;

import com.sap.peiqiplatform.entity.po.DeployAppInvoluntaryRecord;

/**
* @author jinmao
* @description 针对表【deploy_app_involuntary_record】的数据库操作Mapper
* @createDate 2022-08-28 10:48:48
* @Entity com.sap.peiqiplatform.entity.po.DeployAppInvoluntaryRecord
*/
public interface DeployAppInvoluntaryRecordMapper {

    int deleteByPrimaryKey(Long id);

    int insert(DeployAppInvoluntaryRecord record);

    int insertSelective(DeployAppInvoluntaryRecord record);

    DeployAppInvoluntaryRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeployAppInvoluntaryRecord record);

    int updateByPrimaryKey(DeployAppInvoluntaryRecord record);

}
