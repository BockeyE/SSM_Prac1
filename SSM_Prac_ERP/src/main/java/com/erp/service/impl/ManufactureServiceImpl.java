package com.erp.service.impl;

import com.erp.bean.Custom;
import com.erp.bean.Manufacture;
import com.erp.bean.VO.ManufactureVO;
import com.erp.bean.Views.OperationResult;
import com.erp.bean.Views.ViewResult;
import com.erp.dao.schedule.CustomMapper;
import com.erp.dao.schedule.ManufactureMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bockey
 */
@Service
public class ManufactureServiceImpl {
    @Autowired
    ManufactureMapper mapper;

    public ViewResult getList(Integer page, Integer rows, ManufactureVO vo) {
        ViewResult vs = new ViewResult();
        PageHelper.startPage(page, rows);
        List<ManufactureVO> list = mapper.findAll(vo);
        PageInfo<ManufactureVO> pageInfo = new PageInfo<>(list);
        vs.setRows(list);
        vs.setTotal(pageInfo.getTotal());
        return vs;
    }
    public List<ManufactureVO> getAll() {
        List<ManufactureVO> vos = mapper.findAll(null);
        return vos;
    }
    public ManufactureVO get(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    public OperationResult insert(Manufacture a)   {
        int i = mapper.insert(a);
        if (i > 0) {
            return OperationResult.ok();
        } else {
            return OperationResult.build(101, "新增订单失败");
        }
    }
    public OperationResult delete(String id) throws Exception {
        int i = mapper.deleteByPrimaryKey(id);
        if (i > 0) {
            return OperationResult.ok();
        } else {
            return null;
        }
    }


    public OperationResult deleteBatch(String[] ids) throws Exception {
        int i = 0;
        for (String id : ids) {
            i += mapper.deleteByPrimaryKey(id);
        }
        if (i > 0) {
            return OperationResult.ok();
        } else {
            return null;
        }
    }
    public OperationResult update(Manufacture a) throws Exception{
        int i = mapper.updateByPrimaryKeySelective(a);
        if(i>0){
            return OperationResult.ok();
        }else{
            return OperationResult.build(101, "修改信息失败");
        }
    }


    public OperationResult updateAll(Manufacture a) throws Exception{
        int i = mapper.updateByPrimaryKey(a);
        if(i>0){
            return OperationResult.ok();
        }else{
            return OperationResult.build(101, "修改信息失败");
        }
    }
}
