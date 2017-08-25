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
 * @since 2017-08-25
 */
@TableName("login_record")
public class LoginRecord extends Model<LoginRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * 登录记录id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 登录时间
     */
	private Date loginTime;
    /**
     * 用户ID
     */
	private Integer userId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "LoginRecord{" +
			"id=" + id +
			", loginTime=" + loginTime +
			", userId=" + userId +
			"}";
	}
}
