package com.erp.dao.schedule;

import com.erp.bean.VO.WorkVO;
import com.erp.bean.Work;

import java.util.List;

public interface WorkMapper {
    int deleteByPrimaryKey(String workId);

    int insert(Work record);

    int insertSelective(Work record);

    WorkVO selectByPrimaryKey(String workId);
    List<WorkVO> findAll(WorkVO p);
    int updateByPrimaryKeySelective(Work record);

    int updateByPrimaryKey(Work record);
}