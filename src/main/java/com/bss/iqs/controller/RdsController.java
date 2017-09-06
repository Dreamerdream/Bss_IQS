package com.bss.iqs.controller;


import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.entity.Rds;
import com.bss.iqs.service.IRdsService;
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
@RequestMapping("/rds")
public class RdsController {


    @Autowired
    private IRdsService rdsService;

    @GetMapping("/save")
    @ResponseBody
    //@RequiresPermissions("rds:add")
    public ResultBean saveRDS(Rds rds){
        ResultBean resultBean = rdsService.saveRDS(rds);
        return resultBean;
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    //@RequiresPermissions("rds:delete")
    public ResultBean deleteRDS(@PathVariable Integer id){
        System.out.println("111111");
        ResultBean resultBean = rdsService.deleteRDS(id);
        return resultBean;
    }

    @GetMapping("/update")
    @ResponseBody
    //@RequiresPermissions("rds:update")
    public ResultBean updateRDS(Rds rds){
        ResultBean resultBean = rdsService.updateRDS(rds);
        return resultBean;
    }


    @GetMapping("/get/{id}")
    //@RequiresPermissions("rds:update")
    public ModelAndView getRDS(@PathVariable Integer id){
        Rds rds = rdsService.getRDS(id);
        ModelAndView modelAndView = new ModelAndView("updateRDS");
        modelAndView.addObject("rds",rds);
        return  modelAndView;
    }

    @GetMapping("/queryAll")
    @ResponseBody
    //@RequiresPermissions("rds:query")
    public List<Rds> queryRDS(){
        List<Rds> rds = rdsService.queryAllRDS();
//        ModelAndView modelAndView = new ModelAndView("update");
//        modelAndView.addObject("rds",rds);
        return rds;

    }

	
}
