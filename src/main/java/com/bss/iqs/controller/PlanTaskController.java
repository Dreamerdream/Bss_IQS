package com.bss.iqs.controller;


import com.bss.iqs.bean.AddPlanTaskBean;
import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.entity.DataQueryGroup;
import com.bss.iqs.entity.DataQueryTask;
import com.bss.iqs.entity.PlanTask;
import com.bss.iqs.service.IPlanTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hgh
 * @since 2017-08-29
 */
@Controller
@RequestMapping("/planTask")
public class PlanTaskController {


    @Autowired
    private IPlanTaskService planTaskService;


    @GetMapping("/save")
    @ResponseBody
    //@RequiresPermissions("plantask:add")
    public ResultBean saveRDS(PlanTask planTask){
        ResultBean resultBean = planTaskService.savePlanTask(planTask);
        return resultBean;
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    //@RequiresPermissions("plantask:delete")
    public ResultBean deletePlanTask(@PathVariable Integer id){
        System.out.println("111111");
        ResultBean resultBean = planTaskService.deletePlanTask(id);
        return resultBean;
    }

    @GetMapping("/update")
    @ResponseBody
    //@RequiresPermissions("plantask:update")
    public ResultBean updatePlanTask(PlanTask planTask){
        ResultBean resultBean = planTaskService.updatePlanTask(planTask);
        return resultBean;
    }


    @GetMapping("/get/{id}")
    //@RequiresPermissions("plantask:update")
    @ResponseBody
    public PlanTask findPlanTaskById(@PathVariable Integer id){
        PlanTask planTask = planTaskService.findPlanTaskById(id);
//        ModelAndView modelAndView = new ModelAndView("updatePlanTask");
//        modelAndView.addObject("updatePlanTask",planTask);
        return  planTask;
    }

//    @GetMapping("/query/{groupId}/{keyword}/{pageNum}/{pageSize}")
//    public ModelAndView queryRDS(@PathVariable Integer groupId,@PathVariable String keyword,@PathVariable Integer pageNum,@PathVariable Integer pageSize){
//        List<Rds> rds = rdsService.queryRDS(groupId, keyword,pageNum,pageSize);
//        ModelAndView modelAndView = new ModelAndView("update");
//        modelAndView.addObject("sql",pageSize);
//        return modelAndView;
//
//    }

    @GetMapping("/findAllDataQueryGroup")
    //@RequiresPermissions("plantask:add")
    @ResponseBody
    public List<DataQueryGroup> findAllDataQueryGroup(){
        List<DataQueryGroup> dataQueryGroup = planTaskService.findAllDataQueryGroup();
//        ModelAndView modelAndView = new ModelAndView("");
//        modelAndView.addObject("dataQueryGroup",dataQueryGroup);
        return dataQueryGroup;
    }


    //用于添加
    @GetMapping("/getDataQueryTask/{dataQueryGroupId}")
    //@RequiresPermissions("plantask:add")
    @ResponseBody
    public List<AddPlanTaskBean> findDataQueryTaskByDqgId(@PathVariable Integer dataQueryGroupId){
        List<AddPlanTaskBean> addPlanTaskBeans = planTaskService.findDataQueryTaskByDqgId(dataQueryGroupId);
//        ModelAndView modelAndView = new ModelAndView("");
//        modelAndView.addObject("addPlanTaskBeans",addPlanTaskBeans);
        return addPlanTaskBeans;
    }


    //查询
    @GetMapping("/getPlanTask/{dataQueryGroupId}/{type}")
    @ResponseBody
    //@RequiresPermissions("plantask:query")
    public  List<PlanTask> findPlanTaskByDqgId(@PathVariable Integer dataQueryGroupId,@PathVariable String type){
        List<PlanTask> planTaskByDqgId = planTaskService.findPlanTaskByDqgId(dataQueryGroupId, type);
        return planTaskByDqgId;
    }


}
