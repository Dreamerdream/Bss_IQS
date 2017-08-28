package com.bss.iqs.controller;



import com.bss.iqs.bean.AddUserBean;
import com.bss.iqs.bean.QueryUserLoginRecordBean;
import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.entity.QueryUserLogin;
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
    @ResponseBody
    public ResultBean saveUser(User user){
        ResultBean resultBean = userService.saveUser(user);
        return resultBean;
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public ResultBean deleteUser(@PathVariable Integer id){
        System.out.println("111111");
        ResultBean resultBean = userService.deleteUser(id);
        return resultBean;

    }

    @GetMapping("/update")
    @ResponseBody
    public ResultBean updateUser(User user){
        ResultBean resultBean = userService.updateUser(user);
        return resultBean;
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
        List<QueryUserLogin> queryUserLogins = userService.queryUser(type, keyword, pageNum, pageSize);
        ModelAndView modelAndView = new ModelAndView("updateUser");
        modelAndView.addObject("queryUserLogins",queryUserLogins);
        return  modelAndView;
    }

    @RequestMapping("/beforeAdd")
    public ModelAndView getDepartmentAndUserGroup(){
        AddUserBean departmentAndUserGroup = userService.getDepartmentAndUserGroup();
        ModelAndView modelAndView = new ModelAndView("addUser");
        modelAndView.addObject("departmentAndUserGroup",departmentAndUserGroup);
        return  modelAndView;
    }

    //交给shiro处理
    @RequestMapping("/login/username/password")
    @ResponseBody
    public ResultBean login(@PathVariable String username, @PathVariable String password){
        ResultBean login = userService.login(username, password);
        return login;
    }

    @RequestMapping("/queryAll")
    @ResponseBody
    public ModelAndView queryAll(){
        List<QueryUserLoginRecordBean> queryUserLoginRecordBeans = userService.queryAll();
        ModelAndView modelAndView = new ModelAndView("");
        return modelAndView;
    }
	
}
