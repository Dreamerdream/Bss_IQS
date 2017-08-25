package com.bss.iqs.bean;



import com.bss.iqs.entity.Department;
import com.bss.iqs.entity.UserGroup;

import java.util.List;

public class UserResult {

    private List<Department> departments;

    private List<UserGroup> userGroups;

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }
}
