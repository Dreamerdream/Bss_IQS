package com.bss.iqs.service;


import com.baomidou.mybatisplus.service.IService;
import com.bss.iqs.bean.Result;
import com.bss.iqs.bean.UserLoginResult;
import com.bss.iqs.bean.UserResult;
import com.bss.iqs.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hgh
 * @since 2017-08-25
 */
public interface IUserService extends IService<User> {
    public void saveUser(User user);

    public void deleteUser(Integer id);

    public void updateUser(User user);

    public User getUser(Integer id);


    public User queryUser(String type,String keyword,Integer pageNum,Integer pageSize);

    public UserResult getDepartmentAndUserGroup();

    public Result login(String username, String password);

    public List<UserLoginResult> queryAll();
}
