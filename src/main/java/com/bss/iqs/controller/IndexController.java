package com.bss.iqs.controller;


import com.bss.iqs.bean.ActiveUser;
import com.bss.iqs.bean.IndexBean;
import com.bss.iqs.entity.User;
import com.bss.iqs.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private IUserService userService;
    @GetMapping(value = {"/index","/"})
    public String index(Model model){

        Subject subject = SecurityUtils.getSubject();
        //得到要传到主页的信息
        ActiveUser activeUser = (ActiveUser)subject.getPrincipal();
        activeUser = userService.saveLoginRecord(activeUser);
        model.addAttribute("activeUser",activeUser);
        return "index";
    }

//    @GetMapping("/error")
//    public String error(){
//
//
//        return "error";
//    }

    @GetMapping("/logout")
    @RequestMapping
    public String logout(){


        SecurityUtils.getSubject().logout();

        return "login";
    }

    //交给shiro处理
    @PostMapping(value = "/login")
    public String login(User user, HttpServletRequest request, Map<String, Object> map){
//        ResultBean login = userService.login(username, password);
//        return login;
        System.out.println("---------登录");
        String exception = (String) request.getAttribute("shiroLoginFailure");
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
            }
            map.put("msg", msg);
            return "login";
        }


        // 此方法不处理登录成功,由shiro进行处理.
        return "index";
    }

}
