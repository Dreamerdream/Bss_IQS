package com.bss.iqs.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bss.iqs.bean.AddPlanTaskBean;
import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.entity.*;

import com.bss.iqs.mapper.*;
import com.bss.iqs.service.IPlanTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hgh
 * @since 2017-08-29
 */
@Service
public class PlanTaskServiceImpl extends ServiceImpl<PlanTaskMapper, PlanTask> implements IPlanTaskService {

    @Autowired
    private PlanTaskMapper planTaskMapper;

    @Autowired
    private DataQueryGroupMapper dataQueryGroupMapper;

    @Autowired
    private DataQueryTaskMapper dataQueryTaskMapper;

    @Autowired
    private DataTemplateMapper dataTemplateMapper;

    @Autowired
    private PlanQueryTaskMapper planQueryTaskMapper;

    @Transactional
    @Override
    public ResultBean savePlanTask(PlanTask planTask) {
        Date date = new Date();
        planTask.setCreateTime(date);
        planTask.setUpdateTime(date);
        Integer insert = planTaskMapper.insert(planTask);
        if (insert != null){
            //添加到主子表中
            String dataQueryTaskId = planTask.getDataQueryTaskId();
            String[] split = dataQueryTaskId.split(",");
            if (split != null && split.length != 0){
                for (int i = 0; i < split.length ; i++) {
                    PlanQueryTask planQueryTask = new PlanQueryTask();
                    planQueryTask.setDataQueryTaskId(Integer.valueOf(split[i]));
                    planQueryTask.setPlanTaskId(insert);
                    planQueryTaskMapper.insert(planQueryTask);
                }
            }
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("添加成功");
            return result;
        }
        return null;
    }

    @Transactional
    @Override
    public ResultBean deletePlanTask(Integer id) {
        Integer integer = planTaskMapper.deleteById(id);
        if (integer != null){
            //删除主子表中的该计划任务的记录
            Wrapper<PlanQueryTask> planQueryTaskWrapper = new EntityWrapper<>();
            planQueryTaskWrapper.eq("planTaskId",id);
            Integer delete = planQueryTaskMapper.delete(planQueryTaskWrapper);
            if (delete != null){
                ResultBean result = new ResultBean();
                result.setErrorCode(0);
                result.setErrorReason("删除成功");
                return result;
            }
        }
        return null;
    }

    @Transactional
    @Override
    public ResultBean updatePlanTask(PlanTask planTask) {
        planTask.setUpdateTime(new Date());
        Integer integer = planTaskMapper.updateById(planTask);
        if (integer != null){
            //更新主子表
            //删除
            Wrapper<PlanQueryTask> planQueryTaskWrapper = new EntityWrapper<>();
            planQueryTaskWrapper.eq("planTaskId",planTask.getId());
            planQueryTaskMapper.delete(planQueryTaskWrapper);

            //添加
            String dataQueryTaskId = planTask.getDataQueryTaskId();
            String[] split = dataQueryTaskId.split(",");
            if (split != null && split.length != 0){
                for (int i = 0; i < split.length ; i++) {
                    PlanQueryTask planQueryTask = new PlanQueryTask();
                    planQueryTask.setPlanTaskId(planTask.getId());
                    planQueryTask.setDataQueryTaskId(Integer.valueOf(split[i]));
                    planQueryTaskMapper.insert(planQueryTask);
                }
            }
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("更新成功");
            return result;
        }
        return null;
    }

    @Override
    public PlanTask findPlanTaskById(Integer id) {
//        Wrapper<PlanQueryTask> planQueryTaskWrapper = new EntityWrapper<>();
//        planQueryTaskWrapper.eq("planTaskId",id);
//        List<PlanQueryTask> planQueryTasks = planQueryTaskMapper.selectList(planQueryTaskWrapper);
//        if (planQueryTasks != null && planQueryTasks.size() != 0){
//            for (int i = 0; i < planQueryTasks.size() ; i++) {
//                PlanQueryTask planQueryTask = planQueryTasks.get(i);
//                DataQueryTask dataQueryTask = dataQueryTaskMapper.selectById(planQueryTask.getDataQueryTaskId());
//            }
//        }
        PlanTask planTask = planTaskMapper.selectById(id);
        return planTask;
    }

    //用于添加，找到所有组
    @Override
    public List<DataQueryGroup> findAllDataQueryGroup() {
        //组名称
        Wrapper<DataQueryGroup> dataQueryGroupWrapper = new EntityWrapper<>();
        List<DataQueryGroup> dataQueryGroups = dataQueryGroupMapper.selectList(dataQueryGroupWrapper);
        if (dataQueryGroups != null && dataQueryGroups.size() != 0){
            return dataQueryGroups;
        }
        return null;
    }

    //根据组的到计划任务和其他描述
    @Override
    public List<AddPlanTaskBean> findDataQueryTaskByDqgId(Integer dataQueryGroupId) {
        Wrapper<DataQueryTask> dataQueryTaskWrapper = new EntityWrapper<>();
        dataQueryTaskWrapper.eq("dataQueryGroupId",dataQueryGroupId);
        List<DataQueryTask> dataQueryTasks = dataQueryTaskMapper.selectList(dataQueryTaskWrapper);
        if (dataQueryTasks != null && dataQueryTasks.size() != 0){
            List<AddPlanTaskBean> addPlanTaskBeans = new ArrayList<>();
            for (int i = 0; i < dataQueryTasks.size() ; i++) {
                DataQueryTask dataQueryTask = dataQueryTasks.get(i);
                AddPlanTaskBean addPlanTaskBean = new AddPlanTaskBean();
                DataTemplate template = dataTemplateMapper.selectById(dataQueryTask.getTemplateId());
                addPlanTaskBean.setTemplateName(template.getName());
                addPlanTaskBean.setAddress(dataQueryTask.getProvince()+","+dataQueryTask.getCity());
                addPlanTaskBean.setDataQueryTaskId(dataQueryTask.getId());
                addPlanTaskBeans.add(addPlanTaskBean);
            }
            return addPlanTaskBeans;
        }
        return null;
    }

    //查询
    @Override
    public List<PlanTask> findPlanTaskByDqgId(Integer dataQueryGroupId, String name) {
        Wrapper<PlanTask> planTaskWrapper = new EntityWrapper<>();
        planTaskWrapper.eq("dataQueryGroupId", dataQueryGroupId).like("name", name).orderBy("createTime", true);
        List<PlanTask> planTasks = planTaskMapper.selectList(planTaskWrapper);
        if (planTasks != null && planTasks.size() != 0){
            return planTasks;
        }
        return null;
    }

}
