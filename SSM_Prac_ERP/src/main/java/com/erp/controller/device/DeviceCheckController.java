package com.erp.controller.device;

import com.erp.bean.DeviceCheck;
import com.erp.bean.DeviceFault;
import com.erp.bean.Views.OperationResult;
import com.erp.bean.Views.ViewResult;
import com.erp.service.impl.DeviceCheckServiceImpl;
import com.erp.service.impl.DeviceFaultServiceImpl;
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
@RequestMapping("/deviceCheck")
public class DeviceCheckController {
    @Autowired
    DeviceCheckServiceImpl ser;

    @RequestMapping("/find")
    public String find(HttpSession session) {
//        ArrayList arr = new ArrayList();
//        arr.add("device:add");
//        arr.add("device:edit");
//        arr.add("device:delete");
//        session.setAttribute("sysPermissionList", arr);
        return "deviceCheck";
    }

    @RequestMapping("/list")
    @ResponseBody
    public ViewResult list(Integer page, Integer rows, DeviceCheck vo) {
        ViewResult vos = ser.getList(page, rows, vo);
        return vos;

    }
    @RequestMapping("/get_data")
    @ResponseBody
    public List<DeviceCheck> getData() {
        return ser.getAll();
    }


    @RequestMapping("/add")
    public String add()  {
        return "deviceCheck_add";
    }

    @RequestMapping("/edit")
    public String edit()  {
        return "deviceCheck_edit";
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    private OperationResult insert(@Valid DeviceCheck a, BindingResult bindingResult) {
        OperationResult result;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            System.out.println(fieldError.getDefaultMessage());
            return OperationResult.build(100, fieldError.getDefaultMessage());
        }
        if (ser.get(a.getDeviceCheckId()) != null) {
            result = new OperationResult(0, "该顾客编号已经存在，请更换顾客编号！", null);
        } else {
            result = ser.insert(a);
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
    private OperationResult update(@Valid DeviceCheck a, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return OperationResult.build(100, fieldError.getDefaultMessage());
        }
        return ser.update(a);
    }

    @RequestMapping(value="/update_all")
    @ResponseBody
    private OperationResult updateAll(@Valid DeviceCheck a, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return OperationResult.build(100, fieldError.getDefaultMessage());
        }
        return ser.updateAll(a);
    }



}
