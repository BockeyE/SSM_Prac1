package com.erp.controller.schedule;

import com.erp.bean.COrder;
import com.erp.bean.VO.COrderVO;
import com.erp.bean.Views.OperationResult;
import com.erp.bean.Views.ViewResult;
import com.erp.service.impl.OrderServiceImpl;
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
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderServiceImpl ser;

    @RequestMapping("/find")
    public String find(HttpSession session) {
//        ArrayList arr = new ArrayList();
//        arr.add("order:add");
//        arr.add("order:edit");
//        arr.add("order:delete");
//        session.setAttribute("sysPermissionList", arr);
        return "order_list";
    }


    @RequestMapping("/list")
    @ResponseBody
    public ViewResult list(Integer page, Integer rows, COrderVO vo) {
        ViewResult vos = ser.getList(page, rows, vo);
        return vos;
    }

    @RequestMapping("/get_data")
    @ResponseBody
    public List<COrderVO> getData() {
        return ser.getAll();
    }

    @RequestMapping("/add")
    public String add() {
        return "order_add";
    }

    @RequestMapping("/edit")
    public String edit() {
        return "order_edit";
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    private OperationResult insert(@Valid COrder cOrder, BindingResult bindingResult) {
        OperationResult result;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            System.out.println(fieldError.getDefaultMessage());
            return OperationResult.build(100, fieldError.getDefaultMessage());
        }
        if (ser.get(cOrder.getOrderId()) != null) {
            result = new OperationResult(0, "该订单编号已经存在，请更换订单编号！", null);
        } else {
            result = ser.insert(cOrder);
        }
        return result;
    }

    @RequestMapping(value="/delete")
    @ResponseBody
    private OperationResult delete(String id)  throws Exception {
        OperationResult result = ser.delete(id);
        return result;
    }

    @RequestMapping(value="/delete_batch")
    @ResponseBody
    private OperationResult deleteBatch(String[] ids) throws Exception  {
        OperationResult result = ser.deleteBatch(ids);
        return result;
    }
    @RequestMapping(value="/update")
    @ResponseBody
    private OperationResult update(@Valid COrder a, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return OperationResult.build(100, fieldError.getDefaultMessage());
        }
        return ser.update(a);
    }

    @RequestMapping(value="/update_all")
    @ResponseBody
    private OperationResult updateAll(@Valid COrder a, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return OperationResult.build(100, fieldError.getDefaultMessage());
        }
        return ser.updateAll(a);
    }


    //根据订单id查找
    @RequestMapping("/search_order_by_orderId")
    @ResponseBody
    public ViewResult searchOrderByOrderId(Integer page, Integer rows, String searchValue) throws Exception{
        ViewResult result = ser.searchOrderByOrderId(page, rows, searchValue);
        return result;
    }

    //根据客户名称查找
    @RequestMapping("/search_order_by_orderCustom")
    @ResponseBody
    public ViewResult searchOrderByOrderCustom(Integer page, Integer rows, String searchValue) throws Exception{
        ViewResult result = ser.searchOrderByCustomName(page, rows, searchValue);
        return result;
    }

    //根据产品名称查找
    @RequestMapping("/search_order_by_orderProduct")
    @ResponseBody
    public ViewResult searchOrderByProductName(Integer page, Integer rows, String searchValue) throws Exception{
        ViewResult result = ser.searchOrderByProductName(page, rows, searchValue);
        return result;
    }




}
