package com.bss.iqs.controller;


import com.bss.iqs.entity.PlanTaskRecord;
import com.bss.iqs.service.IPlanTaskRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hgh
 * @since 2017-09-04
 */
@Controller
@RequestMapping("/planTaskRecord")
public class PlanTaskRecordController {

    @Autowired
    private IPlanTaskRecordService planTaskRecordService;

    @RequestMapping("/getPlanTaskRecord/{dataQueryGroupId}/{keyword}/{pageNum}/{pageSize}")

    public ModelAndView getPlanTaskRecord(@PathVariable Integer dataQueryGroupId, @PathVariable String keyword, @PathVariable Integer pageNum, @PathVariable Integer pageSize){
        List<PlanTaskRecord> planTaskRecords = planTaskRecordService.getPlanTaskRecord(dataQueryGroupId, keyword, pageNum, pageSize);
        ModelAndView modelAndView = new ModelAndView("");
        modelAndView.addObject("planTaskRecords",planTaskRecords);
        return modelAndView;
    }
	
}
