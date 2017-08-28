package com.bss.iqs.service;


import com.baomidou.mybatisplus.service.IService;
import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.entity.DataQueryTask;

import java.util.List;

/**
 * <p>
 * InnoDB free: 3072 kB 服务类
 * </p>
 *
 * @author hgh
 * @since 2017-08-26
 */
public interface IDataQueryTaskService extends IService<DataQueryTask> {

    public ResultBean saveDataQueryTask(DataQueryTask dataQueryTask);

    public ResultBean deleteDataQueryTask(Integer id);

    public ResultBean updateDataQueryTask(DataQueryTask dataQueryTask);

    public DataQueryTask getDataQueryTask(Integer id);

    public List<DataQueryTask> queryDataQueryTask(Integer type, String keyword);

    public void getTemplateAndAddress();

    public List<DataQueryTask> queryAll();
	
}
