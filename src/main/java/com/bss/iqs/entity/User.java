package com.bss.iqs.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author hgh
 * @since 2017-09-04
 */
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 用户名
     */
	private String username;
    /**
     * 用户真实姓名
     */
	private String realname;
    /**
     *  用户密码
     */
	private String password;
    /**
     * 部门id，用户属于哪个部门
     */
	private Integer departmentId;
    /**
     * 分组id，用户属于哪个组
     */
	private Integer userGroupId;
    /**
     * 状态  0：开，1：关  默认开
     */
	private String status;
    /**
     * 权限
     */
	private String permission;
    /**
     * 菜单名称
     */
	private String permissionName;
    /**
     * 是否单独授权
     */
	private String permissionStatus;
    /**
     * 组权限Id
     */
	private Integer groupPermissionId;
    /**
     * 访问的url
     */
	private String url;
	private Date createTime;
	private Date updateTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(Integer userGroupId) {
		this.userGroupId = userGroupId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionStatus() {
		return permissionStatus;
	}

	public void setPermissionStatus(String permissionStatus) {
		this.permissionStatus = permissionStatus;
	}

	public Integer getGroupPermissionId() {
		return groupPermissionId;
	}

	public void setGroupPermissionId(Integer groupPermissionId) {
		this.groupPermissionId = groupPermissionId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "User{" +
			"id=" + id +
			", username=" + username +
			", realname=" + realname +
			", password=" + password +
			", departmentId=" + departmentId +
			", userGroupId=" + userGroupId +
			", status=" + status +
			", permission=" + permission +
			", permissionName=" + permissionName +
			", permissionStatus=" + permissionStatus +
			", groupPermissionId=" + groupPermissionId +
			", url=" + url +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
