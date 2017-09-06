package com.bss.iqs.controller;


import com.bss.iqs.bean.AddGroupPermissionBean;
import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.entity.GroupPermission;
import com.bss.iqs.service.IGroupPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hgh
 * @since 2017-08-28
 */
@Controller
@RequestMapping("/groupPermission")
public class GroupPermissionController {

    @Autowired
    private IGroupPermissionService groupPermissionService;

    @PostMapping(value = "/save")
    //@RequiresPermissions("grouppermission:add")
    public ResultBean saveGroupPermission(GroupPermission groupPermission){
        ResultBean resultBean = groupPermissionService.saveGroupPermission(groupPermission);
        return resultBean;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    //@RequiresPermissions("grouppermission:update")
    public ResultBean updateGroupPermission(GroupPermission groupPermission){
        ResultBean resultBean = groupPermissionService.updateGroupPermission(groupPermission);
        return resultBean;
    }



    @RequestMapping(value = "/get/{id}")
    //@RequiresPermissions("grouppermission:update")
    public GroupPermission getGroupPermission(@PathVariable Integer id){
        GroupPermission groupPermission = groupPermissionService.findGroupPermissionById(id);
        return groupPermission;
    }

    @RequestMapping(value = "/delete/{id}")
    //@RequiresPermissions("grouppermission:delete")
    public ResultBean deleteGroupPermission(@PathVariable Integer id){
        ResultBean resultBean = groupPermissionService.deleteGroupPermission(id);
        return resultBean;
    }

    @RequestMapping("/getPermissionAndGroup")
    //@RequiresPermissions("grouppermission:add")
    public ModelAndView getPermissionAndGroup(){
        AddGroupPermissionBean permissionAndGroup = groupPermissionService.getPermissionAndGroup();
        ModelAndView modelAndView = new ModelAndView("addPermission");
        modelAndView.addObject("permissionAndGroup","");
        return modelAndView;
    }
}
