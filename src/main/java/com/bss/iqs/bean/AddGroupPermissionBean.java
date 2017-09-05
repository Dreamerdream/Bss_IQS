package com.bss.iqs.bean;

import com.bss.iqs.entity.GroupPermission;
import com.bss.iqs.entity.SystemPermission;
import com.bss.iqs.entity.User;
import com.bss.iqs.entity.UserGroup;

import java.util.List;
import java.util.Map;

public class AddGroupPermissionBean {

    private List<SystemPermission> systemPermissions;

    private List<GroupPermission> groupPermissions;


    private List<User> users;

    public List<SystemPermission> getSystemPermissions() {
        return systemPermissions;
    }

    public void setSystemPermissions(List<SystemPermission> systemPermissions) {
        this.systemPermissions = systemPermissions;
    }

    public List<GroupPermission> getGroupPermissions() {
        return groupPermissions;
    }

    public void setGroupPermissions(List<GroupPermission> groupPermissions) {
        this.groupPermissions = groupPermissions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
