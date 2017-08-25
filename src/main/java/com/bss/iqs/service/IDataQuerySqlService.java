package com.bss.iqs.service;


import com.baomidou.mybatisplus.service.IService;
import com.bss.iqs.entity.DataQueryGroup;
import com.bss.iqs.entity.DataQuerySql;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hgh
 * @since 2017-08-25
 */
public interface IDataQuerySqlService extends IService<DataQuerySql> {
    public void saveSql(DataQuerySql sql);

    public void deleteSql(Integer id);

    public void updateSql(DataQuerySql sql);

    public DataQuerySql getSql(Integer id);

    public List<DataQueryGroup> getDataQueryGroup();

    public List<DataQuerySql> querySql(Integer groupId,String keyword,Integer pageNum,Integer pageSize);
}
