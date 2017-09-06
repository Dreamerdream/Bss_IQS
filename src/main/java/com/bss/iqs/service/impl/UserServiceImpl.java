package com.bss.iqs.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bss.iqs.bean.*;
import com.bss.iqs.entity.*;
import com.bss.iqs.mapper.*;
import com.bss.iqs.service.IUserService;
import com.bss.iqs.value.AuthAlone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
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
    private UserLoginRecordDao loginRecordDao;

    @Autowired
    private GroupPermissionMapper groupPermissionMapper;

    @Autowired
    private DataQueryTaskMapper dataQueryTaskMapper;

    @Autowired
    private DataTemplateMapper dataTemplateMapper;

    @Autowired
    private PlanTaskMapper planTaskMapper;

    @Autowired
    private DataQuerySqlMapper dataQuerySqlMapper;


    @Transactional
    @Override
    public ResultBean saveUser(User user) {
        Wrapper<User> userWrapper = new EntityWrapper<>();
        userWrapper.eq("username",user.getUsername());
        List<User> users = userMapper.selectList(userWrapper);
        ResultBean result = new ResultBean();
        if (users != null && users.size() != 0){
            result.setErrorCode(1);
            result.setErrorReason("用户已存在");
            return result;
        }else {
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            Integer insert = userMapper.insert(user);
            if (insert != null) {
                result.setErrorCode(0);
                result.setErrorReason("添加成功");
                return result;
            }
        }

        return null;
    }

    @Transactional
    @Override
    public ResultBean deleteUser(Integer id) {
        Integer integer = userMapper.deleteById(id);
        if (integer != null) {
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("删除成功");
            return result;
        }
        return null;
    }

    @Transactional
    @Override
    public ResultBean updateUser(User user) {
        user.setUpdateTime(new Date());
        Integer integer = userMapper.updateById(user);
        if (integer != null) {
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("修改成功");
            return result;
        }
        return null;
    }

    @Override
    public User getUser(Integer id) {
        return userMapper.selectById(id);
    }

