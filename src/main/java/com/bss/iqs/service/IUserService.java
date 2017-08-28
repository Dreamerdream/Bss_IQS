package com.bss.iqs.service;


import com.baomidou.mybatisplus.service.IService;
import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.bean.QueryUserLoginRecordBean;
import com.bss.iqs.bean.AddUserBean;
import com.bss.iqs.entity.QueryUserLogin;
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
    public ResultBean saveUser(User user);

    public ResultBean deleteUser(Integer id);

    public ResultBean updateUser(User user);

    public User getUser(Integer id);


    public List<QueryUserLogin> queryUser(String type, String keyword, Integer pageNum, Integer pageSize);

    public AddUserBean getDepartmentAndUserGroup();

    public ResultBean login(String username, String password);

    public List<QueryUserLoginRecordBean> queryAll();
}
