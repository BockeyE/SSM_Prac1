package com.erp.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author bockey
 */
@Controller
public class LoginController {

    @RequestMapping("/ajaxLogin")
    @ResponseBody
    public Map ajaxLogin(@RequestParam String username, @RequestParam String password, HttpSession session) {
        HashMap<Object, Object> map = new HashMap<>();
        System.out.println("enter ajax login");
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                currentUser.login(token);
            } catch (UnknownAccountException ex) {
                ex.printStackTrace();
                map.put("msg", "account_error");
            } catch (IncorrectCredentialsException ex) {
                ex.printStackTrace();
                map.put("msg", "password_error");
            } catch (AuthenticationException ex) {
                ex.printStackTrace();
                map.put("msg", "authentication_error");
            }
        }
        //返回json数据
        return map;
    }
}
