package com.bss.iqs.service;


import com.baomidou.mybatisplus.service.IService;
import com.bss.iqs.bean.AddPlanTaskBean;
import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.entity.DataQueryGroup;
import com.bss.iqs.entity.DataQueryTask;
import com.bss.iqs.entity.PlanTask;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hgh
 * @since 2017-08-29
 */
public interface IPlanTaskService extends IService<PlanTask> {

     ResultBean savePlanTask(PlanTask planTask);

     ResultBean deletePlanTask(Integer id);


     ResultBean updatePlanTask(PlanTask planTask);


     PlanTask findPlanTaskById(Integer id);

     List<DataQueryGroup> findAllDataQueryGroup();

     List<AddPlanTaskBean> findDataQueryTaskByDqgId(Integer dataQueryGroupId);

     List<PlanTask> findPlanTaskByDqgId(Integer dataQueryGroupId, String name);
	
}
