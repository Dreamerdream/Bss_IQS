package com.bss.iqs.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bss.iqs.bean.AddPlanTaskBean;
import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.entity.DataQueryGroup;
import com.bss.iqs.entity.DataQueryTask;
import com.bss.iqs.entity.PlanTask;
import com.bss.iqs.entity.Template;
import com.bss.iqs.mapper.DataQueryGroupMapper;
import com.bss.iqs.mapper.DataQueryTaskMapper;
import com.bss.iqs.mapper.PlanTaskMapper;
import com.bss.iqs.mapper.TemplateMapper;
import com.bss.iqs.service.IPlanTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private TemplateMapper templateMapper;

    @Override
    public ResultBean savePlanTask(PlanTask planTask) {
        Date date = new Date();
        planTask.setCreateTime(date);
        planTask.setUpdateTime(date);
        Integer insert = planTaskMapper.insert(planTask);
        if (insert != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("添加成功");
            return result;
        }
        return null;
    }

    @Override
    public ResultBean deletePlanTask(Integer id) {
        Integer integer = planTaskMapper.deleteById(id);
        if (integer != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("删除成功");
            return result;
        }
        return null;
    }

    @Override
    public ResultBean updatePlanTask(PlanTask planTask) {
        planTask.setUpdateTime(new Date());
        Integer integer = planTaskMapper.updateById(planTask);
        if (integer != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("更新成功");
            return result;
        }
        return null;
    }

    @Override
    public PlanTask findPlanTaskById(Integer id) {
        PlanTask planTask = planTaskMapper.selectById(id);
        return planTask;
    }

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
                Template template = templateMapper.selectById(dataQueryTask.getTemplateId());
                addPlanTaskBean.setTemplateName(template.getName());
                addPlanTaskBean.setAddress(dataQueryTask.getProvince()+","+dataQueryTask.getCity());
                addPlanTaskBean.setDataQueryTaskId(dataQueryTask.getId());
                addPlanTaskBeans.add(addPlanTaskBean);
            }
            return addPlanTaskBeans;
        }
        return null;
    }

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
