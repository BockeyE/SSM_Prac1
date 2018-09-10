package com.erp.dao.schedule;

import com.erp.bean.COrder;
import com.erp.bean.Custom;
import com.erp.bean.VO.COrderVO;
import com.erp.bean.example.COrderExample;
import com.github.pagehelper.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface COrderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(COrder record);

    int insertSelective(COrder record);

    List<COrderVO> findAll(COrderVO p);

    List<COrderVO> selectByExample(COrderExample example);

    COrderVO selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(COrder record);

    int updateByPrimaryKey(COrder record);

    List<COrderVO> searchOrderByCustomName(String custonName);

    List<COrderVO> searchOrderByProductName(String productName);

    List<COrderVO> searchOrderByOrderId(String orderId);
}