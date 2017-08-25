package com.bss.iqs.controller;


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
    public void SaveUserGroup(UserGroup userGroup){

        userGroupService.saveUserGroup(userGroup);

    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public void deleteUserGroup(@PathVariable Integer id){
        userGroupService.deleteUserGroup(id);
    }

    @GetMapping("/update")
    public void updateUserGroup(UserGroup userGroup){
        userGroupService.updateUserGroup(userGroup);
    }



    @RequestMapping("/get/{id}")
    public ModelAndView getUserGroup(@PathVariable Integer id){
        UserGroup userGroup = userGroupService.getUserGroup(id);
        ModelAndView modelAndView = new ModelAndView("update");
        modelAndView.addObject("userGroup",userGroup);
        return  modelAndView;
    }
	
}
