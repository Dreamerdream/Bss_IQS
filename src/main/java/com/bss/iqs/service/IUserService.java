package com.bss.iqs.service;


import com.baomidou.mybatisplus.service.IService;
import com.bss.iqs.bean.*;

import com.bss.iqs.entity.User;

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


   // public PageBean queryUser(String type, String keyword, Integer pageNum, Integer pageSize);

    public AddUserBean getDepartmentAndUserGroup();

    public ActiveUser saveLoginRecord(ActiveUser user);



    public User findUserByUsername(String username);

    public ResultBean addPermission(String username,String permissionStatus,String permission,String permissionName,String url,Integer groupPermissionId);

    public ResultBean updatePermission(Integer userId,String permissionStatus,String permission,String permissionName,String url,Integer groupPermissionId);
}
