package com.bss.iqs.service;


import com.baomidou.mybatisplus.service.IService;
import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.bean.UpdateDataQueryroupBean;
import com.bss.iqs.entity.DataQueryGroup;
import com.bss.iqs.entity.DataQueryTask;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hgh
 * @since 2017-08-25
 */
public interface IDataQueryGroupService extends IService<DataQueryGroup> {

     List<DataQueryTask> findAllDataQueryTasks();

     ResultBean savedataQueryGroup(String filePath,String dataQueryGroupName,Integer dataQueryTaskId);

     ResultBean updatedataQueryGroup(DataQueryGroup dataQueryGroup);

     ResultBean deletedataQueryGroup(Integer id);

     UpdateDataQueryroupBean getdataQueryGroup(Integer id);
	
}
