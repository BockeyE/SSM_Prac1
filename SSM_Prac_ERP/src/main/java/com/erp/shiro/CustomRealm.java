package com.erp.shiro;

import com.erp.bean.VO.RoleVo;
import com.erp.bean.Views.ValidUser;
import com.erp.bean.auths.SysPermission;
import com.erp.bean.auths.SysUser;
import com.erp.service.impl.RoleService;
import com.erp.service.impl.SysService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author bockey
 */
public class CustomRealm extends AuthorizingRealm {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysService sserv;

    @Autowired
    private RoleService rserv;

    @Override
    public void setName(String name) {
        super.setName("customRealm");
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();

        SysUser su = null;
        su = sserv.getSysUserByName(username);
        if (su == null) {
            return null;
        }
        String password = su.getPassword();
        ValidUser validUser = new ValidUser();
        validUser.setUserid(su.getId());
        validUser.setUsername(su.getUsername());
        validUser.setUserStatus(su.getLocked());

        RoleVo rv = null;
        rv = rserv.getRoleByUserId(su.getId());

        validUser.setRolename(rv.getRoleName());
        validUser.setRoleStatus(rv.getAvailable());

        List<SysPermission> menus = null;

        //通过service取出菜单
        menus = sserv.findMenuListByUserId(su.getId());
        validUser.setMenus(menus);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                validUser, password, this.getName());
        return simpleAuthenticationInfo;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pcc) {
        ValidUser validUser = (ValidUser) pcc.getPrimaryPrincipal();
        List<SysPermission> permissionList = null;
        permissionList = sserv.findPermissionListByUserId(validUser.getUserid());
        ArrayList arr = new ArrayList();
        if (permissionList != null) {
            for (SysPermission sysPermission : permissionList) {
                arr.add(sysPermission.getPercode());
            }
        }
        System.out.println("sysPermission in customrealm  ==  " + arr);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //将上边查询到授权信息填充到simpleAuthorizationInfo对象中
        simpleAuthorizationInfo.addStringPermissions(arr);
        return simpleAuthorizationInfo;
    }

}
