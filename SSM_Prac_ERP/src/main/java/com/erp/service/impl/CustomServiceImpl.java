package com.erp.service.impl;

import com.erp.bean.Custom;
import com.erp.bean.Views.OperationResult;
import com.erp.bean.Views.ViewResult;
import com.erp.dao.schedule.CustomMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bockey
 */
@Service
public class CustomServiceImpl {
    @Autowired
    CustomMapper mapper;

    public ViewResult getList(Integer page, Integer rows, Custom vo) {
        ViewResult vs = new ViewResult();
        PageHelper.startPage(page, rows);
        List<Custom> list = mapper.findAll(vo);
        PageInfo<Custom> pageInfo = new PageInfo<>(list);
        vs.setRows(list);
        vs.setTotal(pageInfo.getTotal());
        return vs;
    }

    public List<Custom> getAll() {
        List<Custom> vos = mapper.findAll(null);
        return vos;
    }

    public Custom get(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    public OperationResult insert(Custom a) {
        int i = mapper.insert(a);
        if (i > 0) {
            return OperationResult.ok();
        } else {
            return OperationResult.build(101, "新增客戶失败");
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


    public OperationResult update(Custom a) throws Exception {
        int i = mapper.updateByPrimaryKeySelective(a);
        if (i > 0) {
            return OperationResult.ok();
        } else {
            return OperationResult.build(101, "修改信息失败");
        }
    }


    public OperationResult updateAll(Custom a) throws Exception {
        int i = mapper.updateByPrimaryKey(a);
        if (i > 0) {
            return OperationResult.ok();
        } else {
            return OperationResult.build(101, "修改信息失败");
        }
    }


    public ViewResult searchCustomByCustomId(Integer page, Integer rows, String searchValue) {
        PageHelper.startPage(page, rows);
        ViewResult vr = new ViewResult();
        Custom custom = mapper.selectByPrimaryKey(searchValue);
        ArrayList arr = new ArrayList();
        arr.add(custom);
        vr.setRows(arr);
        PageInfo<Custom> p = new PageInfo(arr);
        vr.setTotal(p.getTotal());
        return vr;
    }

    public ViewResult searchCustomByCustomName(Integer page, Integer rows, String searchValue) {
        PageHelper.startPage(page, rows);
        ViewResult vr = new ViewResult();
        List<Custom> arr = mapper.searchCustomByCustomName(searchValue);
        vr.setRows(arr);
        PageInfo<Custom> p = new PageInfo(arr);
        vr.setTotal(p.getTotal());
        return vr;
    }
}
