package com.bss.iqs.service;


import com.baomidou.mybatisplus.service.IService;
import com.bss.iqs.bean.AddDataQueryTaskBean;
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

     ResultBean saveDataQueryTask(DataQueryTask dataQueryTask);

     ResultBean deleteDataQueryTask(Integer id);

     ResultBean updateDataQueryTask(DataQueryTask dataQueryTask);

     DataQueryTask findDataQueryTaskById(Integer id);

     List<DataQueryTask> queryDataQueryTask(Integer type, String keyword);

     AddDataQueryTaskBean getTemplateAndAddress();

     List<DataQueryTask> queryAll();
	
}
