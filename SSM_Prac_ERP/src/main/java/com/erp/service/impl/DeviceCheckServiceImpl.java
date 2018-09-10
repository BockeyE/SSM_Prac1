package com.erp.service.impl;


import com.erp.bean.DeviceCheck;
import com.erp.bean.DeviceFault;
import com.erp.bean.Views.OperationResult;
import com.erp.bean.Views.ViewResult;
import com.erp.dao.device.DeviceCheckMapper;
import com.erp.dao.device.DeviceFaultMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bockey
 */
@Service
public class DeviceCheckServiceImpl {
    @Autowired
    DeviceCheckMapper mapper;

    public ViewResult getList(Integer page, Integer rows, DeviceCheck vo) {
        ViewResult vs = new ViewResult();
        PageHelper.startPage(page, rows);
        List<DeviceCheck> list = mapper.findAll(vo);
        PageInfo<DeviceCheck> pageInfo = new PageInfo<>(list);
        vs.setRows(list);
        vs.setTotal(pageInfo.getTotal());
        return vs;
    }

    public List<DeviceCheck> getAll() {
        List<DeviceCheck> vos = mapper.findAll(null);
        return vos;
    }

    public DeviceCheck get(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    public OperationResult insert(DeviceCheck a) {
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


    public OperationResult update(DeviceCheck a) throws Exception {
        int i = mapper.updateByPrimaryKeySelective(a);
        if (i > 0) {
            return OperationResult.ok();
        } else {
            return OperationResult.build(101, "修改信息失败");
        }
    }


    public OperationResult updateAll(DeviceCheck a) throws Exception {
        int i = mapper.updateByPrimaryKey(a);
        if (i > 0) {
            return OperationResult.ok();
        } else {
            return OperationResult.build(101, "修改信息失败");
        }
    }


}
