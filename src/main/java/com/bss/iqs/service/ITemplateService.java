package com.bss.iqs.service;


import com.baomidou.mybatisplus.service.IService;
import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.entity.DataQueryGroup;
import com.bss.iqs.entity.DataQuerySql;
import com.bss.iqs.entity.Template;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hgh
 * @since 2017-08-28
 */
public interface ITemplateService extends IService<Template> {


    public List<DataQueryGroup> getDataQueryGroup();

    public List<DataQuerySql> findDqsByDqgId(Integer dataQueryGroupId);

    public ResultBean saveTemplate(Template template);

    public ResultBean updateTemplate(Template template);

    public ResultBean deleteTemplate(Integer id);

    public Template findTemplateById(Integer id);
	
}
