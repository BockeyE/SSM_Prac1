package com.erp.controller.schedule;

import com.erp.bean.Manufacture;
import com.erp.bean.VO.ManufactureVO;
import com.erp.bean.Views.OperationResult;
import com.erp.bean.Views.ViewResult;
import com.erp.service.impl.ManufactureServiceImpl;
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
@RequestMapping("/manufacture")
public class ManufactureController {
    @Autowired
    ManufactureServiceImpl ser;

    @RequestMapping("/find")
    public String find(HttpSession session) {
//        ArrayList arr = new ArrayList();
//        arr.add("manufacture:add");
//        arr.add("manufacture:edit");
//        arr.add("manufacture:delete");
//        session.setAttribute("sysPermissionList", arr);

        return "manufacture_list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public ViewResult list(Integer page, Integer rows, ManufactureVO vo) {
        ViewResult vos = ser.getList(page, rows, vo);
        return vos;
    }

    @RequestMapping("/get_data")
    @ResponseBody
    public List<ManufactureVO> getData() {
        return ser.getAll();
    }


    @RequestMapping("/add")
    public String add() {
        return "manufacture_add";
    }

    @RequestMapping("/edit")
    public String edit() {
        return "manufacture_edit";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    private OperationResult insert(@Valid Manufacture a, BindingResult bindingResult) {
        OperationResult result;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            System.out.println(fieldError.getDefaultMessage());
            return OperationResult.build(100, fieldError.getDefaultMessage());
        }
        if (ser.get(a.getManufactureSn()) != null) {
            result = new OperationResult(0, "该生产计划编号已经存在，请更换生产计划编号！", null);
        } else {
            result = ser.insert(a);
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

    @RequestMapping(value="/update")
    @ResponseBody
    private OperationResult update(@Valid Manufacture a, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return OperationResult.build(100, fieldError.getDefaultMessage());
        }
        return ser.update(a);
    }

    @RequestMapping(value="/update_all")
    @ResponseBody
    private OperationResult updateAll(@Valid Manufacture a, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return OperationResult.build(100, fieldError.getDefaultMessage());
        }
        return ser.updateAll(a);
    }
}
