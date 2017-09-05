package com.bss.iqs.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.entity.UserGroup;
import com.bss.iqs.mapper.UserGroupMapper;
import com.bss.iqs.service.IUserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    public ResultBean saveUserGroup(UserGroup userGroup) {
        Date date = new Date();
        userGroup.setCreateTime(date);
        userGroup.setUpdateTime(date);
        Integer insert = userGroupMapper.insert(userGroup);
        if (insert != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("添加成功");
            return result;
        }
        return null;
    }

    @Override
    public ResultBean deleteUserGroup(Integer id) {
        Integer integer = userGroupMapper.deleteById(id);
        if (integer != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("删除成功");
            return result;
        }
        return null;
    }

    @Override
    public ResultBean updateUserGroup(UserGroup userGroup) {
        userGroup.setUpdateTime(new Date());
        Integer integer = userGroupMapper.updateById(userGroup);
        if (integer != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("更新成功");
            return result;
        }
        return null;
    }

    @Override
    public UserGroup getUserGroup(Integer id) {
        return userGroupMapper.selectById(id);
    }
}
