package com.bss.iqs.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.entity.DataQueryGroup;
import com.bss.iqs.entity.DataQuerySql;
import com.bss.iqs.entity.Template;
import com.bss.iqs.mapper.DataQueryGroupMapper;
import com.bss.iqs.mapper.DataQuerySqlMapper;
import com.bss.iqs.mapper.TemplateMapper;
import com.bss.iqs.service.ITemplateService;
import com.bss.iqs.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hgh
 * @since 2017-08-28
 */
@Service
public class TemplateServiceImpl extends ServiceImpl<TemplateMapper, Template> implements ITemplateService {

    @Autowired
    private DataQueryGroupMapper dataQueryGroupMapper;
    @Autowired
    private DataQuerySqlMapper dataQuerySqlMapper;

    @Autowired
    private TemplateMapper templateMapper;

    @Override
    public List<DataQueryGroup> getDataQueryGroup() {
        Wrapper<DataQueryGroup> dataQueryGroupWrapper = new EntityWrapper<>();
        List<DataQueryGroup> dataQueryGroups = dataQueryGroupMapper.selectList(dataQueryGroupWrapper);
        if (dataQueryGroups != null && dataQueryGroups.size() != 0){
            return dataQueryGroups;
        }
        return null;
    }

    @Override
    public List<DataQuerySql> findDqsByDqgId(Integer dataQueryGroupId) {
        Wrapper<DataQuerySql> dataQuerySqlWrapper = new EntityWrapper<>();
        dataQuerySqlWrapper.eq("dataQueryGroupId",dataQueryGroupId);
        List<DataQuerySql> dataQuerySqls = dataQuerySqlMapper.selectList(dataQuerySqlWrapper);
        if (dataQuerySqls != null && dataQuerySqls.size() != 0){
            return dataQuerySqls;
        }
        return null;
    }
    @Transactional
    @Override
    public ResultBean saveTemplate(Template template) {
        Date date = new Date();
        template.setCreateTime(date);
        template.setUpdateTime(date);
        Integer insert = templateMapper.insert(template);
        if (insert != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("添加成功");
            //将输入的内容转化为ftl文件
            FileUtils.templat2ftl(template.getContent(),"");
            return result;
        }
        return null;
    }

    @Transactional
    @Override
    public ResultBean updateTemplate(Template template) {
        template.setUpdateTime(new Date());
        Integer integer = templateMapper.updateById(template);
        if (integer != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("更新成功");
            return result;
        }
        return null;
    }
    @Transactional
    @Override
    public ResultBean deleteTemplate(Integer id) {
        Integer integer = templateMapper.deleteById(id);
        if (integer != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("删除成功");
            return result;
        }
        return null;
    }

    @Override
    public Template findTemplateById(Integer id) {
        return null;
    }
}
