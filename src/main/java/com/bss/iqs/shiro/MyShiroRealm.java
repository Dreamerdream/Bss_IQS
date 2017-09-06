package com.bss.iqs.shiro;

import com.bss.iqs.bean.ActiveUser;
import com.bss.iqs.bean.Menu;
import com.bss.iqs.value.AuthAlone;
import com.bss.iqs.entity.GroupPermission;
import com.bss.iqs.entity.User;

import com.bss.iqs.service.IGroupPermissionService;

import com.bss.iqs.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private IUserService userService;



    @Autowired
    private IGroupPermissionService groupPermissionService;



    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        ActiveUser activeUser = (ActiveUser) principalCollection.getPrimaryPrincipal();
        User user = activeUser.getUser();
        List<String> permissions = new ArrayList<>();
        if (AuthAlone.OPEN.val().equals(user.getPermissionStatus())){
            String permission = user.getPermission();
            String[] split = permission.split(",");
            permissions = Arrays.asList(split);
            //将string转化为List
        }else {
            GroupPermission groupPermission = groupPermissionService.selectById(user.getGroupPermissionId());
            String permission = groupPermission.getPermission();
            //将string转化为List
            String[] split = permission.split(",");
            permissions = Arrays.asList(split);
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo= new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(permissions);

        return simpleAuthorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       // Object credentials = authenticationToken.getCredentials();
        String username = (String) authenticationToken.getPrincipal();
        User user = userService.findUserByUsername(username);
        if (user == null){
            System.out.println("-----用户不存在");
            return null;
        }
        ActiveUser activeUser = new ActiveUser();
        activeUser.setUser(user);
        if (AuthAlone.OPEN.val().equals(user.getPermissionStatus())){
            //开启单独授权
            String permissionName = user.getPermissionName();//菜单
            String url = user.getUrl();//链接
            List<Menu> menus = getMenu(permissionName, url);
            activeUser.setMenuList(menus);
        }else {
            if (user.getGroupPermissionId() != 0){
                GroupPermission groupPermission = groupPermissionService.selectById(user.getGroupPermissionId());
                String permissionName = groupPermission.getPermissionName();//菜单
                String url = groupPermission.getUrl();//链接
                List<Menu> menus = getMenu(permissionName, url);
                activeUser.setMenuList(menus);
            }

        }

        //将菜单放到Bean中


        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                activeUser, //用户名
                user.getPassword(), //密码
                getName()  //realm name
        );
        return authenticationInfo;
    }

    public List<Menu> getMenu(String permissionName,String url){
        List<Menu> menus = new ArrayList<>();
        String[] permissionNames = permissionName.split(",");
        String[] urls = url.split(",");
        for (int i = 0; i < permissionNames.length ; i++) {
            Menu menu = new Menu();
            menu.setName(permissionNames[i]);
            menu.setUrl(urls[i]);
            menus.add(menu);
        }
        return menus;
    }
}
