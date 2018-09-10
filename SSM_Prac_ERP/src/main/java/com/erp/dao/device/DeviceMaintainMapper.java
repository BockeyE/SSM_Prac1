package com.erp.dao.device;

import com.erp.bean.DeviceMaintain;

import java.util.List;

public interface DeviceMaintainMapper {
    int deleteByPrimaryKey(String deviceMaintainId);

    int insert(DeviceMaintain record);

    int insertSelective(DeviceMaintain record);
    List<DeviceMaintain> findAll(DeviceMaintain p);
    DeviceMaintain selectByPrimaryKey(String deviceMaintainId);

    int updateByPrimaryKeySelective(DeviceMaintain record);

    int updateByPrimaryKey(DeviceMaintain record);
}