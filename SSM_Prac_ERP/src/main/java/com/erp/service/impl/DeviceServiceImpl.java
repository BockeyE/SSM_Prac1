package com.erp.service.impl;


import com.erp.bean.Device;
import com.erp.bean.Views.OperationResult;
import com.erp.bean.Views.ViewResult;
import com.erp.dao.device.DeviceMapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bockey
 */
@Service
public class DeviceServiceImpl {
    @Autowired
    DeviceMapper mapper;

    public ViewResult getList(Integer page, Integer rows, Device vo) {
        ViewResult vs = new ViewResult();
        PageHelper.startPage(page, rows);
        List<Device> list = mapper.findAll(vo);
        PageInfo<Device> pageInfo = new PageInfo<>(list);
        vs.setRows(list);
        vs.setTotal(pageInfo.getTotal());
        return vs;
    }

    public List<Device> getAll() {
        List<Device> vos = mapper.findAll(null);
        return vos;
    }

    public Device get(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    public OperationResult insert(Device a) {
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


    public OperationResult update(Device a) throws Exception {
        int i = mapper.updateByPrimaryKeySelective(a);
        if (i > 0) {
            return OperationResult.ok();
        } else {
            return OperationResult.build(101, "修改信息失败");
        }
    }


    public OperationResult updateAll(Device a) throws Exception {
        int i = mapper.updateByPrimaryKey(a);
        if (i > 0) {
            return OperationResult.ok();
        } else {
            return OperationResult.build(101, "修改信息失败");
        }
    }


}
