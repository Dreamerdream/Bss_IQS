package com.bss.iqs.bean;

import com.bss.iqs.entity.Area;
import com.bss.iqs.entity.Rds;
import com.bss.iqs.entity.Template;

import java.util.List;

public class AddDataQueryTaskBean {



    private List<Area> areas;

    private List<Template> templates;

    private List<Rds> rds;

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public List<Template> getTemplates() {
        return templates;
    }

    public void setTemplates(List<Template> templates) {
        this.templates = templates;
    }

    public List<Rds> getRds() {
        return rds;
    }

    public void setRds(List<Rds> rds) {
        this.rds = rds;
    }
}
