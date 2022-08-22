package com.sap.peiqiplatform.mapper;

import com.sap.peiqiplatform.entity.po.UploadFileInfo;

/**
* @author jinmao
* @description 针对表【upload_file_info】的数据库操作Mapper
* @createDate 2022-08-21 14:53:16
* @Entity com.sap.peiqiplatform.entity.po.UploadFileInfo
*/
public interface UploadFileInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UploadFileInfo record);

    int insertSelective(UploadFileInfo record);

    UploadFileInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UploadFileInfo record);

    int updateByPrimaryKey(UploadFileInfo record);

}
