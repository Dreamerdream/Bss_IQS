package com.bss.iqs.controller;


import com.bss.iqs.entity.LoginRecord;
import com.bss.iqs.service.ILoginRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
 * @since 2017-08-25
 */
@Controller
@RequestMapping("/loginRecord")
public class LoginRecordController {


    @Autowired
    private ILoginRecordService loginRecordService;

    @RequestMapping("/getLoginRecord/{type}/{keyword}/{pageNum}/{pageSize}")
    public ModelAndView getLoginRecord(@PathVariable String type,@PathVariable String keyword,@PathVariable Integer pageNum,@PathVariable Integer pageSize){
        List<LoginRecord> userLoginRecord = loginRecordService.getUserLoginRecord(type, keyword, pageNum, pageSize);
        ModelAndView modelAndView = new ModelAndView("");
        modelAndView.addObject("userLoginRecord",userLoginRecord);
        return modelAndView;
    }
	
}