//    @Override
//    public PageBean queryUser(String type, String keyword, Integer pageNum, Integer pageSize) {
//        int pageStart = 0;
//        int pageEnd = 0;
//        if (pageNum != null && pageSize != null ){
//            pageStart = (pageNum - 1) * pageSize;
//            pageEnd = pageStart + pageSize;
//        }
//        List<LoginRecord> loginRecords = new ArrayList<>();
//        List<Map> maps = new  ArrayList<>();
//        if ("用户名".equals(type)) {
//            maps = loginRecordDao.getUserLoginRecord("username", keyword,pageStart,pageEnd);
//        } else if ("真实姓名".equals(type)) {
//            maps = loginRecordDao.getUserLoginRecord("realname", keyword,pageStart,pageEnd);
//        } else if ("所属部门".equals(type)) {
//            maps = loginRecordDao.getUserLoginRecord("departmentName", keyword,pageStart,pageEnd);
//        }
//
//        //总记录数,为了计算几页
//        Wrapper<LoginRecord> loginRecordWrapper = new EntityWrapper<>();
//        loginRecordWrapper.
//        Integer loginRecordCount = loginRecordMapper.selectCount(loginRecordWrapper);
//
//
//        PageBean page = new PageBean(pageNum,pageSize,maps,loginRecordCount,"");
//
//
//        return page;
//    }

    @Override
    public AddUserBean getDepartmentAndUserGroup() {
        Wrapper<Department> departmentWrapper = new EntityWrapper<>();
        List<Department> departments = departmentMapper.selectList(departmentWrapper);
        Wrapper<UserGroup> userGroupWrapper = new EntityWrapper<>();
        List<UserGroup> userGroups = userGroupMapper.selectList(userGroupWrapper);
        if (departments != null && departments.size() != 0 && userGroups != null && userGroups.size() != 0) {
            AddUserBean userResult = new AddUserBean();
            userResult.setDepartments(departments);
            userResult.setUserGroups(userGroups);
            return userResult;
        }
        return null;
    }

    @Transactional
    @Override
    public ActiveUser saveLoginRecord(ActiveUser activeUser) {
        User user = activeUser.getUser();
        Date date = new Date();
        LoginRecord loginRecord = new LoginRecord();
        loginRecord.setUserId(user.getId());
        Integer departmentId = user.getDepartmentId();
        if (departmentId != null){
            Department department = departmentMapper.selectById(departmentId);
            loginRecord.setDapartmentName(department.getName());
        }

        loginRecord.setRealname(user.getRealname());
        loginRecord.setStatus(user.getStatus());
        Integer userGroupId = user.getUserGroupId();
        if (userGroupId != null){
            UserGroup userGroup = userGroupMapper.selectById(userGroupId);
            loginRecord.setUserGroupName(userGroup.getName());
        }
        loginRecord.setUsername(user.getUsername());
        loginRecord.setLoginTime(date);
        loginRecordMapper.insert(loginRecord);


        //还需要将个数显示出来
        Wrapper<DataQueryTask> dataQueryTaskwrapper = new EntityWrapper<>();
        Integer dataQueryTaskCount = dataQueryTaskMapper.selectCount(dataQueryTaskwrapper);
        Wrapper<DataTemplate> templateWrapper = new EntityWrapper<>();
        Integer templateCount = dataTemplateMapper.selectCount(templateWrapper);
        Wrapper<PlanTask> planTaskWrapper = new EntityWrapper<>();
        Integer planTaskCount = planTaskMapper.selectCount(planTaskWrapper);
        Wrapper<DataQuerySql> dataQuerySqlWrapper = new EntityWrapper<>();
        Integer dataQuerySqlCount = dataQuerySqlMapper.selectCount(dataQuerySqlWrapper);

        activeUser.setDataQuerySqlCount(dataQuerySqlCount);
        activeUser.setPlanTaskCount(planTaskCount);
        activeUser.setDataQueryTaskCount(dataQueryTaskCount);
        activeUser.setTemplateCount(templateCount);

        return activeUser;

        //记录用户登录，便于查询
//        Wrapper<QueryUserLogin> queryUserLoginWrapper = new EntityWrapper<>();
//        queryUserLoginWrapper.eq("username", user.getUsername());
//        List<QueryUserLogin> queryUserLogins = queryUserLoginMapper.selectList(queryUserLoginWrapper);
//        if (queryUserLogins != null && queryUserLogins.size() != 0) {
//            QueryUserLogin queryUserLogin = queryUserLogins.get(0);
//            queryUserLogin.setLoginCount(queryUserLogin.getLoginCount() + 1);
//            queryUserLoginMapper.updateById(queryUserLogin);
//        } else {
//            QueryUserLogin queryUserLogin = new QueryUserLogin();
//            Department department = departmentMapper.selectById(user.getDepartmentId());
//            queryUserLogin.setDepartmentName(department.getName());
//            UserGroup userGroup = userGroupMapper.selectById(user.getGroupId());
//            queryUserLogin.setGroupName(userGroup.getName());
//            queryUserLogin.setLastLoginTime(date);
//            queryUserLogin.setLoginCount(1);
//            queryUserLogin.setRealname(user.getRealname());
//            queryUserLogin.setUserId(user.getId());
//            queryUserLogin.setStatus(user.getStatus());
//            queryUserLoginMapper.insert(queryUserLogin);
//        }


    }

