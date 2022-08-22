package com.sap.peiqiplatform.mapper;

import com.sap.peiqiplatform.entity.po.DeployAppVoluntaryRecord;

/**
* @author jinmao
* @description 针对表【deploy_app_voluntary_record】的数据库操作Mapper
* @createDate 2022-08-22 23:11:33
* @Entity com.sap.peiqiplatform.entity.po.DeployAppVoluntaryRecord
*/
public interface DeployAppVoluntaryRecordMapper {

    int deleteByPrimaryKey(Long id);

    int insert(DeployAppVoluntaryRecord record);

    int insertSelective(DeployAppVoluntaryRecord record);

    DeployAppVoluntaryRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeployAppVoluntaryRecord record);

    int updateByPrimaryKey(DeployAppVoluntaryRecord record);

}
