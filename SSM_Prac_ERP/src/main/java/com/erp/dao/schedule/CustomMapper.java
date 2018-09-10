package com.erp.dao.schedule;

import com.erp.bean.Custom;
import com.erp.bean.example.CustomExample;

import java.util.HashMap;
import java.util.List;

public interface CustomMapper {
    int deleteByPrimaryKey(String customId);

    int insert(Custom record);

    int insertSelective(Custom record);

    List<Custom> selectByExample(CustomExample example);
    List<Custom> findAll(Custom a);
    Custom selectByPrimaryKey(String customId);

    int updateByPrimaryKeySelective(Custom record);

    int updateByPrimaryKey(Custom record);

    List<Custom> searchCustomByCustomName(String searchValue);
}