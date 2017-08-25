package com.bss.iqs.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bss.iqs.entity.UserGroup;
import com.bss.iqs.mapper.UserGroupMapper;
import com.bss.iqs.service.IUserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hgh
 * @since 2017-08-25
 */
@Service
public class UserGroupServiceImpl extends ServiceImpl<UserGroupMapper, UserGroup> implements IUserGroupService {
    @Autowired
    private UserGroupMapper userGroupMapper;

    @Override
    public void saveUserGroup(UserGroup userGroup) {
        userGroupMapper.insert(userGroup);
    }

    @Override
    public void deleteUserGroup(Integer id) {
        userGroupMapper.deleteById(id);
    }

    @Override
    public void updateUserGroup(UserGroup userGroup) {
        userGroupMapper.updateById(userGroup);
    }

    @Override
    public UserGroup getUserGroup(Integer id) {
        return userGroupMapper.selectById(id);
    }
}
