package com.sap.peiqiplatform.mapper;

import com.sap.peiqiplatform.entity.po.AppTable;

import java.util.List;

public interface AppTableMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppTable record);

    int insertSelective(AppTable record);

    AppTable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppTable record);

    int updateByPrimaryKey(AppTable record);

    void bulkInsertAppTable(List<AppTable> appTableList);
}