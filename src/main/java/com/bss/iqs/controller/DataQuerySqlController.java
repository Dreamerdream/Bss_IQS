package com.bss.iqs.controller;


import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.entity.DataQueryGroup;
import com.bss.iqs.entity.DataQuerySql;
import com.bss.iqs.entity.DataQueryTask;
import com.bss.iqs.service.IDataQuerySqlService;
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
 * @since 2017-08-25
 */
@Controller
@RequestMapping("/dataQuerySql")
public class DataQuerySqlController {

    @Autowired
    private IDataQuerySqlService dataQuerySqlService;


    @GetMapping("/save")
    @ResponseBody
    //@RequiresPermissions("dataquerysql:add")
    public ResultBean saveDataQuerySql(DataQuerySql dataQuerySql){
        ResultBean resultBean = dataQuerySqlService.saveDataQuerySql(dataQuerySql);
        return resultBean;
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    //@RequiresPermissions("dataquerysql:delete")
    public ResultBean deleteDataQuerySql(@PathVariable Integer id){
        System.out.println("111111");
        ResultBean resultBean = dataQuerySqlService.deleteDataQuerySql(id);
        return resultBean;
    }

    @GetMapping("/update")
    @ResponseBody
    //@RequiresPermissions("dataquerysql:update")
    public ResultBean updateDataQuerySql(DataQuerySql dataQuerySql){
        ResultBean resultBean = dataQuerySqlService.updateDataQuerySql(dataQuerySql);
        return resultBean;
    }


    @RequestMapping("/get/{id}")
    //@RequiresPermissions("dataquerysql:update")
    public ModelAndView findDataQuerySqlById(@PathVariable Integer id){
        DataQuerySql dataQuerySql = dataQuerySqlService.findDataQuerySqlById(id);
        ModelAndView modelAndView = new ModelAndView("update");
        modelAndView.addObject("user",dataQuerySql);
        return  modelAndView;
    }

    @RequestMapping("/getDataQueryGroup")
    //@RequiresPermissions("dataquerysql:add")
    public ModelAndView getDataQueryGroup(){
        List<DataQueryGroup> dataQueryGroups = dataQuerySqlService.findAllDataQueryGroup();
        ModelAndView modelAndView = new ModelAndView("");
        modelAndView.addObject("dataQueryGroups",dataQueryGroups);
        return modelAndView;
    }

    @RequestMapping("/query/{type}/{keyword}/")
    //@RequiresPermissions("dataquerysql:query")
    public List<DataQuerySql> queryDataQuerySql(@PathVariable Integer dataQueryGroupId,@PathVariable String keyword){
        List<DataQuerySql> dataQuerySqls = dataQuerySqlService.queryDataQuerySql(dataQueryGroupId, keyword);
//        ModelAndView modelAndView = new ModelAndView("updateUser");
//        modelAndView.addObject("user",dataQuerySqls);
        return  dataQuerySqls;

    }
	
}
