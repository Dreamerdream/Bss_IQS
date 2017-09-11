package com.bss.iqs.entity;

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
@TableName("group_permission_user")
public class GroupPermissionUser extends Model<GroupPermissionUser> {

    private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer groupPermissionId;
	private Integer userId;
	private String realName;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGroupPermissionId() {
		return groupPermissionId;
	}

	public void setGroupPermissionId(Integer groupPermissionId) {
		this.groupPermissionId = groupPermissionId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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
		return "GroupPermissionUser{" +
			"id=" + id +
			", groupPermissionId=" + groupPermissionId +
			", userId=" + userId +
			", realName=" + realName +
			"}";
	}
}
