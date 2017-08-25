package com.bss.iqs.controller;


import com.bss.iqs.bean.Result;
import com.bss.iqs.bean.UserLoginResult;
import com.bss.iqs.bean.UserResult;
import com.bss.iqs.entity.User;
import com.bss.iqs.service.IUserService;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/save")
    public void saveUser(User user){
        userService.saveUser(user);
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable Integer id){
        System.out.println("111111");
        userService.deleteUser(id);
    }

    @GetMapping("/update")
    @ResponseBody
    public void updateUser(User user){
        userService.updateUser(user);
    }


    @RequestMapping("/get/{id}")
    public ModelAndView getUser(@PathVariable Integer id){
        User user = userService.getUser(id);
        ModelAndView modelAndView = new ModelAndView("update");
        modelAndView.addObject("user",user);
        return  modelAndView;
    }


    @RequestMapping("/query/{type}/{keyword}/{pageNum}/{pageSize}")
    public ModelAndView queryUser(@PathVariable String type,@PathVariable String keyword,@PathVariable Integer pageNum,@PathVariable Integer pageSize){
        User user = userService.queryUser(type,keyword,pageNum,pageSize);
        ModelAndView modelAndView = new ModelAndView("updateUser");
        modelAndView.addObject("user",user);
        return  modelAndView;
    }

    @RequestMapping("/beforeAdd")

    public ModelAndView getDepartmentAndUserGroup(){
        UserResult departmentAndUserGroup = userService.getDepartmentAndUserGroup();
        ModelAndView modelAndView = new ModelAndView("addUser");
        modelAndView.addObject("departmentAndUserGroup",departmentAndUserGroup);
        return  modelAndView;
    }

    @RequestMapping("/login/username/password")
    @ResponseBody
    public Result login(@PathVariable String username, @PathVariable String password){
        Result result = userService.login(username, password);
        return result;
    }

    @RequestMapping("/queryAll")
    @ResponseBody
    public List<UserLoginResult> queryAll(){
        List<UserLoginResult> userLoginResults = userService.queryAll();
        return userLoginResults;
    }
	
}
