package com.sap.peiqiplatform.mapper;

import com.sap.peiqiplatform.entity.po.DeployAppLog;

/**
* @author jinmao
* @description 针对表【deploy_app_log】的数据库操作Mapper
* @createDate 2022-08-25 09:25:16
* @Entity com.sap.peiqiplatform.entity.po.DeployAppLog
*/
public interface DeployAppLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(DeployAppLog record);

    int insertSelective(DeployAppLog record);

    DeployAppLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeployAppLog record);

    int updateByPrimaryKey(DeployAppLog record);

}
