package com.erp.controller;

import com.erp.bean.Views.ValidUser;
import com.erp.bean.auths.SysPermission;
import com.erp.service.impl.SysService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bockey
 */
@Controller

public class FirstController {
    @Autowired
    SysService sserv;

    @RequestMapping({ "/login", "/first"})
    public String login() {
        return "login";
    }

    @RequestMapping({"/home"})
    public String home(HttpSession session, Model model) {

        Subject subject = SecurityUtils.getSubject();
        ValidUser vuser = (ValidUser) subject.getPrincipal();

        List<SysPermission> permissionList = null;

        permissionList = sserv.findPermissionListByUserId(vuser.getUserid());

        List<String> sysPermissionList = new ArrayList();
        if (permissionList != null) {
            for (int i = 0; i < permissionList.size(); i++) {
                sysPermissionList.add(permissionList.get(i).getPercode());
            }
        }
        System.out.println("sysPermissionList in home==" + sysPermissionList);
        model.addAttribute("activeUser", vuser);
        session.setAttribute("sysPermissionList", sysPermissionList);
        return "home";
    }


}
