package com.bss.iqs.controller;



import com.bss.iqs.bean.AddUserBean;
import com.bss.iqs.bean.PageBean;
import com.bss.iqs.bean.ResultBean;

import com.bss.iqs.entity.User;
import com.bss.iqs.service.IUserService;
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
 * @since 2017-08-25
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/save")
    public ResultBean saveUser(User user){
        ResultBean resultBean = userService.saveUser(user);
        return resultBean;
    }

    @GetMapping("/delete/{id}")
    public ResultBean deleteUser(@PathVariable Integer id){
        System.out.println("111111");
        ResultBean resultBean = userService.deleteUser(id);
        return resultBean;

    }

    @PostMapping("/update")
    public ResultBean updateUser(User user){
        ResultBean resultBean = userService.updateUser(user);
        return resultBean;
    }


    @GetMapping("/get/{id}")
    public ModelAndView getUser(@PathVariable Integer id){
        User user = userService.getUser(id);
        ModelAndView modelAndView = new ModelAndView("update");
        modelAndView.addObject("user",user);
        return  modelAndView;
    }


//    @GetMapping("/query/{type}/{keyword}/{pageNum}/{pageSize}")
//    public ModelAndView queryUser(@PathVariable String type,@PathVariable String keyword,@PathVariable Integer pageNum,@PathVariable Integer pageSize){
//        PageBean page = userService.queryUser(type, keyword, pageNum, pageSize);
//        ModelAndView modelAndView = new ModelAndView("");
//        modelAndView.addObject("page",page);
//        return  modelAndView;
//    }

    @GetMapping("/getDepartmentAndUserGroup")
    public ModelAndView getDepartmentAndUserGroup(){
        AddUserBean departmentAndUserGroup = userService.getDepartmentAndUserGroup();
        ModelAndView modelAndView = new ModelAndView("addUser");
        modelAndView.addObject("departmentAndUserGroup",departmentAndUserGroup);
        return  modelAndView;
    }



    @GetMapping("/queryAll")
    public ModelAndView queryAll(){
     //   List<QueryUserLoginRecordBean> queryUserLoginRecordBeans = userService.queryAll();
        ModelAndView modelAndView = new ModelAndView("");
        return modelAndView;
    }

    @RequestMapping("addPermission/{username}/{permissionStatus}/{permission}/{permissionName}/{url}/{groupPermissionId}")
    public ModelAndView addPermission(@PathVariable String username,@PathVariable String permissionStatus,@PathVariable String permission,@PathVariable String permissionName,@PathVariable String url,@PathVariable Integer groupPermissionId){
        userService.addPermission(username,permissionStatus,permission,permissionName,url,groupPermissionId);
        ModelAndView modelAndView = new ModelAndView("");
        return modelAndView;
    }


//    @PostMapping(value = "/addPermissions")
//    @RequiresPermissions("user:add")
//    public void addPermission(User user){
//        String permission = user.getPermission();
//        System.out.println(user.getPermission());
//        System.out.println(111111);
//    }

//    @PostMapping(value = "/testuploadimg")
//    public void testuploadimg(@RequestParam("file") MultipartFile file,String username){
//        String name = file.getName();
//        System.out.println(username);
//
//        System.out.println(name);
//
//        System.out.println(111111);
//    }


    @RequestMapping("updatePermission/{userId}/{permissionStatus}/{permission}/{permissionName}/{url}/{groupPermissionId}")
    public void updatePermission(@PathVariable Integer userId,@PathVariable String permissionStatus,@PathVariable String permission,@PathVariable String permissionName,@PathVariable String url,@PathVariable Integer groupPermissionId ){
        userService.updatePermission(userId,permissionStatus,permission,permissionName,url,groupPermissionId);
    }


//    @RequestMapping("/success")
//    public String success(){
//        return "success";
//    }
}
