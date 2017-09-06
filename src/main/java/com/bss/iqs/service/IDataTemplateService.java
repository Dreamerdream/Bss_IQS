package com.bss.iqs.service;


import com.baomidou.mybatisplus.service.IService;
import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.entity.DataQueryGroup;
import com.bss.iqs.entity.DataQuerySql;
import com.bss.iqs.entity.DataTemplate;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hgh
 * @since 2017-09-06
 */
public interface IDataTemplateService extends IService<DataTemplate> {
     List<DataQueryGroup> getDataQueryGroup();

     List<DataQuerySql> findDqsByDqgId(Integer dataQueryGroupId);

     ResultBean saveDataTemplate(DataTemplate dataTemplate);

     ResultBean updateDataTemplate(DataTemplate dataTemplate);

     ResultBean deleteDataTemplate(Integer id);

     DataTemplate findTemplateById(Integer id);

}
