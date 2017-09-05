package com.bss.iqs.controller;


import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.entity.DataQueryGroup;
import com.bss.iqs.entity.DataQuerySql;
import com.bss.iqs.entity.Template;
import com.bss.iqs.service.ITemplateService;
import com.bss.iqs.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hgh
 * @since 2017-08-28
 */
@Controller
@RequestMapping("/template")
public class TemplateController {


    @Autowired
    private ITemplateService templateService;

    @RequestMapping("/getDataQueryGroup")
    public ModelAndView getDataQueryGroup(){
        ModelAndView modelAndView = new ModelAndView("");
        List<DataQueryGroup> dataQueryGroups = templateService.getDataQueryGroup();
        modelAndView.addObject("dataQueryGroups",dataQueryGroups);
        return modelAndView;
    }

    @RequestMapping("/getDataQuerySql/{templateId}")
    public List<DataQuerySql> getDataQuerySql(@PathVariable Integer dataQueryGroupId){
        List<DataQuerySql> dqsByDqgId = templateService.findDqsByDqgId(dataQueryGroupId);
        return null;
    }

    @PostMapping("/save")
    public ResultBean saveTemplate(Template template){
        ResultBean resultBean = templateService.saveTemplate(template);
        return resultBean;
    }


    @PostMapping("/update")
    public ResultBean updateTemplate(Template template){
        ResultBean resultBean = templateService.updateTemplate(template);
        return resultBean;
    }


    @GetMapping("/delete/{id}")
    public ResultBean deleteTemplate(@PathVariable Integer id){
        ResultBean resultBean = templateService.deleteTemplate(id);
        return resultBean;
    }

    @GetMapping("/get/{id}")
    public Template getTemplate(@PathVariable Integer id){
        Template template = templateService.findTemplateById(id);
        return template;
    }
}
