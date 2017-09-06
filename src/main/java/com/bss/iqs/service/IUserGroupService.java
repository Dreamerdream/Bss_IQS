package com.bss.iqs.service;


import com.baomidou.mybatisplus.service.IService;
import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.entity.UserGroup;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hgh
 * @since 2017-08-25
 */
public interface IUserGroupService extends IService<UserGroup> {

     ResultBean saveUserGroup(UserGroup userGroup);

     ResultBean deleteUserGroup(Integer id);

     ResultBean updateUserGroup(UserGroup userGroup);

     UserGroup getUserGroup(Integer id);
}
