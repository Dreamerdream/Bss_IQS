package com.bss.iqs.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.entity.DataQueryGroup;
import com.bss.iqs.entity.DataQuerySql;
import com.bss.iqs.mapper.DataQueryGroupMapper;
import com.bss.iqs.mapper.DataQuerySqlMapper;
import com.bss.iqs.service.IDataQuerySqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class DataQuerySqlServiceImpl extends ServiceImpl<DataQuerySqlMapper, DataQuerySql> implements IDataQuerySqlService {

    @Autowired
    private DataQuerySqlMapper dataQuerySqlMapper;

    @Autowired
    private DataQueryGroupMapper dataQueryGroupMapper;


    @Transactional
    @Override
    public ResultBean saveDataQuerySql(DataQuerySql sql) {
        Date date = new Date();
        sql.setCreateTime(date);
        sql.setUpdateTime(date);
        Integer insert = dataQuerySqlMapper.insert(sql);
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
    public ResultBean deleteDataQuerySql(Integer id) {
        Integer integer = dataQuerySqlMapper.deleteById(id);
        if (integer != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("删除成功");
            return result;
        }
        return null;

    }

    @Transactional
    @Override
    public ResultBean updateDataQuerySql(DataQuerySql sql) {
        sql.setUpdateTime(new Date());
        Integer integer = dataQuerySqlMapper.updateById(sql);
        if (integer != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("更新成功");
            return result;
        }
        return null;
    }

    @Override
    public DataQuerySql findDataQuerySqlById(Integer id) {
        return dataQuerySqlMapper.selectById(id);
    }

    @Override
    public List<DataQueryGroup> findAllDataQueryGroup() {
        Wrapper<DataQueryGroup> dataQueryGroupMapperWrapper = new EntityWrapper<>();
        List<DataQueryGroup> dataQueryGroups = dataQueryGroupMapper.selectList(dataQueryGroupMapperWrapper);
        if (dataQueryGroups != null && dataQueryGroups.size() != 0){
            return dataQueryGroups;
        }
        return null;
    }

    @Override
    public List<DataQuerySql> queryDataQuerySql(Integer dataQueryGroupId, String keyword) {
        Wrapper<DataQuerySql> dataQuerySqlWrapper = new EntityWrapper<>();
        dataQuerySqlWrapper.eq("dataQueryGroupId",dataQueryGroupId).like("sqlContent",keyword);
        List<DataQuerySql> dataQuerySqls = dataQuerySqlMapper.selectList(dataQuerySqlWrapper);
        if (dataQuerySqls != null && dataQuerySqls.size() != 0){
            return dataQuerySqls;
        }
        return null;
    }
}
