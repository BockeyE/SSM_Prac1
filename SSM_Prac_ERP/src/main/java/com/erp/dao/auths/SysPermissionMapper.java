package com.erp.dao.auths;

import com.erp.bean.auths.SysPermission;

import java.util.List;

public interface SysPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

    List<SysPermission> findMenuListByUserId(String id);

    List<SysPermission> findPermissionListByUserId(String userid);

    String findPermissionStringByUserId(String userid);

    List<SysPermission> findPermissionListByPermissionId(List psid);

}