package com.erp.dao.device;

import com.erp.bean.DeviceType;

import java.util.List;

public interface DeviceTypeMapper {
    int deleteByPrimaryKey(String deviceTypeId);

    int insert(DeviceType record);
    List<DeviceType> findAll(DeviceType p);
    int insertSelective(DeviceType record);

    DeviceType selectByPrimaryKey(String deviceTypeId);

    int updateByPrimaryKeySelective(DeviceType record);

    int updateByPrimaryKey(DeviceType record);
}