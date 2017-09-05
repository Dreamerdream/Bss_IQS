package com.bss.iqs.controller;


import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.entity.DataQueryGroup;
import com.bss.iqs.entity.DataQueryTask;
import com.bss.iqs.service.IDataQueryGroupService;
import com.bss.iqs.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
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
@RequestMapping("/dataQueryGroup")
public class DataQueryGroupController {

    @Autowired
    private IDataQueryGroupService dataQueryGroupService;

    @RequestMapping("/getdataQueryTasks")
    private ModelAndView getdataQueryTasks(){
        List<DataQueryTask> dataQueryTasks = dataQueryGroupService.findAllDataQueryTasks();
        ModelAndView modelAndView = new ModelAndView("Add");
        modelAndView.addObject("dataQueryTasks",dataQueryTasks);
        return modelAndView;
    }

    @PostMapping("/save")
    @ResponseBody
    private ResultBean savedataQueryGroup(@RequestParam("file") MultipartFile file,String dataQueryGroupName,Integer dataQueryTaskId){
        if (file.isEmpty()) {
            return new ResultBean(1,"文件不能为空");
        }

        String path = "D:\\";
        String filePath = FileUtils.uploadFile(file, path);
        ResultBean result = null;
        if (filePath != null){
            result = dataQueryGroupService.savedataQueryGroup(filePath,dataQueryGroupName,dataQueryTaskId);

        }else {
            result = new ResultBean(1,"文件保存失败");
        }


        return result;
    }
}
