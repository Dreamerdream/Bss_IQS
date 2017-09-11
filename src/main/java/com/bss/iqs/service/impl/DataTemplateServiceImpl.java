package com.bss.iqs.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.entity.DataQueryGroup;
import com.bss.iqs.entity.DataQuerySql;
import com.bss.iqs.entity.DataTemplate;
import com.bss.iqs.entity.DataTemplateSql;
import com.bss.iqs.mapper.DataQueryGroupMapper;
import com.bss.iqs.mapper.DataQuerySqlMapper;
import com.bss.iqs.mapper.DataTemplateMapper;
import com.bss.iqs.mapper.DataTemplateSqlMapper;
import com.bss.iqs.service.IDataTemplateService;
import com.bss.iqs.util.FileUtils;
import groovy.util.IFileNameFinder;
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
 * @since 2017-09-06
 */
@Service
public class DataTemplateServiceImpl extends ServiceImpl<DataTemplateMapper, DataTemplate> implements IDataTemplateService {
    @Autowired
    private DataQueryGroupMapper dataQueryGroupMapper;
    @Autowired
    private DataQuerySqlMapper dataQuerySqlMapper;

    @Autowired
    private DataTemplateMapper dataTemplateMapper;

    @Autowired
    private DataTemplateSqlMapper dataTemplateSqlMapper;

    //添加的时候用，找到分组
    @Override
    public List<DataQueryGroup> getDataQueryGroup() {
        Wrapper<DataQueryGroup> dataQueryGroupWrapper = new EntityWrapper<>();
        List<DataQueryGroup> dataQueryGroups = dataQueryGroupMapper.selectList(dataQueryGroupWrapper);
        if (dataQueryGroups != null && dataQueryGroups.size() != 0){
            return dataQueryGroups;
        }
        return null;
    }

    //根据分组选埋点
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
    public ResultBean saveDataTemplate(DataTemplate template) {
        Date date = new Date();
        template.setCreateTime(date);
        template.setUpdateTime(date);
        Integer insert = dataTemplateMapper.insert(template);
        if (insert != null){
            String sqlId = template.getSqlId();
            String[] split = sqlId.split(",");
            //插入主子表
            if (split != null && split.length != 0) {
                for (int i = 0; i < split.length; i++) {
                    DataTemplateSql dataTemplateSql = new DataTemplateSql();
                    dataTemplateSql.setSqlId(Integer.valueOf(split[i]));
                    dataTemplateSql.setTemplateId(insert);
                    dataTemplateSqlMapper.insert(dataTemplateSql);
                }
            }
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("添加成功");
            //将输入的内容转化为ftl文件
            FileUtils.templat2ftl(template.getContent(),insert);
            return result;
        }
        return null;
    }

    @Transactional
    @Override
    public ResultBean updateDataTemplate(DataTemplate template) {
        template.setUpdateTime(new Date());
        Integer integer = dataTemplateMapper.updateById(template);
        if (integer != null){
            //更新主子表
            //删除
            Wrapper<DataTemplateSql> sqlWrapper = new EntityWrapper<>();
            sqlWrapper.eq("templateId",template.getId());
            dataTemplateSqlMapper.delete(sqlWrapper);
            //重新添加
            String sqlId = template.getSqlId();
            String[] split = sqlId.split(",");
            //插入主子表
            if (split != null && split.length != 0){
                for (int i = 0; i < split.length ; i++) {
                    DataTemplateSql dataTemplateSql = new DataTemplateSql();
                    dataTemplateSql.setSqlId(Integer.valueOf(split[i]));
                    dataTemplateSql.setTemplateId(template.getId());
                    dataTemplateSqlMapper.insert(dataTemplateSql);
                }
            }

            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("更新成功");
            //将输入的内容转化为ftl文件
            FileUtils.templat2ftl(template.getContent(),integer);
            return result;
        }
        return null;
    }
    @Transactional
    @Override
    public ResultBean deleteDataTemplate(Integer id) {
        Integer integer = dataTemplateMapper.deleteById(id);
        if (integer != null){
            //删除主子表中的记录
            Wrapper<DataTemplateSql> dataTemplateSqlWrapper = new EntityWrapper<>();
            dataTemplateSqlWrapper.eq("templateId",id);
            Integer delete = dataTemplateSqlMapper.delete(dataTemplateSqlWrapper);
            if (delete != null){
                ResultBean result = new ResultBean();
                result.setErrorCode(0);
                result.setErrorReason("删除成功");
                return result;
            }
        }
        return null;
    }


    @Override
    public DataTemplate findTemplateById(Integer id) {
        Wrapper<DataTemplateSql> dataTemplateSqlWrapper = new EntityWrapper<>();
        dataTemplateSqlWrapper.eq("templateId",id);
        List<DataTemplateSql> dataTemplateSqls = dataTemplateSqlMapper.selectList(dataTemplateSqlWrapper);
        if (dataTemplateSqls != null && dataTemplateSqls.size() != 0){
            for (int i = 0; i < dataTemplateSqls.size() ; i++) {
                DataTemplateSql dataTemplateSql = dataTemplateSqls.get(i);
                //得到sql描述,即埋点,为了显示
                DataQuerySql dataQuerySql = dataQuerySqlMapper.selectById(dataTemplateSql.getSqlId());

            }
        }
        return dataTemplateMapper.selectById(id);
    }

}
