package com.erp.service.impl;

import com.erp.bean.COrder;
import com.erp.bean.VO.COrderVO;
import com.erp.bean.Views.OperationResult;
import com.erp.bean.Views.ViewResult;
import com.erp.dao.schedule.COrderMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bockey
 */
@Service
public class OrderServiceImpl {

    @Autowired
    COrderMapper mapper;

    public ViewResult getList(int page, int rows, COrderVO vo) {
        PageHelper.startPage(page, rows);
        List<COrderVO> vos = mapper.findAll(vo);
        //取记录总条数
        PageInfo<COrderVO> pageInfo = new PageInfo<>(vos);
        ViewResult vr = new ViewResult();
        vr.setTotal(pageInfo.getTotal());
        vr.setRows(vos);
        return vr;
    }


    public List<COrderVO> getAll() {
        List<COrderVO> vos = mapper.findAll(null);
        return vos;
    }


    public COrderVO get(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    public OperationResult insert(COrder cOrder) {
        int i = mapper.insert(cOrder);
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
    public OperationResult update(COrder a) throws Exception{
        int i = mapper.updateByPrimaryKeySelective(a);
        if(i>0){
            return OperationResult.ok();
        }else{
            return OperationResult.build(101, "修改信息失败");
        }
    }


    public OperationResult updateAll(COrder a) throws Exception{
        int i = mapper.updateByPrimaryKey(a);
        if(i>0){
            return OperationResult.ok();
        }else{
            return OperationResult.build(101, "修改信息失败");
        }
    }




    public ViewResult searchOrderByOrderId(int page, int rows, String orderId) throws Exception{
        //分页处理
        PageHelper.startPage(page, rows);
        List<COrderVO> list = mapper.searchOrderByOrderId(orderId);
        //创建一个返回值对象
        ViewResult result = new ViewResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<COrderVO> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }


    public ViewResult searchOrderByCustomName(int page, int rows, String custonName)
            throws Exception{
        //分页处理
        PageHelper.startPage(page, rows);
        List<COrderVO> list = mapper.searchOrderByCustomName(custonName);
        //创建一个返回值对象
        ViewResult result = new ViewResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<COrderVO> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }


    public ViewResult searchOrderByProductName(int page, int rows, String productName)
            throws Exception{
        //分页处理
        PageHelper.startPage(page, rows);
        List<COrderVO> list = mapper.searchOrderByProductName(productName);
        //创建一个返回值对象
        ViewResult result = new ViewResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<COrderVO> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }




}
