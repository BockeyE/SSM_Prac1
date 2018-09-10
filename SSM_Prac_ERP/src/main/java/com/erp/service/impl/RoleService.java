package com.erp.service.impl;

import com.erp.bean.VO.RoleVo;
import com.erp.bean.Views.ViewResult;
import com.erp.bean.auths.SysRole;
import com.erp.bean.auths.SysUserRole;
import com.erp.dao.auths.SysRoleMapper;
import com.erp.dao.auths.SysUserMapper;
import com.erp.dao.auths.SysUserRoleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bockey
 */
@Service
public class RoleService {
    @Autowired
    SysRoleMapper srmapper;
    @Autowired
    SysUserRoleMapper surmapper;

    public ViewResult getList(int page, int rows, RoleVo sysRole) throws Exception {
        //查询列表

        //分页处理
        PageHelper.startPage(page, rows);
        List<RoleVo> list = srmapper.findAll();
        //创建一个返回值对象
        ViewResult result = new ViewResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<RoleVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }


    public RoleVo getRoleByUserId(String id) {
        SysUserRole sysUserRole = surmapper.selectByByUserId(id);
        RoleVo sysRole = srmapper.selectByPrimaryKey(sysUserRole.getSysRoleId());
        return sysRole;
    }
}
