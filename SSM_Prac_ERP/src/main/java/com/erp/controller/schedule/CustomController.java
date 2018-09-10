package com.erp.controller.schedule;

import com.erp.bean.Custom;
import com.erp.bean.Views.OperationResult;
import com.erp.bean.Views.ViewResult;
import com.erp.service.impl.CustomServiceImpl;
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
@RequestMapping("/custom")
public class CustomController {
    @Autowired
    CustomServiceImpl ser;

    @RequestMapping("/find")
    public String find(HttpSession session) {
//        ArrayList arr = new ArrayList();
//        arr.add("custom:add");
//        arr.add("custom:edit");
//        arr.add("custom:delete");
//        session.setAttribute("sysPermissionList", arr);
        return "custom_list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public ViewResult list(Integer page, Integer rows, Custom vo) {
        ViewResult vos = ser.getList(page, rows, vo);
        return vos;

    }
    @RequestMapping("/get_data")
    @ResponseBody
    public List<Custom> getData() {
        return ser.getAll();
    }


    @RequestMapping("/add")
    public String add()  {
        return "custom_add";
    }

    @RequestMapping("/edit")
    public String edit()  {
        return "custom_edit";
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    private OperationResult insert(@Valid Custom custom, BindingResult bindingResult) {
        OperationResult result;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            System.out.println(fieldError.getDefaultMessage());
            return OperationResult.build(100, fieldError.getDefaultMessage());
        }
        if (ser.get(custom.getCustomId()) != null) {
            result = new OperationResult(0, "该顾客编号已经存在，请更换顾客编号！", null);
        } else {
            result = ser.insert(custom);
        }
        return result;
    }

    @RequestMapping(value="/delete")
    @ResponseBody
    private OperationResult delete(String id) throws Exception {
        OperationResult result = ser.delete(id);
        return result;
    }

    @RequestMapping(value="/delete_batch")
    @ResponseBody
    private OperationResult deleteBatch(String[] ids)  throws Exception {
        OperationResult result = ser.deleteBatch(ids);
        return result;
    }

    @RequestMapping(value="/update")
    @ResponseBody
    private OperationResult update(@Valid Custom a, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return OperationResult.build(100, fieldError.getDefaultMessage());
        }
        return ser.update(a);
    }

    @RequestMapping(value="/update_all")
    @ResponseBody
    private OperationResult updateAll(@Valid Custom a, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return OperationResult.build(100, fieldError.getDefaultMessage());
        }
        return ser.updateAll(a);
    }
    //根据客户id查找
    @RequestMapping("/search_custom_by_customId")
    @ResponseBody
    public ViewResult searchCustomByCustomId(Integer page, Integer rows, String searchValue)
            throws Exception{
        ViewResult result = ser.searchCustomByCustomId(page, rows, searchValue);
        return result;
    }

    //根据客户名查找
    @RequestMapping("/search_custom_by_customName")
    @ResponseBody
    public ViewResult searchCustomByCustomName(Integer page, Integer rows, String searchValue)
            throws Exception{
        ViewResult result = ser.searchCustomByCustomName(page, rows, searchValue);
        return result;
    }


}
