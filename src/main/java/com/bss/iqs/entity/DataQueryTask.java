package com.bss.iqs.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * InnoDB free: 11264 kB
 * </p>
 *
 * @author hgh
 * @since 2017-08-26
 */
@TableName("data_query_task")
public class DataQueryTask extends Model<DataQueryTask> {

    private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer groupId;
	private Integer templateId;
	private String province;
	private String city;
	private String outputFormat;
	private String comment;
	private String phone;
	private Integer rdsId;
	private String name;
	private String status;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getOutputFormat() {
		return outputFormat;
	}

	public void setOutputFormat(String outputFormat) {
		this.outputFormat = outputFormat;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getRdsId() {
		return rdsId;
	}

	public void setRdsId(Integer rdsId) {
		this.rdsId = rdsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return "DataQueryTask{" +
			"id=" + id +
			", groupId=" + groupId +
			", templateId=" + templateId +
			", province=" + province +
			", city=" + city +
			", outputFormat=" + outputFormat +
			", comment=" + comment +
			", phone=" + phone +
			", rdsId=" + rdsId +
			", name=" + name +
			", status=" + status +
			"}";
	}
}
