package com.bss.iqs.controller;


import com.bss.iqs.bean.AddDataQueryTaskBean;
import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.entity.DataQueryTask;
import com.bss.iqs.service.IDataQueryTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 * InnoDB free: 3072 kB 前端控制器
 * </p>
 *
 * @author hgh
 * @since 2017-08-26
 */
@Controller
@RequestMapping("/dataQueryTask")
public class DataQueryTaskController {
    @Autowired
    private IDataQueryTaskService queryTaskService;

    @GetMapping("/save")
    @ResponseBody
    //@RequiresPermissions("dataquerytask:add")
    public ResultBean saveDataQueryTask(DataQueryTask dataQueryTask){
        ResultBean resultBean = queryTaskService.saveDataQueryTask(dataQueryTask);
        return resultBean;
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    //@RequiresPermissions("dataquerytask:delete")
    public ResultBean deleteDataQueryTask(@PathVariable Integer id){
        System.out.println("111111");
        ResultBean resultBean = queryTaskService.deleteDataQueryTask(id);
        return resultBean;
    }

    @GetMapping("/update")
    @ResponseBody
    //@RequiresPermissions("dataquerytask:update")
    public ResultBean updateDataQueryTask(DataQueryTask dataQueryTask){
        ResultBean resultBean = queryTaskService.updateDataQueryTask(dataQueryTask);
        return resultBean;
    }


    @RequestMapping("/get/{id}")
    //@RequiresPermissions("dataquerytask:update")
    public ModelAndView getDataQueryTask(@PathVariable Integer id){
        DataQueryTask dataQueryTask = queryTaskService.findDataQueryTaskById(id);
        ModelAndView modelAndView = new ModelAndView("update");
        modelAndView.addObject("user",dataQueryTask);
        return  modelAndView;
    }


    @RequestMapping("/query/{type}/{keyword}")
    //@RequiresPermissions("dataquerytask:query")
    public ModelAndView queryDataQueryTask(@PathVariable Integer type,@PathVariable String keyword){
        List<DataQueryTask> dataQueryTasks = queryTaskService.queryDataQueryTask(type, keyword);
        ModelAndView modelAndView = new ModelAndView("updateUser");
        modelAndView.addObject("user",dataQueryTasks);
        return  modelAndView;

    }

    @RequestMapping("/getTemplateAndAddress")
    public ModelAndView getTemplateAndAddress(){
        AddDataQueryTaskBean templateAndAddress = queryTaskService.getTemplateAndAddress();
        ModelAndView modelAndView = new ModelAndView("");
        modelAndView.addObject("templateAndAddress",templateAndAddress);
        return  modelAndView;
    }




}
