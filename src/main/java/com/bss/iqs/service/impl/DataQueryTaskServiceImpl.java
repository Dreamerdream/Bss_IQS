package com.bss.iqs.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bss.iqs.bean.AddDataQueryTaskBean;
import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.entity.*;
import com.bss.iqs.mapper.*;
import com.bss.iqs.service.IDataQueryTaskService;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * InnoDB free: 3072 kB 服务实现类
 * </p>
 *
 * @author hgh
 * @since 2017-08-26
 */
@Service
public class DataQueryTaskServiceImpl extends ServiceImpl<DataQueryTaskMapper, DataQueryTask> implements IDataQueryTaskService {

    @Autowired
    private DataQueryTaskMapper dataQueryTaskMapper;

    @Autowired
    private AreaMapper areaMapper;

    @Autowired
    private DataTemplateMapper dataTemplateMapper;

    @Autowired
    private RdsMapper rdsMapper;

    @Autowired
    private DataQueryGroupMapper dataQueryGroupMapper;

    @Autowired
    private PlanQueryTaskMapper planQueryTaskMapper;

    @Transactional
    @Override
    public ResultBean saveDataQueryTask(DataQueryTask dataQueryTask) {
        Date date = new Date();
        dataQueryTask.setStatus("0");
        dataQueryTask.setCreateTime(date);
        dataQueryTask.setUpdateTime(date);
        Integer insert = dataQueryTaskMapper.insert(dataQueryTask);
        if (insert != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("添加成功");
            return result;
        }
        return null;
    }

    @Transactional
    @Override
    public ResultBean deleteDataQueryTask(Integer id) {
        Integer integer = dataQueryTaskMapper.deleteById(id);
        if (integer != null){
            Wrapper<PlanQueryTask> planQueryTaskWrapper = new EntityWrapper<>();
            planQueryTaskWrapper.eq("dataQueryTaskId",id);
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
    public ResultBean updateDataQueryTask(DataQueryTask dataQueryTask) {
        dataQueryTask.setUpdateTime(new Date());
        Integer integer = dataQueryTaskMapper.updateById(dataQueryTask);
        if (integer != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("更新成功");
            return result;
        }
        return null;
    }

    @Override
    public DataQueryTask findDataQueryTaskById(Integer id) {
        return dataQueryTaskMapper.selectById(id);
    }

    @Override
    public List<DataQueryTask> queryDataQueryTask(Integer type, String keyword) {
        Wrapper<DataQueryTask> dataQueryTaskWrapper = new EntityWrapper<>();
        dataQueryTaskWrapper.eq("groupId",type).like("name",keyword);
        List<DataQueryTask> dataQueryTasks = dataQueryTaskMapper.selectList(dataQueryTaskWrapper);
        if (dataQueryTasks != null && dataQueryTasks.size() != 0){
            return dataQueryTasks;
        }
        return null;
    }

    @Override
    public AddDataQueryTaskBean getTemplateAndAddress() {

        AddDataQueryTaskBean addDataQueryTaskBean  = new AddDataQueryTaskBean();
        //得到数据查询任务组
        Wrapper<DataQueryGroup> dataQueryGroupWrapper = new EntityWrapper<>();
        List<DataQueryGroup> dataQueryGroups = dataQueryGroupMapper.selectList(dataQueryGroupWrapper);

        if (dataQueryGroups != null && dataQueryGroups.size() != 0){
            addDataQueryTaskBean.setDataQueryGroups(dataQueryGroups);
        }

        //得到省份
        Wrapper<Area> areaWrapper = new EntityWrapper<>();
        areaWrapper.eq("parentId", 0);
        List<Area> areas = areaMapper.selectList(areaWrapper);
        if (areas != null && areas.size() != 0){
            addDataQueryTaskBean.setAreas(areas);
        }

        //得到模板
        Wrapper<DataTemplate> templateWrapper = new EntityWrapper<>();
        List<DataTemplate> templates = dataTemplateMapper.selectList(templateWrapper);
        if (templates != null && templates.size() != 0){
            addDataQueryTaskBean.setTemplates(templates);
        }

        //得到RDS
        Wrapper<Rds> rdsWrapper = new EntityWrapper<>();
        rdsWrapper.eq("status",1);
        List<Rds> rds = rdsMapper.selectList(rdsWrapper);
        if (rds != null && rds.size() != 0){
            addDataQueryTaskBean.setRds(rds);
        }

        return addDataQueryTaskBean;

    }

    @Override
    public List<DataQueryTask> queryAll() {
        return null;
    }
}
