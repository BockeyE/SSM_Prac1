package com.erp.controller;

import com.erp.bean.Views.ValidUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static jdk.net.SocketFlow.Status.NO_PERMISSION;

/**
 * @author bockey
 */
@Controller
public class AuthsJudgeController {

    @RequestMapping("*/*_judge")
    @ResponseBody
    public Map judge(HttpServletRequest request) {

        Subject subject = SecurityUtils.getSubject();
        ValidUser vuser = (ValidUser) subject.getPrincipal();

        //根据uri,使用shiro判断相应权限
        String uri = request.getRequestURI();
        String[] names = uri.split("/");
        String featureName = names[1];
        String operateName = names[2].split("_")[0];
        HashMap map = new HashMap();
        if (!vuser.getUserStatus().equals("1")) {
            map.put("msg", "您的账户已被锁定，请切换账户登录！");
        } else if (!vuser.getRoleStatus().equals("1")) {
            map.put("msg", "当前角色已被锁定，请切换账户登录！");
        } else {
            if (!subject.isPermitted(featureName + ":" + operateName)) {
                map.put("msg", "您没有权限，请切换用户登录！");
            }
        }
        return map;
    }
}
