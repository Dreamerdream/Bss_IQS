package com.bss.iqs.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bss.iqs.bean.AddGroupPermissionBean;
import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.entity.GroupPermission;
import com.bss.iqs.entity.SystemPermission;
import com.bss.iqs.entity.User;
import com.bss.iqs.entity.UserGroup;
import com.bss.iqs.mapper.GroupPermissionMapper;
import com.bss.iqs.mapper.SystemPermissionMapper;
import com.bss.iqs.mapper.UserGroupMapper;
import com.bss.iqs.mapper.UserMapper;
import com.bss.iqs.service.IGroupPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hgh
 * @since 2017-08-28
 */
@Service
public class GroupPermissionServiceImpl extends ServiceImpl<GroupPermissionMapper, GroupPermission> implements IGroupPermissionService {

    @Autowired
    private GroupPermissionMapper groupPermissionMapper;

    @Autowired
    private SystemPermissionMapper systemPermissionMapper;


    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultBean saveGroupPermission(GroupPermission groupPermission) {
        //这里也有问题，可能上传上来的人有多个，应该是个数组，权限也有多个，是数组
//        GroupPermission groupPermission = new GroupPermission();
//        groupPermission.setPermissionName(permissionName.toString());
//        groupPermission.setPermission(permission.toString());
//        groupPermission.setPermissionGroupName(groupPermissionName);
//        groupPermission.setUrl(url.toString());
//        groupPermission.setUserId(userIds.toString());
        Date date = new Date();
        groupPermission.setCreateTime(date);
        groupPermission.setUpdateTime(date);
        Integer insert = groupPermissionMapper.insert(groupPermission);
        if (insert != null){
            String userIds = groupPermission.getUserId();
            String[] split = userIds.split(",");
            //这里不知道对不对，需要测试
            for (int i = 0; i <split.length ; i++) {
                Integer userId = Integer.valueOf(split[i]);
                User user = userMapper.selectById(userId);
                user.setGroupPermissionId(insert);
                userMapper.updateById(user);
            }
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("添加成功");
            return result;
        }
        return null;
    }

    //这里还要写
    @Override
    public ResultBean updateGroupPermission(GroupPermission groupPermission) {
        groupPermission.setUpdateTime(new Date());
        Integer integer = groupPermissionMapper.updateById(groupPermission);
        if (integer != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("修改成功");
            return result;
        }
        return null;
    }

    @Override
    public GroupPermission findGroupPermissionById(Integer id) {
        return groupPermissionMapper.selectById(id);
    }

    @Override
    public ResultBean deleteGroupPermission(Integer id) {
        Integer integer = groupPermissionMapper.deleteById(id);
        if (integer != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("删除成功");
            return result;
        }
        return null;
    }

    @Override
    public AddGroupPermissionBean getPermissionAndGroup() {

        AddGroupPermissionBean addGroupPermissionBean = new AddGroupPermissionBean();

        //获取权限组
        Wrapper<GroupPermission> groupPermissionWrapper = new EntityWrapper<>();
        List<GroupPermission> groupPermissions = groupPermissionMapper.selectList(groupPermissionWrapper);
        if (groupPermissions != null && groupPermissions.size() != 0){
            List<String> userIds = new ArrayList<>();
            addGroupPermissionBean.setGroupPermissions(groupPermissions);
            for (GroupPermission groupPermission:groupPermissions) {
                String userId = groupPermission.getUserId();
               //这里还有问题的，取出来是多个数组，应该变为一个数组
                userIds.add(userId);

            }
            Wrapper<User> userWrapper = new EntityWrapper<>();
            userWrapper.notIn("id",userIds);
            List<User> users = userMapper.selectList(userWrapper);
            addGroupPermissionBean.setUsers(users);
        }else {
            //获取所有用户
            Wrapper<User> userWrapper = new EntityWrapper<>();
            List<User> users = userMapper.selectList(userWrapper);
            addGroupPermissionBean.setUsers(users);
        }

        //获取所有权限
        Wrapper<SystemPermission> systemPermissionWrapper = new EntityWrapper<>();
        List<SystemPermission> systemPermissions = systemPermissionMapper.selectList(systemPermissionWrapper);
        addGroupPermissionBean.setSystemPermissions(systemPermissions);

        return addGroupPermissionBean;
    }

    @Override
    public List<GroupPermission> findGroupPermissionByUsername(String username) {
        Wrapper<GroupPermission> groupPermissionWrapper = new EntityWrapper<>();
        groupPermissionWrapper.eq("username",username);
        List<GroupPermission> groupPermissions = groupPermissionMapper.selectList(groupPermissionWrapper);
        if (groupPermissions != null && groupPermissions.size() !=0){
            return groupPermissions;
        }
        return null;
    }
}
