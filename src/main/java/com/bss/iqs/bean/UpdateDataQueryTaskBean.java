package com.bss.iqs.bean;

import com.bss.iqs.entity.DataQueryGroup;
import com.bss.iqs.entity.DataQueryTask;

import java.util.List;

/**
 * Created by hgh on 2017/8/26.
 */
public class UpdateDataQueryTaskBean {

    private DataQueryGroup dataQueryGroup;

    private List<DataQueryTask> dataQueryTasks;

    public DataQueryGroup getDataQueryGroup() {
        return dataQueryGroup;
    }

    public void setDataQueryGroup(DataQueryGroup dataQueryGroup) {
        this.dataQueryGroup = dataQueryGroup;
    }

    public List<DataQueryTask> getDataQueryTasks() {
        return dataQueryTasks;
    }

    public void setDataQueryTasks(List<DataQueryTask> dataQueryTasks) {
        this.dataQueryTasks = dataQueryTasks;
    }
}
