package com.bss.iqs.bean;

import com.bss.iqs.entity.Area;
import com.bss.iqs.entity.DataQueryGroup;
import com.bss.iqs.entity.DataTemplate;
import com.bss.iqs.entity.Rds;


import java.util.List;

public class AddDataQueryTaskBean {



    private List<Area> areas;

    private List<DataTemplate> templates;

    private List<Rds> rds;

    private List<DataQueryGroup> dataQueryGroups;

    public List<DataQueryGroup> getDataQueryGroups() {
        return dataQueryGroups;
    }

    public void setDataQueryGroups(List<DataQueryGroup> dataQueryGroups) {
        this.dataQueryGroups = dataQueryGroups;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public List<DataTemplate> getTemplates() {
        return templates;
    }

    public void setTemplates(List<DataTemplate> templates) {
        this.templates = templates;
    }

    public List<Rds> getRds() {
        return rds;
    }

    public void setRds(List<Rds> rds) {
        this.rds = rds;
    }
}
