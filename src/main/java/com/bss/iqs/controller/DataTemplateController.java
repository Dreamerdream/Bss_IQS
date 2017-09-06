package com.bss.iqs.controller;


import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.entity.DataQueryGroup;
import com.bss.iqs.entity.DataQuerySql;
import com.bss.iqs.entity.DataTemplate;
import com.bss.iqs.service.IDataTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hgh
 * @since 2017-09-06
 */
@Controller
@RequestMapping("/dataTemplate")
public class DataTemplateController {
    @Autowired
    private IDataTemplateService dataTemplateService;

    @RequestMapping("/getDataQueryGroup")
    //@RequiresPermissions("template:add")
    public ModelAndView getDataQueryGroup(){
        ModelAndView modelAndView = new ModelAndView("");
        List<DataQueryGroup> dataQueryGroups = dataTemplateService.getDataQueryGroup();
        modelAndView.addObject("dataQueryGroups",dataQueryGroups);
        return modelAndView;
    }

    @RequestMapping("/getDataQuerySql/{dataQueryGroupId}")
    //@RequiresPermissions("template:add")
    public List<DataQuerySql> getDataQuerySql(@PathVariable Integer dataQueryGroupId){
        List<DataQuerySql> dqsByDqgId = dataTemplateService.findDqsByDqgId(dataQueryGroupId);
        return dqsByDqgId;
    }

    @PostMapping("/save")
    @ResponseBody
    //@RequiresPermissions("template:add")
    public ResultBean saveDataTemplate(DataTemplate template){
        ResultBean resultBean = dataTemplateService.saveDataTemplate(template);
        return resultBean;
    }




    @PostMapping("/update")
    @ResponseBody
    //@RequiresPermissions("template:update")
    public ResultBean updateTemplate(DataTemplate template){
        ResultBean resultBean = dataTemplateService.updateDataTemplate(template);
        return resultBean;
    }


    @GetMapping("/delete/{id}")
    @ResponseBody
    //@RequiresPermissions("template:delete")
    public ResultBean deleteTemplate(@PathVariable Integer id){
        ResultBean resultBean = dataTemplateService.deleteDataTemplate(id);
        return resultBean;
    }

    @GetMapping("/get/{id}")
    //@RequiresPermissions("template:update")
    public ModelAndView getTemplate(@PathVariable Integer id){
        DataTemplate template = dataTemplateService.findTemplateById(id);
        ModelAndView modelAndView = new ModelAndView("");
        modelAndView.addObject("dataTemplate",template);
        return modelAndView;
    }
}
