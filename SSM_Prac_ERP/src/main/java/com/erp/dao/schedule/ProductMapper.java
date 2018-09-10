package com.erp.dao.schedule;

import com.erp.bean.Product;
import com.erp.bean.example.ProductExample;

import java.util.HashMap;
import java.util.List;

public interface ProductMapper {
    int insert(Product record);

    int insertSelective(Product record);

    int deleteByPrimaryKey(String id);

    Product selectByPrimaryKey(String s);

    List<Product> findAll(Product p);

    List<Product> selectByExample(ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> searchProductByProductName(String searchValue);

    List<Product> searchProductByProductType(String searchValue);
}