package com.bss.iqs.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.entity.DataQueryGroup;
import com.bss.iqs.entity.DataQueryTask;
import com.bss.iqs.mapper.DataQueryGroupMapper;
import com.bss.iqs.mapper.DataQueryTaskMapper;
import com.bss.iqs.service.IDataQueryGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hgh
 * @since 2017-08-25
 */
@Service
public class DataQueryGroupServiceImpl extends ServiceImpl<DataQueryGroupMapper, DataQueryGroup> implements IDataQueryGroupService {

    @Autowired
    private DataQueryTaskMapper dataQueryTaskMapper;

    @Autowired
    private DataQueryGroupMapper dataQueryGroupMapper;
    @Override
    public List<DataQueryTask> findAllDataQueryTasks() {

        Wrapper<DataQueryTask> dataQueryTaskWrapper = new EntityWrapper<>();
        dataQueryTaskWrapper.eq("status","1");
        List<DataQueryTask> dataQueryTasks = dataQueryTaskMapper.selectList(dataQueryTaskWrapper);
        return dataQueryTasks;
    }

    @Override
    public ResultBean savedataQueryGroup(String filePath,String dataQueryGroupName,Integer dataQueryTaskId) {
        DataQueryGroup dataQueryGroup = new DataQueryGroup();
        dataQueryGroup.setDataQueryTaskId(dataQueryTaskId);
        dataQueryGroup.setLogo(filePath);
        dataQueryGroup.setName(dataQueryGroupName);
        Date date = new Date();
        dataQueryGroup.setCreateTime(date);
        dataQueryGroup.setUpdateTime(date);
        Integer insert = dataQueryGroupMapper.insert(dataQueryGroup);
        if (insert != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("添加成功");
            return result;
        }
        return null;
    }

    @Override
    public ResultBean updatedataQueryGroup(DataQueryGroup dataQueryGroup) {
        dataQueryGroup.setUpdateTime(new Date());
        Integer integer = dataQueryGroupMapper.updateById(dataQueryGroup);
        if (integer != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("修改成功");
            return result;
        }
        return null;
    }

    @Override
    public ResultBean deletedataQueryGroup(Integer id) {
        Integer integer = dataQueryGroupMapper.deleteById(id);
        if (integer != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("删除成功");
            return result;
        }
        return null;
    }

    @Override
    public DataQueryGroup getdataQueryGroup(Integer id) {
        DataQueryGroup dataQueryGroup = dataQueryGroupMapper.selectById(id);
        Wrapper<DataQueryTask> dataQueryTaskWrapper = new EntityWrapper<>();
        dataQueryTaskWrapper.eq("type",1);
        List<DataQueryTask> dataQueryTasks = dataQueryTaskMapper.selectList(dataQueryTaskWrapper);
        return dataQueryGroup;


    }
}
