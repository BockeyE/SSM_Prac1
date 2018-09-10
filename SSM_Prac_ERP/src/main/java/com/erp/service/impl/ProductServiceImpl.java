package com.erp.service.impl;

import com.erp.bean.Product;
import com.erp.bean.Views.OperationResult;
import com.erp.bean.Views.ViewResult;

import com.erp.dao.schedule.ProductMapper;
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
public class ProductServiceImpl {
    @Autowired
    ProductMapper mapper;

    public ViewResult getList(Integer page, Integer rows, Product vo) {
        ViewResult vs = new ViewResult();
        PageHelper.startPage(page, rows);
        List<Product> list = mapper.findAll(vo);
        PageInfo<Product> pageInfo = new PageInfo<>(list);
        vs.setRows(list);
        vs.setTotal(pageInfo.getTotal());
        return vs;
    }

    public List<Product> getAll() {
        List<Product> vos = mapper.findAll(null);
        return vos;
    }

    public Product get(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    public OperationResult insert(Product product) {
        int i = mapper.insert(product);
        if (i > 0) {
            return OperationResult.ok();
        } else {
            return OperationResult.build(101, "新增商品失败");
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

    public OperationResult update(Product a) throws Exception {
        int i = mapper.updateByPrimaryKeySelective(a);
        if (i > 0) {
            return OperationResult.ok();
        } else {
            return OperationResult.build(101, "修改信息失败");
        }
    }


    public OperationResult updateAll(Product a) throws Exception {
        int i = mapper.updateByPrimaryKey(a);
        if (i > 0) {
            return OperationResult.ok();
        } else {
            return OperationResult.build(101, "修改信息失败");
        }
    }

    public ViewResult searchProductByProductId(Integer page, Integer rows, String searchValue) {
        Product product = mapper.selectByPrimaryKey(searchValue);
        ArrayList arr = new ArrayList();
        arr.add(product);
        ViewResult vr = new ViewResult();
        vr.setTotal(1);
        vr.setRows(arr);
        return vr;
    }

    public ViewResult searchProductByProductName(Integer page, Integer rows, String searchValue) {
        PageHelper.startPage(page, rows);
        List<Product> vos = mapper.searchProductByProductName(searchValue);
        PageInfo<Product> pf = new PageInfo<>();
        ViewResult vr = new ViewResult();
        vr.setRows(vos);
        vr.setTotal(pf.getTotal());
        return vr;
    }

    public ViewResult searchProductByProductType(Integer page, Integer rows, String searchValue) {
        PageHelper.startPage(page, rows);
        List<Product> vos = mapper.searchProductByProductType(searchValue);
        PageInfo<Product> pf = new PageInfo<>();
        ViewResult vr = new ViewResult();
        vr.setRows(vos);
        vr.setTotal(pf.getTotal());
        return vr;
    }
}
