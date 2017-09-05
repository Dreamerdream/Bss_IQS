package com.bss.iqs.service;


import com.baomidou.mybatisplus.service.IService;
import com.bss.iqs.bean.ResultBean;
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
    public ResultBean saveDataQuerySql(DataQuerySql sql);

    public ResultBean deleteDataQuerySql(Integer id);

    public ResultBean updateDataQuerySql(DataQuerySql sql);

    public DataQuerySql findDataQuerySqlById(Integer id);

    public List<DataQueryGroup> findAllDataQueryGroup();

    public List<DataQuerySql> queryDataQuerySql(Integer dataQueryGroupId, String keyword);
}
