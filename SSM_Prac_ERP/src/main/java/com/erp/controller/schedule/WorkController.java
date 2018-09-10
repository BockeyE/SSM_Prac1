package com.erp.controller.schedule;

import com.erp.bean.VO.WorkVO;
import com.erp.bean.Views.OperationResult;
import com.erp.bean.Views.ViewResult;
import com.erp.bean.Work;
import com.erp.service.impl.WorkServiceImpl;
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
@RequestMapping("/work")
public class WorkController {
    @Autowired
    WorkServiceImpl ser;

    @RequestMapping("/find")
    public String find(HttpSession session) {

//        ArrayList arr = new ArrayList();
//        arr.add("work:add");
//        arr.add("work:edit");
//        arr.add("work:delete");
//        session.setAttribute("sysPermissionList", arr);
        return "work_list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public ViewResult list(Integer page, Integer rows, WorkVO vo) {
        ViewResult vos = ser.getList(page, rows, vo);
        return vos;

    }

    @RequestMapping("/get_data")
    @ResponseBody
    public List<WorkVO> getData() {
        return ser.getAll();
    }


    @RequestMapping("/add")
    public String add()  {
        return "work_add";
    }

    @RequestMapping("/edit")
    public String edit()  {
        return "work_edit";
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    private OperationResult insert(@Valid Work w, BindingResult bindingResult) {
        OperationResult result;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            System.out.println(fieldError.getDefaultMessage());
            return OperationResult.build(100, fieldError.getDefaultMessage());
        }
        if (ser.get(w.getWorkId()) != null) {
            result = new OperationResult(0, "该作业编号已经存在，请更换作业编号！", null);
        } else {
            result = ser.insert(w);
        }
        return result;
    }

    @RequestMapping(value="/delete")
    @ResponseBody
    private OperationResult delete(String id)   throws Exception{
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
    private OperationResult update(@Valid Work a, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return OperationResult.build(100, fieldError.getDefaultMessage());
        }
        return ser.update(a);
    }

    @RequestMapping(value="/update_all")
    @ResponseBody
    private OperationResult updateAll(@Valid Work a, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return OperationResult.build(100, fieldError.getDefaultMessage());
        }
        return ser.updateAll(a);
    }
}
