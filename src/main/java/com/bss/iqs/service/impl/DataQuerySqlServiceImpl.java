package com.bss.iqs.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.entity.DataQueryGroup;
import com.bss.iqs.entity.DataQuerySql;
import com.bss.iqs.mapper.DataQuerySqlMapper;
import com.bss.iqs.service.IDataQuerySqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public ResultBean saveSql(DataQuerySql sql) {
        Integer insert = dataQuerySqlMapper.insert(sql);
        if (insert != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("添加成功");
        }
        return null;
    }

    @Override
    public ResultBean deleteSql(Integer id) {
        Integer integer = dataQuerySqlMapper.deleteById(id);
        if (integer != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("删除成功");
        }
        return null;

    }

    @Override
    public ResultBean updateSql(DataQuerySql sql) {
        Integer integer = dataQuerySqlMapper.updateById(sql);
        if (integer != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("更新成功");
        }
        return null;
    }

    @Override
    public DataQuerySql getSql(Integer id) {
        return dataQuerySqlMapper.selectById(id);
    }

    @Override
    public List<DataQueryGroup> getDataQueryGroup() {
        return null;
    }

    @Override
    public List<DataQuerySql> querySql(Integer groupId, String keyword) {
        Wrapper<DataQuerySql> dataQuerySqlWrapper = new EntityWrapper<>();
        dataQuerySqlWrapper.eq("groupId",groupId).like("sqlContent",keyword);
        List<DataQuerySql> dataQuerySqls = dataQuerySqlMapper.selectList(dataQuerySqlWrapper);
        if (dataQuerySqls != null && dataQuerySqls.size() != 0){
            return dataQuerySqls;
        }
        return null;
    }
}
