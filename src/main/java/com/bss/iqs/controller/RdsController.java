package com.bss.iqs.controller;


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

    @GetMapping("/save/")
    @ResponseBody
    public void saveRDS(Rds rds){
        rdsService.saveRDS(rds);
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public void deleteRDS(@PathVariable Integer id){
        System.out.println("111111");
        rdsService.deleteRDS(id);
    }

    @GetMapping("/update")
    @ResponseBody
    public void updateRDS(Rds rds){
        rdsService.updateRDS(rds);
    }


    @GetMapping("/get/{id}")
    public ModelAndView getRDS(@PathVariable Integer id){
        Rds rds = rdsService.getRDS(id);
        ModelAndView modelAndView = new ModelAndView("updateRDS");
        modelAndView.addObject("rds",rds);
        return  modelAndView;
    }

    @GetMapping("/query/{groupId}/{keyword}/{pageNum}/{pageSize}")
    public ModelAndView queryRDS(@PathVariable Integer groupId,@PathVariable String keyword,@PathVariable Integer pageNum,@PathVariable Integer pageSize){
        List<Rds> rds = rdsService.queryRDS(groupId, keyword,pageNum,pageSize);
        ModelAndView modelAndView = new ModelAndView("update");
        modelAndView.addObject("sql",pageSize);
        return modelAndView;

    }

	
}