//    @Override
//    public List<QueryUserLoginRecordBean> queryAll() {
//        List<QueryUserLoginRecordBean> userLoginResults = new ArrayList<>();
//        Wrapper<User> userWrapper = new EntityWrapper<>();
//        List<User> users = userMapper.selectList(userWrapper);
//        if (users != null && users.size() != 0) {
//            for (int i = 0; i < users.size(); i++) {
//                User user = users.get(i);
//                Department department = departmentMapper.selectById(user.getDepartmentId());
//                UserGroup userGroup = userGroupMapper.selectById(user.getUserGroupId());
//                Wrapper<LoginRecord> loginRecordwrapper = new EntityWrapper<>();
//                loginRecordwrapper.eq("userId", user.getId()).orderBy("loginTime", false);
//                List<LoginRecord> loginRecords = loginRecordMapper.selectList(loginRecordwrapper);
//                QueryUserLoginRecordBean userLoginResult = new QueryUserLoginRecordBean();
//                userLoginResult.setCount(loginRecords.size());
//                userLoginResult.setDepartmentName(department.getName());
//                userLoginResult.setRealname(user.getRealname());
//                userLoginResult.setStatus(user.getStatus());
//                userLoginResult.setUserGroupName(userGroup.getName());
//                // userLoginResult.setLastLoginTime(loginRecords.get(0).getLastLoginTime());
//                userLoginResults.add(userLoginResult);
//            }
//            return userLoginResults;
//        }
//        return null;
//    }

    @Override
    public User findUserByUsername(String username) {
        Wrapper<User> userWrapper = new EntityWrapper<>();
        userWrapper.eq("username", username);
        List<User> users = userMapper.selectList(userWrapper);
        if (users != null && users.size() != 0) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public ResultBean addPermission(String username, String permissionStatus, String permission, String permissionName, String url, Integer groupPermissionId) {
        Wrapper<User> userWrapper = new EntityWrapper<>();
        userWrapper.eq("username", username);
        List<User> users = userMapper.selectList(userWrapper);
        if (users != null && users.size() != 0) {
            User user = users.get(0);
            if (AuthAlone.OPEN.val().equals(permissionStatus)) {
                user.setPermissionStatus(permissionStatus);
                user.setPermission(permission);
                user.setUrl(url);
                user.setPermissionName(permissionName);
                userMapper.updateById(user);
            }
            //更新组权限
            updateGroupPermission(user,permissionStatus,groupPermissionId);
        }
//            Integer userGPI = user.getGroupPermissionId();
//            //如果将用户归到了未分组,删掉用户组中用户的权限
//            if (groupPermissionId == 0) {
//                //如果用户之前就有分组
//                if (userGPI != 0) {
//                    //删除之前用户组权限中的用户
//                    GroupPermission groupPermission = groupPermissionMapper.selectById(userGPI);
//                    String userIds = groupPermission.getUserId();
//                    String[] split = userIds.split(",");
//                    StringBuilder deleteOldUserId = new StringBuilder();
//                    for (int i = 0; i < split.length; i++) {
//                        if (split[i].equals(String.valueOf(user.getId()))) {
//                            continue;
//                        }
//                        deleteOldUserId.append(split[i]);
//                    }
//                    groupPermission.setUserId(deleteOldUserId.toString());
//                    //将用户ID添加进去
//                    groupPermissionMapper.updateById(groupPermission);
//                    //将用户的组权限ID变为0
//                    user.setGroupPermissionId(0);
//                    userMapper.updateById(user);
//                }
//            } else {
//                //如果将用户归到了其他分组,删掉原有用户组中用户的权限,赋予其他组权限
//
//                //如果用户没有被分组
////                if (userGPI == 0) {
////                    //将用户分到该组
////                    GroupPermission groupPermission = groupPermissionMapper.selectById(groupPermissionId);
////                    String userIds = groupPermission.getUserId();
////                    StringBuilder stringBuilder =new StringBuilder();
////                    stringBuilder.append(userIds);
////                    stringBuilder.append(",");
////                    stringBuilder.append(id);
//
////                } else {
//
//                //如果用户被分组,删掉原有用户组中用户的权限,赋予其他组权限
//
//                if (userGPI != 0) {
//                    //删除原先的组中的userId
//                    GroupPermission oldGroupPermission = groupPermissionMapper.selectById(user.getGroupPermissionId());
//                    String userIds = oldGroupPermission.getUserId();
//                    String[] split = userIds.split(",");
//                    StringBuilder deleteOldUserId = new StringBuilder();
//                    for (int i = 0; i < split.length; i++) {
//                        if (split[i].equals(String.valueOf(user.getId()))) {
//                            continue;
//                        }
//                        deleteOldUserId.append(split[i]);
//                    }
//                    oldGroupPermission.setUserId(deleteOldUserId.toString());
//                    //将用户ID添加进去
//                    groupPermissionMapper.updateById(oldGroupPermission);
//                }
//
//                //               }
//                //userId添加到新的权限组中
//                GroupPermission groupPermission = groupPermissionMapper.selectById(groupPermissionId);
//                String userId = groupPermission.getUserId();
//                StringBuilder stringBuilder = new StringBuilder();
//                stringBuilder.append(userId);
//                stringBuilder.append(",");
//                stringBuilder.append(id);
//
//                //更新用户的权限组ID
//                user.setGroupPermissionId(groupPermissionId);
//                userMapper.updateById(user);
//            }
//        }

        //开启单独授权
//        if (PermissionStatus.OPEN.val().equals(permissionStatus)){
//            Wrapper<User> userWrapper = new EntityWrapper<>();
//            userWrapper.eq("username",username);
//            List<User> users = userMapper.selectList(userWrapper);
//            if (users != null && users.size() != 0){
//                User user = users.get(0);
//                user.setPermissionStatus(permissionStatus);
//                user.setPermission(permission);
//                user.setUrl(url);
//                user.setPermissionName(permissionName);
//                //用户未分组
//                if (user.getGroupPermissionId() == 0){
//                    user.setGroupPermissionId(groupPermissionId); //修改用户的权限组
//                    //将改用户添加到新的权限组里面
//                    GroupPermission newGroupPermission = groupPermissionMapper.selectById(groupPermissionId);
//                    String userId = newGroupPermission.getUserId();
//                    StringBuilder stringBuilder =new StringBuilder();
//                    stringBuilder.append(userId);
//                    stringBuilder.append(",");
//                    stringBuilder.append(user.getId());
//                    newGroupPermission.setUserId(stringBuilder.toString());
//                    //将用户ID添加进去
//                    groupPermissionMapper.updateById(newGroupPermission);
//                } else {
//                    if (groupPermissionId != user.getGroupPermissionId()){
//                        user.setGroupPermissionId(groupPermissionId); //修改用户的权限组
//                        //找到组权限
//                        GroupPermission groupPermission = groupPermissionMapper.selectById(user.getGroupPermissionId());
//                        //剔除掉改用户
//                        String userIds = groupPermission.getUserId();
//                        String[] split = userIds.split(",");
//                        StringBuilder deleteOldUserId = new StringBuilder();
//                        for (int i = 0; i < split.length; i++) {
//                            if (split[i].equals(String.valueOf(user.getId()))){
//                                continue;
//                            }
//                            deleteOldUserId.append(split[i]);
//                        }
//                        groupPermission.setUserId(deleteOldUserId.toString());
//                        groupPermissionMapper.updateById(groupPermission);
//                        //将改用户添加到新的权限组里面
//                        GroupPermission newGroupPermission = groupPermissionMapper.selectById(groupPermissionId);
//                        String userId = newGroupPermission.getUserId();
//                        StringBuilder stringBuilder =new StringBuilder();
//                        stringBuilder.append(userId);
//                        stringBuilder.append(",");
//                        stringBuilder.append(user.getId());
//                        groupPermission.setUserId(stringBuilder.toString());
//                        //将用户ID添加进去
//                        groupPermissionMapper.updateById(newGroupPermission);
//                    }
//                }
//                userMapper.updateById(user);
//            }
//        }else {
//            //不开启单独授权,只是修改用户属于哪个组
//        }
        return null;
    }


    public ResultBean updatePermission(Integer userId, String permissionStatus, String permission, String permissionName, String url, Integer groupPermissionId){

        User user = userMapper.selectById(userId);
        if (user != null){
            if (AuthAlone.OPEN.val().equals(permissionStatus)) {
                user.setPermissionStatus(permissionStatus);
                user.setUrl(url);
                user.setPermission(permission);
                user.setPermissionName(permissionName);
                userMapper.updateById(user);
            }
        }


        //更新组权限
        updateGroupPermission(user,permissionStatus,groupPermissionId);
        return null;
    }


    public void updateGroupPermission(User user,String permissionStatus,Integer groupPermissionId){

        Integer id = user.getId();
        Integer userGPI = user.getGroupPermissionId();
        //如果将用户归到了未分组,删掉用户组中用户的权限
        if (groupPermissionId == 0) {
            //如果用户之前就有分组
            if (userGPI != 0) {
                //删除之前用户组权限中的用户
                GroupPermission groupPermission = groupPermissionMapper.selectById(userGPI);
                String userIds = groupPermission.getUserId();
                String[] split = userIds.split(",");
                StringBuilder deleteOldUserId = new StringBuilder();
                for (int i = 0; i < split.length; i++) {
                    if (split[i].equals(String.valueOf(id))) {
                        continue;
                    }
                    deleteOldUserId.append(split[i]);
                }
                groupPermission.setUserId(deleteOldUserId.toString());
                //将用户ID添加进去
                groupPermissionMapper.updateById(groupPermission);
                //将用户的组权限ID变为0
                user.setGroupPermissionId(0);
                userMapper.updateById(user);
            }
        } else {
            //如果用户被分组,删掉原有用户组中用户的权限,赋予其他组权限

            if (userGPI != 0) {
                //删除原先的组中的userId
                GroupPermission oldGroupPermission = groupPermissionMapper.selectById(user.getGroupPermissionId());
                String userIds = oldGroupPermission.getUserId();
                String[] split = userIds.split(",");
                StringBuilder deleteOldUserId = new StringBuilder();
                for (int i = 0; i < split.length; i++) {
                    if (split[i].equals(String.valueOf(id))) {
                        continue;
                    }
                    deleteOldUserId.append(split[i]);
                }
                oldGroupPermission.setUserId(deleteOldUserId.toString());
                //将用户ID添加进去
                groupPermissionMapper.updateById(oldGroupPermission);
            }
            //userId添加到新的权限组中
            GroupPermission groupPermission = groupPermissionMapper.selectById(groupPermissionId);
            String userId = groupPermission.getUserId();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(userId);
            stringBuilder.append(",");
            stringBuilder.append(id);

            //更新用户的权限组ID
            user.setGroupPermissionId(groupPermissionId);
            userMapper.updateById(user);
        }
    }
}
