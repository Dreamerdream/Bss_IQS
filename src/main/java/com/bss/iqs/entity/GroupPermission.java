package com.bss.iqs.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author hgh
 * @since 2017-09-08
 */
@TableName("group_permission")
public class GroupPermission extends Model<GroupPermission> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 权限组组名
     */
	private String permissionGroupName;
    /**
     * 权限
     */
	private String permission;
    /**
     * 用户
     */
	private String userId;
    /**
     * 菜单
     */
	private String url;
    /**
     * 菜单名
     */
	private String permissionName;
	private Date createTime;
	private Date updateTime;
    /**
     * 用户真实名字
     */
	private String realName;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPermissionGroupName() {
		return permissionGroupName;
	}

	public void setPermissionGroupName(String permissionGroupName) {
		this.permissionGroupName = permissionGroupName;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "GroupPermission{" +
			"id=" + id +
			", permissionGroupName=" + permissionGroupName +
			", permission=" + permission +
			", userId=" + userId +
			", url=" + url +
			", permissionName=" + permissionName +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			", realName=" + realName +
			"}";
	}
}
