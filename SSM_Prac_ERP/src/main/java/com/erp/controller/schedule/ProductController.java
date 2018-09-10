package com.erp.controller.schedule;

import com.erp.bean.Product;
import com.erp.bean.Views.OperationResult;
import com.erp.bean.Views.ViewResult;
import com.erp.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bockey
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductServiceImpl ser;

    @RequestMapping("/find")
    public String find(HttpSession session) {

//        ArrayList arr = new ArrayList();
//        arr.add("product:add");
//        arr.add("product:edit");
//        arr.add("product:delete");
//        session.setAttribute("sysPermissionList", arr);
        return "product_list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public ViewResult list(Integer page, Integer rows, Product vo) {
        ViewResult vos = ser.getList(page, rows, vo);
        return vos;
    }

    @RequestMapping("/get_data")
    @ResponseBody
    public List<Product> getData() {
        return ser.getAll();
    }


    @RequestMapping("/add")
    public String add() {
        return "product_add";
    }

    @RequestMapping("/edit")
    public String edit() {
        return "product_edit";
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    private OperationResult insert(@Valid Product product, BindingResult bindingResult) {
        OperationResult result;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            System.out.println(fieldError.getDefaultMessage());
            return OperationResult.build(100, fieldError.getDefaultMessage());
        }
        if (ser.get(product.getProductId()) != null) {
            result = new OperationResult(0, "该商品编号已经存在，请更换订单编号！", null);
        } else {
            result = ser.insert(product);
        }
        return result;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    private OperationResult delete(String id) throws Exception {
        OperationResult result = ser.delete(id);
        return result;
    }

    @RequestMapping(value = "/delete_batch")
    @ResponseBody
    private OperationResult deleteBatch(String[] ids) throws Exception {
        OperationResult result = ser.deleteBatch(ids);
        return result;
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    private OperationResult update(@Valid Product a, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            return OperationResult.build(100, fieldError.getDefaultMessage());
        }
        return ser.update(a);
    }

    @RequestMapping(value = "/update_all")
    @ResponseBody
    private OperationResult updateAll(@Valid Product a, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            return OperationResult.build(100, fieldError.getDefaultMessage());
        }
        return ser.updateAll(a);
    }

    //根据产品id查找
    @RequestMapping("/search_product_by_productId")
    @ResponseBody
    public ViewResult searchProductByProductId(Integer page, Integer rows, String searchValue) throws Exception {
        ViewResult result = ser.searchProductByProductId(page, rows, searchValue);
        return result;
    }

    //根据产品名称查找
    @RequestMapping("/search_product_by_productName")
    @ResponseBody
    public ViewResult searchProductByProductName(Integer page, Integer rows, String searchValue) throws Exception {
        ViewResult result = ser.searchProductByProductName(page, rows, searchValue);
        return result;
    }

    //根据产品类型查找
    @RequestMapping("/search_product_by_productType")
    @ResponseBody
    public ViewResult searchProductByProductType(Integer page, Integer rows, String searchValue)
            throws Exception {
        ViewResult result = ser.searchProductByProductType(page, rows, searchValue);
        return result;
    }


}
