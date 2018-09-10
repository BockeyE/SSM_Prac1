package com.erp.service.impl;

import com.erp.bean.auths.SysPermission;
import com.erp.bean.auths.SysUser;
import com.erp.dao.auths.SysPermissionMapper;
import com.erp.dao.auths.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bockey
 */
@Service
public class SysService {

    @Autowired
    SysUserMapper sumapper;
    @Autowired
    SysPermissionMapper spmapper;

    public SysUser getSysUserByName(String username) {
        SysUser s = sumapper.getByName(username);
        return s;
    }

    public List<SysPermission> findMenuListByUserId(String id) {
        System.out.println("id==" + id);
        List<SysPermission> list = spmapper.findMenuListByUserId(id);
        return list;
    }

    public List<SysPermission> findPermissionListByUserId(String userid) {
        List<SysPermission> list = spmapper.findPermissionListByUserId(userid);

        String perString = spmapper.findPermissionStringByUserId(userid);
        String[] split = perString.split(",");
        ArrayList arr = new ArrayList();
        for (String s : split) {
            arr.add(Integer.parseInt(s));
        }
        List<SysPermission> permissions = spmapper.findPermissionListByPermissionId(arr);
        System.out.println("findPermissionListByUserId in sysservice == " + permissions);
        return permissions;
    }
}
