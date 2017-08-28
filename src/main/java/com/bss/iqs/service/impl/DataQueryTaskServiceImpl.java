package com.bss.iqs.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.entity.Area;
import com.bss.iqs.entity.DataQueryTask;
import com.bss.iqs.mapper.AreaMapper;
import com.bss.iqs.mapper.DataQueryTaskMapper;
import com.bss.iqs.service.IDataQueryTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Override
    public ResultBean saveDataQueryTask(DataQueryTask dataQueryTask) {

        Integer insert = dataQueryTaskMapper.insert(dataQueryTask);
        if (insert != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("添加成功");
        }
        return null;
    }

    @Override
    public ResultBean deleteDataQueryTask(Integer id) {
        Integer integer = dataQueryTaskMapper.deleteById(id);
        if (integer != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("删除成功");
        }
        return null;
    }

    @Override
    public ResultBean updateDataQueryTask(DataQueryTask dataQueryTask) {
        Integer integer = dataQueryTaskMapper.updateById(dataQueryTask);
        if (integer != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("更新成功");
        }
        return null;
    }

    @Override
    public DataQueryTask getDataQueryTask(Integer id) {
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
    public void getTemplateAndAddress() {
        //得到省份
        Wrapper<Area> areaWrapper = new EntityWrapper<>();
        areaWrapper.eq("parentId", 0);
        List<Area> provinces = areaMapper.selectList(areaWrapper);

        //得到模板，未写


    }

    @Override
    public List<DataQueryTask> queryAll() {
        return null;
    }
}
