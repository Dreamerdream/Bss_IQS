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
     ResultBean saveDataQuerySql(DataQuerySql sql);

     ResultBean deleteDataQuerySql(Integer id);

     ResultBean updateDataQuerySql(DataQuerySql sql);

     DataQuerySql findDataQuerySqlById(Integer id);

     List<DataQueryGroup> findAllDataQueryGroup();

     List<DataQuerySql> queryDataQuerySql(Integer dataQueryGroupId, String keyword);
}
