package com.erp.service.impl;


import com.erp.bean.DeviceMaintain;
import com.erp.bean.DeviceType;
import com.erp.bean.Views.OperationResult;
import com.erp.bean.Views.ViewResult;
import com.erp.dao.device.DeviceMaintainMapper;
import com.erp.dao.device.DeviceTypeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bockey
 */
@Service
public class DeviceMaintainServiceImpl {
    @Autowired
    DeviceMaintainMapper mapper;

    public ViewResult getList(Integer page, Integer rows, DeviceMaintain vo) {
        ViewResult vs = new ViewResult();
        PageHelper.startPage(page, rows);
        List<DeviceMaintain> list = mapper.findAll(vo);
        PageInfo<DeviceMaintain> pageInfo = new PageInfo<>(list);
        vs.setRows(list);
        vs.setTotal(pageInfo.getTotal());
        return vs;
    }

    public List<DeviceMaintain> getAll() {
        List<DeviceMaintain> vos = mapper.findAll(null);
        return vos;
    }

    public DeviceMaintain get(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    public OperationResult insert(DeviceMaintain a) {
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


    public OperationResult update(DeviceMaintain a) throws Exception {
        int i = mapper.updateByPrimaryKeySelective(a);
        if (i > 0) {
            return OperationResult.ok();
        } else {
            return OperationResult.build(101, "修改信息失败");
        }
    }


    public OperationResult updateAll(DeviceMaintain a) throws Exception {
        int i = mapper.updateByPrimaryKey(a);
        if (i > 0) {
            return OperationResult.ok();
        } else {
            return OperationResult.build(101, "修改信息失败");
        }
    }


}
