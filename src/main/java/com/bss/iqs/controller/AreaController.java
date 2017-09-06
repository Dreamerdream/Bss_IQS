package com.bss.iqs.controller;


import com.bss.iqs.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 * InnoDB free: 11264 kB 前端控制器
 * </p>
 *
 * @author hgh
 * @since 2017-08-26
 */
@Controller
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private IAreaService areaService;

    @RequestMapping("/getcitys/{parentId}")
    @ResponseBody
    public List<String> getCitys(@PathVariable Integer parentId){

        List<String> citys = areaService.getCitys(parentId);
//        ModelAndView modelAndView = new ModelAndView("");
//        modelAndView.addObject("citys",citys);
        return citys;
    }
	
}
