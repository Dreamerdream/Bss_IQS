package com.bss.iqs.controller;


import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.entity.UserGroup;
import com.bss.iqs.service.IUserGroupService;
import com.bss.iqs.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hgh
 * @since 2017-08-25
 */
@Controller
@RequestMapping("/userGroup")
public class UserGroupController {

    @Autowired
    private IUserGroupService userGroupService;


    @GetMapping("/save")
    @ResponseBody
    public ResultBean SaveUserGroup(UserGroup userGroup){

        ResultBean resultBean = userGroupService.saveUserGroup(userGroup);
        return resultBean;

    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public ResultBean deleteUserGroup(@PathVariable Integer id){
        ResultBean resultBean = userGroupService.deleteUserGroup(id);
        return resultBean;
    }

    @GetMapping("/update")
    public ResultBean updateUserGroup(UserGroup userGroup){
        ResultBean resultBean = userGroupService.updateUserGroup(userGroup);
        return resultBean;
    }



    @RequestMapping("/get/{id}")
    public ModelAndView getUserGroup(@PathVariable Integer id){
        UserGroup userGroup = userGroupService.getUserGroup(id);
        ModelAndView modelAndView = new ModelAndView("update");
        modelAndView.addObject("userGroup",userGroup);
        return  modelAndView;
    }
	
}
