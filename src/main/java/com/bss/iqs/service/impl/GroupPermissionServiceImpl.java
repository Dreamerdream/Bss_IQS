package com.bss.iqs.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bss.iqs.bean.AddGroupPermissionBean;
import com.bss.iqs.bean.GroupPermissionBean;
import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.bean.UserGroupPermissionBean;
import com.bss.iqs.entity.*;
import com.bss.iqs.mapper.*;
import com.bss.iqs.service.IGroupPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private GroupPermissionUserMapper groupPermissionUserMapper;

    @Transactional
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
            String userIds = groupPermission.getUserId(); //用户Id
            String[] split = userIds.split(",");
            //这里不知道对不对，需要测试
            for (int i = 0; i <split.length ; i++) {
                Integer userId = Integer.valueOf(split[i]);
                User user = userMapper.selectById(userId);
                user.setGroupPermissionId(insert);
                userMapper.updateById(user);

                //添加到组权限用户表中
                GroupPermissionUser groupPermissionUser = new GroupPermissionUser();
                groupPermissionUser.setGroupPermissionId(insert);
                groupPermissionUser.setUserId(userId);
                groupPermissionUserMapper.insert(groupPermissionUser);
            }
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("添加成功");
            return result;
        }
        return null;
    }


    //这里还要写,等值传上来在说
    //如果userId存数组的话，得把组ID带上来,有点麻烦
    @Transactional
    @Override
    public ResultBean updateGroupPermission(GroupPermission groupPermission) {
        groupPermission.setUpdateTime(new Date());
        Integer id = groupPermission.getId();
        Integer integer = groupPermissionMapper.updateById(groupPermission);
        if (integer != null){
            //需要把新的用户ID添加到该组中，删除原用户组的用户,未分组

            String userId = groupPermission.getUserId();
            String[] split = userId.split(",");

            if (split != null && split.length != 0){
                for (int i = 0; i < split.length ; i++) {
                    Wrapper<GroupPermissionUser> groupPermissionUserWrapper = new EntityWrapper<>();
                    groupPermissionUserWrapper.eq("userId",split[i]);
                    List<GroupPermissionUser> groupPermissionUsers = groupPermissionUserMapper.selectList(groupPermissionUserWrapper);
                    if (groupPermissionUsers != null && groupPermissionUsers.size() != 0){
                        GroupPermissionUser groupPermissionUser = groupPermissionUsers.get(0);
                        groupPermissionUserMapper.deleteById(groupPermissionUser.getId());

                    }

                    //更新用户表中的groupPermissionId
                    User user = userMapper.selectById(split[i]);
                    user.setGroupPermissionId(id);
                    userMapper.updateById(user);

                    //添加到groupPermissionUser表中
                    GroupPermissionUser groupPermissionUser = new GroupPermissionUser();
                    groupPermissionUser.setGroupPermissionId(id);
                    groupPermissionUser.setUserId(Integer.valueOf(split[i]));
                    groupPermissionUser.setRealName("hgh");
                    groupPermissionUserMapper.insert(groupPermissionUser);

                }


            }

            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("修改成功");
            return result;
        }
        return null;
    }

    @Override
    public AddGroupPermissionBean findGroupPermissionById(Integer id) {

        AddGroupPermissionBean addGroupPermissionBean = new AddGroupPermissionBean();
        //编辑需要将所有权限，组的权限，所有组，组的成员，未分组的人带过去。

//        GroupPermission groupPermission = groupPermissionMapper.selectById(id);
//
//
//        //所有权限
//        Wrapper<SystemPermission> systemPermissionWrapper = new EntityWrapper<>();
//        List<SystemPermission> systemPermissions = systemPermissionMapper.selectList(systemPermissionWrapper);
//
//        addGroupPermissionBean.setSystemPermissions(systemPermissions);
//
//        //获取所有权限组
//        Wrapper<GroupPermission> groupPermissionWrapper = new EntityWrapper<>();
//        List<GroupPermission> groupPermissions = groupPermissionMapper.selectList(groupPermissionWrapper);
//
//
//        if (groupPermissions != null && groupPermissions.size() != 0){
//            //不存在权限组的用户Id
//            List<Integer> userIds = new ArrayList<>();
//            //包含用户组，以及用户组权限，以及用户组成员
//            List<GroupPermissionBean> groupPermissionBeans = new ArrayList<>();
//            //获取所有权限组用户
//            for (GroupPermission permission:groupPermissions) {
//
//
//                //构建Bean
//                GroupPermissionBean groupPermissionBean = new GroupPermissionBean();
//                groupPermissionBean.setPermissionName(permission.getPermissionName());
//                groupPermissionBean.setGroupPermissionId(permission.getId());
//
//
//                Wrapper<GroupPermissionUser> groupPermissionUserWrapper = new EntityWrapper<>();
//                groupPermissionUserWrapper.eq("groupPermissionId",permission.getId());
//                List<GroupPermissionUser> groupPermissionUsers = groupPermissionUserMapper.selectList(groupPermissionUserWrapper);
//
//
//                List<UserGroupPermissionBean> userGroupPermissionBeans = new ArrayList<>();
//                for (GroupPermissionUser groupPermissionUser:groupPermissionUsers) {
//
//                    Integer userId = groupPermissionUser.getUserId();
//                    String realName = groupPermissionUser.getRealName();
//
//                    //构建Bean
//                    UserGroupPermissionBean userGroupPermissionBean = new UserGroupPermissionBean();
//                    userGroupPermissionBean.setUserId(userId);
//                    userGroupPermissionBean.setRealName(realName);
//
//                    userGroupPermissionBeans.add(userGroupPermissionBean);
//
//                    userIds.add(userId);
//                }
//
//                groupPermissionBean.setUserGroupPermissionBeans(userGroupPermissionBeans);
//
//                groupPermissionBeans.add(groupPermissionBean);
//
//            }
//
//            addGroupPermissionBean.setGroupPermissionBeans(groupPermissionBeans);
//
//            //未分组用户
//            Wrapper<User> userWrapper = new EntityWrapper<>();
//            userWrapper.notIn("id",userIds);
//            List<User> users = userMapper.selectList(userWrapper);
//            addGroupPermissionBean.setUsers(users);
//
//            //返回最后数据
//            return addGroupPermissionBean;
//        }

//
//        //所有权限
//        Wrapper<SystemPermission> systemPermissionWrapper = new EntityWrapper<>();
//        List<SystemPermission> systemPermissions = systemPermissionMapper.selectList(systemPermissionWrapper);
//        addGroupPermissionBean.setSystemPermissions(systemPermissions);
//
//        //获取所有权限组
//        Wrapper<GroupPermission> groupPermissionWrapper = new EntityWrapper<>();
//        List<GroupPermission> groupPermissions = groupPermissionMapper.selectList(groupPermissionWrapper);
//        List<GroupPermissionBean> groupPermissionBeans = new ArrayList<>();
//        List<Integer> userIds = new ArrayList<>();
//        for (GroupPermission groupPermission:groupPermissions) {
//
//            GroupPermissionBean groupPermissionBean = new GroupPermissionBean();
//            groupPermissionBean.setPermissionName(groupPermission.getPermissionName());
//
//            String userId = groupPermission.getUserId();
//            String[] split = userId.split(",");
//            List<UserGroupPermissionBean> userGroupPermissionBeans = new ArrayList<>();
//            for (int i = 0; i < split.length ; i++) {
//                UserGroupPermissionBean userGroupPermissionBean = new UserGroupPermissionBean();
//
//                //存在于分组的
//                User user = userMapper.selectById(split[i]);
//                userGroupPermissionBean.setRealName(user.getRealname());
//                userGroupPermissionBean.setUserId(user.getId());
//                userGroupPermissionBeans.add(userGroupPermissionBean);
//
//                //用于找未分组的
//                userIds.add(Integer.valueOf(split[i]));
//            }
//            groupPermissionBean.setUserGroupPermissionBeans(userGroupPermissionBeans);
//            groupPermissionBeans.add(groupPermissionBean);
//        }
//
//
//        //未分组用户
//        Wrapper<User> userWrapper = new EntityWrapper<>();
//        userWrapper.notIn("id",userIds);
//        List<User> users = userMapper.selectList(userWrapper);
//        addGroupPermissionBean.setUsers(users);
        return null;
    }

    @Transactional
    @Override
    public ResultBean deleteGroupPermission(Integer id) {

        Integer integer = groupPermissionMapper.deleteById(id);
        if (integer != null){
            Wrapper<GroupPermissionUser> groupPermissionUserWrapper = new EntityWrapper<>();
            groupPermissionUserWrapper.eq("groupPermissionId",id);
            List<GroupPermissionUser> groupPermissionUsers = groupPermissionUserMapper.selectList(groupPermissionUserWrapper);
            for (GroupPermissionUser groupPermissionUser:groupPermissionUsers) {
                //把user表中的groupPermissionId更新为0
                Integer userId = groupPermissionUser.getUserId();
                User user = userMapper.selectById(userId);
                user.setGroupPermissionId(0);
                userMapper.updateById(user);
                //删除groupPermissionUser表中的数据
                groupPermissionUserMapper.deleteById(groupPermissionUser.getGroupPermissionId());
            }

//            //如果没有groupPermissionUser表
//            GroupPermission groupPermission = groupPermissionMapper.selectById(id);
//            String userId = groupPermission.getUserId();
//            String[] split = userId.split(",");
//            for (int i = 0; i < split.length ; i++) {
//
//                User user = userMapper.selectById(split[i]);
//                user.setGroupPermissionId(0);
//                userMapper.updateById(user);
//            }
//            //在删除数据
//            Integer integer = groupPermissionMapper.deleteById(id);
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
//
//        //获取权限组
//        Wrapper<GroupPermission> groupPermissionWrapper = new EntityWrapper<>();
//        List<GroupPermission> groupPermissions = groupPermissionMapper.selectList(groupPermissionWrapper);
//        if (groupPermissions != null && groupPermissions.size() != 0){
//            List<Integer> userIds = new ArrayList<>();
//            addGroupPermissionBean.setGroupPermissions(groupPermissions);
//            for (GroupPermission groupPermission:groupPermissions) {
//                String userId = groupPermission.getUserId();
//               //这里还有问题的，取出来是多个数组，应该变为一个数组
//                String[] split = userId.split(",");
//                if (split != null && split.length != 0){
//                    for (int i = 0; i < split.length ; i++) {
//                        userIds.add(Integer.valueOf(split[i]));
//                    }
//                }
//
//
//            }
//            Wrapper<User> userWrapper = new EntityWrapper<>();
//            userWrapper.notIn("id",userIds);
//            List<User> users = userMapper.selectList(userWrapper);
//            addGroupPermissionBean.setUsers(users);
//        }else {
//            //获取所有用户
//            Wrapper<User> userWrapper = new EntityWrapper<>();
//            List<User> users = userMapper.selectList(userWrapper);
//            addGroupPermissionBean.setUsers(users);
//        }
//
//        //获取所有权限
//        Wrapper<SystemPermission> systemPermissionWrapper = new EntityWrapper<>();
//        List<SystemPermission> systemPermissions = systemPermissionMapper.selectList(systemPermissionWrapper);
//        addGroupPermissionBean.setSystemPermissions(systemPermissions);



        //获取权限组
        Wrapper<GroupPermission> groupPermissionWrapper = new EntityWrapper<>();
        List<GroupPermission> groupPermissions = groupPermissionMapper.selectList(groupPermissionWrapper);
        //获取不在权限组里面的用户
        if (groupPermissions != null && groupPermissions.size() != 0){
            List<Integer> userIds = new ArrayList<>();
            for (GroupPermission groupPermission:groupPermissions) {
                Wrapper<GroupPermissionUser> groupPermissionUserWrapper = new EntityWrapper<>();
                groupPermissionUserWrapper.eq("groupPermissionId",groupPermission.getId());
                List<GroupPermissionUser> groupPermissionUsers = groupPermissionUserMapper.selectList(groupPermissionUserWrapper);

                for (GroupPermissionUser groupPermissionUser:groupPermissionUsers) {
                    userIds.add(groupPermissionUser.getUserId());
                }
                Wrapper<User> userWrapper = new EntityWrapper<>();
                userWrapper.notIn("id",userIds);
                List<User> users = userMapper.selectList(userWrapper);
                addGroupPermissionBean.setUsers(users);
            }
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


//        //如果没有groupPermissionUser表
//        //获取权限组
//        Wrapper<GroupPermission> groupPermissionWrapper = new EntityWrapper<>();
//        List<GroupPermission> groupPermissions = groupPermissionMapper.selectList(groupPermissionWrapper);
//        if (groupPermissions != null && groupPermissions.size() != 0){
//            List<Integer> userIds = new ArrayList<>();
//            for (GroupPermission groupPermission:groupPermissions) {
//                String userId = groupPermission.getUserId();
//                String[] split = userId.split(",");
//                for (int i = 0; i < split.length ; i++) {
//                    userIds.add(Integer.valueOf(split[i]));
//                }
//            }
//            Wrapper<User> userWrapper = new EntityWrapper<>();
//            userWrapper.notIn("id",userIds);
//            List<User> users = userMapper.selectList(userWrapper);
//            addGroupPermissionBean.setUsers(users);
//        }
//
//        //获取所有权限
//        Wrapper<SystemPermission> systemPermissionWrapper = new EntityWrapper<>();
//        List<SystemPermission> systemPermissions = systemPermissionMapper.selectList(systemPermissionWrapper);
//        addGroupPermissionBean.setSystemPermissions(systemPermissions);
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
