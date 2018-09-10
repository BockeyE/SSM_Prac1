package com.erp.dao.auths;

import com.erp.bean.VO.RoleVo;
import com.erp.bean.auths.SysRole;

import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    List<RoleVo> findAll( );
    RoleVo selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}