package com.bss.iqs.service;


import com.baomidou.mybatisplus.service.IService;
import com.bss.iqs.bean.AddGroupPermissionBean;
import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.entity.GroupPermission;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hgh
 * @since 2017-08-28
 */
public interface IGroupPermissionService extends IService<GroupPermission> {
    public ResultBean saveGroupPermission(GroupPermission groupPermission);

    public ResultBean updateGroupPermission(GroupPermission groupPermission);


    public GroupPermission findGroupPermissionById(Integer id);


    public ResultBean deleteGroupPermission(Integer id);

    public AddGroupPermissionBean getPermissionAndGroup();

    public List<GroupPermission> findGroupPermissionByUsername(String username);
}
