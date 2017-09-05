package com.bss.iqs.bean;

import com.bss.iqs.entity.User;

import java.util.List;

public class ActiveUser {

    private User user;

    private List<Menu> menuList;

    private Integer dataQueryTaskCount;

    private Integer templateCount;

    private Integer planTaskCount;

    private Integer dataQuerySqlCount;



    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Integer getDataQueryTaskCount() {
        return dataQueryTaskCount;
    }

    public void setDataQueryTaskCount(Integer dataQueryTaskCount) {
        this.dataQueryTaskCount = dataQueryTaskCount;
    }

    public Integer getTemplateCount() {
        return templateCount;
    }

    public void setTemplateCount(Integer templateCount) {
        this.templateCount = templateCount;
    }

    public Integer getPlanTaskCount() {
        return planTaskCount;
    }

    public void setPlanTaskCount(Integer planTaskCount) {
        this.planTaskCount = planTaskCount;
    }

    public Integer getDataQuerySqlCount() {
        return dataQuerySqlCount;
    }

    public void setDataQuerySqlCount(Integer dataQuerySqlCount) {
        this.dataQuerySqlCount = dataQuerySqlCount;
    }
}
