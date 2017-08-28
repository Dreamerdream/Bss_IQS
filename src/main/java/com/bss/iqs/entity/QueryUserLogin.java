package com.bss.iqs.entity;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * InnoDB free: 3072 kB
 * </p>
 *
 * @author hgh
 * @since 2017-08-26
 */
@TableName("query_user_login")
public class QueryUserLogin extends Model<QueryUserLogin> {

    private static final long serialVersionUID = 1L;

	private Integer id;
	private String groupName;
	private String username;
	private String realname;
	private String departmentName;
	private Date lastLoginTime;
	private Integer loginCount;
	private String status;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "QueryUserLogin{" +
			"id=" + id +
			", groupName=" + groupName +
			", username=" + username +
			", realname=" + realname +
			", departmentName=" + departmentName +
			", lastLoginTime=" + lastLoginTime +
			", loginCount=" + loginCount +
			", status=" + status +
			"}";
	}
}
