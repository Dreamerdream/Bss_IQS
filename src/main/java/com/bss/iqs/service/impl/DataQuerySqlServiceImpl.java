package com.bss.iqs.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
    public void saveSql(DataQuerySql sql) {
        dataQuerySqlMapper.insert(sql);
    }

    @Override
    public void deleteSql(Integer id) {
        dataQuerySqlMapper.deleteById(id);
    }

    @Override
    public void updateSql(DataQuerySql sql) {
        dataQuerySqlMapper.updateById(sql);
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
    public List<DataQuerySql> querySql(Integer groupId, String keyword, Integer pageNum, Integer pageSize) {
        return null;
    }
}
