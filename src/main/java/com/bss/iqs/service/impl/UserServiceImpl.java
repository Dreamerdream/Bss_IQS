package com.bss.iqs.service.impl;


import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bss.iqs.bean.Result;
import com.bss.iqs.bean.UserLoginResult;
import com.bss.iqs.bean.UserResult;
import com.bss.iqs.entity.Department;
import com.bss.iqs.entity.LoginRecord;
import com.bss.iqs.entity.User;
import com.bss.iqs.entity.UserGroup;
import com.bss.iqs.mapper.DepartmentMapper;
import com.bss.iqs.mapper.LoginRecordMapper;
import com.bss.iqs.mapper.UserGroupMapper;
import com.bss.iqs.mapper.UserMapper;
import com.bss.iqs.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hgh
 * @since 2017-08-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LoginRecordMapper loginRecordMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private UserGroupMapper userGroupMapper;

    @Override
    public void saveUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateById(user);
    }

    @Override
    public User getUser(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public User queryUser(String type, String keyword, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public UserResult getDepartmentAndUserGroup() {
        return null;
    }

    @Override
    public Result login(String username, String password) {

        Wrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("username",username).eq("password",password);
        List<User> users = userMapper.selectList(wrapper);
        if (users != null && users.size() != 0){
            Result result = null;
            if (users != null && users.size() != 0){
                User user = users.get(0);
                result.setErrorCode(0);
                result.setErrorReason("登录成功");

                //插入登录记录表
                LoginRecord loginRecord = new LoginRecord();
                loginRecord.setLoginTime(new Date());
                loginRecord.setUserId(user.getId());
                loginRecordMapper.insert(loginRecord);
            }else {
                result.setErrorCode(1);
                result.setErrorReason("用户名或者密码错误");
            }
            return result;
        }
        return null;
    }

    @Override
    public List<UserLoginResult> queryAll() {
        List<UserLoginResult> userLoginResults = new ArrayList<>();
        Wrapper<User> userWrapper = new EntityWrapper<>();
        List<User> users = userMapper.selectList(userWrapper);
        if (users != null && users.size() != 0){
            for (int i = 0; i < users.size() ; i++) {
                User user = users.get(i);
                Department department = departmentMapper.selectById(user.getDepartmentId());
                UserGroup userGroup = userGroupMapper.selectById(user.getGroupId());
                Wrapper<LoginRecord> loginRecordwrapper = new EntityWrapper<>();
                loginRecordwrapper.eq("userId",user.getId()).orderBy("loginTime",false);
                List<LoginRecord> loginRecords = loginRecordMapper.selectList(loginRecordwrapper);
                UserLoginResult userLoginResult = new UserLoginResult();
                userLoginResult.setCount(loginRecords.size());
                userLoginResult.setDepartmentName(department.getName());
                userLoginResult.setRealname(user.getRealname());
                userLoginResult.setStatus(user.getStatus());
                userLoginResult.setUserGroupName(userGroup.getName());
                userLoginResult.setLastLoginTime(loginRecords.get(0).getLoginTime());
                userLoginResults.add(userLoginResult);
            }
            return userLoginResults;
        }
        return null;
    }
}
