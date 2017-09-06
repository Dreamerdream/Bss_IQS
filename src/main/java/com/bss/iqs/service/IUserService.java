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
     ResultBean saveUser(User user);

     ResultBean deleteUser(Integer id);

     ResultBean updateUser(User user);

     User getUser(Integer id);


   // public PageBean queryUser(String type, String keyword, Integer pageNum, Integer pageSize);

     AddUserBean getDepartmentAndUserGroup();

     ActiveUser saveLoginRecord(ActiveUser user);



     User findUserByUsername(String username);

     ResultBean addPermission(String username,String permissionStatus,String permission,String permissionName,String url,Integer groupPermissionId);

     ResultBean updatePermission(Integer userId,String permissionStatus,String permission,String permissionName,String url,Integer groupPermissionId);
}
