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
     ResultBean saveGroupPermission(GroupPermission groupPermission);

     ResultBean updateGroupPermission(GroupPermission groupPermission);


     GroupPermission findGroupPermissionById(Integer id);


     ResultBean deleteGroupPermission(Integer id);

     AddGroupPermissionBean getPermissionAndGroup();

     List<GroupPermission> findGroupPermissionByUsername(String username);
}
