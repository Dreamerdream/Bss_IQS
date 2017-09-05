package com.bss.iqs.bean;



public class AddPlanTaskBean {

  private Integer dataQueryTaskId;
  private String dataQueryTaskName;
  private String templateName;
  private String address;

    public Integer getDataQueryTaskId() {
        return dataQueryTaskId;
    }

    public void setDataQueryTaskId(Integer dataQueryTaskId) {
        this.dataQueryTaskId = dataQueryTaskId;
    }

    public String getDataQueryTaskName() {
        return dataQueryTaskName;
    }

    public void setDataQueryTaskName(String dataQueryTaskName) {
        this.dataQueryTaskName = dataQueryTaskName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
