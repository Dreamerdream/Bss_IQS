package com.bss.iqs.bean;

import com.bss.iqs.entity.GroupPermission;
import com.bss.iqs.entity.SystemPermission;
import com.bss.iqs.entity.UserGroup;

import java.util.List;

public class AddUserPermissionBean {



    private List<SystemPermission> systemPermissions;

    private List<GroupPermission> groupPermissions;

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
}
