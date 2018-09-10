package com.erp.dao.schedule;

import com.erp.bean.Manufacture;
import com.erp.bean.VO.ManufactureVO;

import java.util.List;

public interface ManufactureMapper {
    int deleteByPrimaryKey(String manufactureSn);

    int insert(Manufacture record);

    int insertSelective(Manufacture record);

    List<ManufactureVO> findAll(ManufactureVO p);

    ManufactureVO selectByPrimaryKey(String manufactureSn);

    int updateByPrimaryKeySelective(Manufacture record);

    int updateByPrimaryKey(Manufacture record);
}