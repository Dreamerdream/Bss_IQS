package com.bss.iqs.bean;

import com.bss.iqs.entity.User;

import java.util.List;

public class GroupPermissionBean {

    private Integer groupPermissionId;
    private String permissionName;


    private List<UserGroupPermissionBean> userGroupPermissionBeans;

    public Integer getGroupPermissionId() {
        return groupPermissionId;
    }

    public void setGroupPermissionId(Integer groupPermissionId) {
        this.groupPermissionId = groupPermissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public List<UserGroupPermissionBean> getUserGroupPermissionBeans() {
        return userGroupPermissionBeans;
    }

    public void setUserGroupPermissionBeans(List<UserGroupPermissionBean> userGroupPermissionBeans) {
        this.userGroupPermissionBeans = userGroupPermissionBeans;
    }
}
