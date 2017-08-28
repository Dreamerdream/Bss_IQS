package com.bss.iqs.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.bean.QueryUserLoginRecordBean;
import com.bss.iqs.bean.AddUserBean;
import com.bss.iqs.entity.*;
import com.bss.iqs.mapper.*;
import com.bss.iqs.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private QueryUserLoginMapper queryUserLoginMapper;

    @Override
    public ResultBean saveUser(User user) {
        Integer insert = userMapper.insert(user);
        if (insert != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("添加成功");
        }
        return null;
    }

    @Override
    public ResultBean deleteUser(Integer id) {
        Integer integer = userMapper.deleteById(id);
        if (integer != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("删除成功");
        }
        return null;
    }

    @Override
    public ResultBean updateUser(User user) {
        Integer integer = userMapper.updateById(user);
        if (integer != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("修改成功");
        }
        return null;
    }

    @Override
    public User getUser(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<QueryUserLogin> queryUser(String type, String keyword, Integer pageNum, Integer pageSize) {
        List<QueryUserLogin> queryUserLogins = new ArrayList<>();
        if ("用户名".equals(type)){
            queryUserLogins = queryUserLoginMapper.selectPage(new Page<QueryUserLogin>(pageNum,pageSize),
                    new EntityWrapper<QueryUserLogin>().eq("username",type).like("username",keyword));
        }else if ("所属分组".equals(type)){
            queryUserLogins = queryUserLoginMapper.selectPage(new Page<QueryUserLogin>(pageNum,pageSize),
                    new EntityWrapper<QueryUserLogin>().eq("groupName",type).like("groupName",keyword));
        }else if ("所属部门".equals(type)){
            queryUserLogins = queryUserLoginMapper.selectPage(new Page<QueryUserLogin>(pageNum,pageSize),
                    new EntityWrapper<QueryUserLogin>().eq("departmentName",type).like("departmentName",keyword));
        }
        if (queryUserLogins != null && queryUserLogins.size() != 0){
            return queryUserLogins;
        }

        return null;
    }

    @Override
    public AddUserBean getDepartmentAndUserGroup() {
        Wrapper<Department> departmentWrapper = new EntityWrapper<>();
        List<Department> departments = departmentMapper.selectList(departmentWrapper);
        Wrapper<UserGroup> userGroupWrapper = new EntityWrapper<>();
        List<UserGroup> userGroups = userGroupMapper.selectList(userGroupWrapper);
        if (departments != null && departments.size() != 0 && userGroups != null && userGroups.size() != 0){
            AddUserBean userResult = new AddUserBean();
            userResult.setDepartments(departments);
            userResult.setUserGroups(userGroups);
            return userResult;
        }
        return null;
    }

    @Transactional
    @Override
    public ResultBean login(String username, String password) {
        Wrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("username",username).eq("password",password);
        List<User> users = userMapper.selectList(wrapper);
        if (users != null && users.size() != 0){
            ResultBean result = null;
            if (users != null && users.size() != 0){
                User user = users.get(0);
                result.setErrorCode(0);
                result.setErrorReason("登录成功");

                //插入登录记录时间表
                LoginRecord loginRecord = new LoginRecord();
                loginRecord.setLoginTime(new Date());
                loginRecord.setUserId(user.getId());
                loginRecordMapper.insert(loginRecord);

                //记录用户登录，便于查询
                Wrapper<QueryUserLogin> queryUserLoginWrapper = new EntityWrapper<>();
                queryUserLoginWrapper.eq("username",user.getUsername());
                List<QueryUserLogin> queryUserLogins = queryUserLoginMapper.selectList(queryUserLoginWrapper);
                if (queryUserLogins != null && queryUserLogins.size() !=0 ){
                    QueryUserLogin queryUserLogin = queryUserLogins.get(0);
                    queryUserLogin.setLoginCount(queryUserLogin.getLoginCount()+1);
                    queryUserLoginMapper.updateById(queryUserLogin);
                }else {
                    QueryUserLogin queryUserLogin = new QueryUserLogin();
                    Department department = departmentMapper.selectById(user.getDepartmentId());
                    queryUserLogin.setDepartmentName(department.getName());
                    UserGroup userGroup = userGroupMapper.selectById(user.getGroupId());
                    queryUserLogin.setGroupName(userGroup.getName());
                    queryUserLogin.setLastLoginTime(new Date());
                    queryUserLogin.setLoginCount(1);
                    queryUserLogin.setRealname(user.getRealname());
                    queryUserLogin.setUsername(user.getUsername());
                    queryUserLogin.setStatus(user.getStatus());
                    queryUserLoginMapper.insert(queryUserLogin);
                }

            }else {
                result.setErrorCode(1);
                result.setErrorReason("用户名或者密码错误");
            }
            return result;
        }
        return null;
    }

    @Override
    public List<QueryUserLoginRecordBean> queryAll() {
        List<QueryUserLoginRecordBean> userLoginResults = new ArrayList<>();
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
                QueryUserLoginRecordBean userLoginResult = new QueryUserLoginRecordBean();
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
