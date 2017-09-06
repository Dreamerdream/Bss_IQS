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
 * @since 2017-09-06
 */
@TableName("data_query_task")
public class DataQueryTask extends Model<DataQueryTask> {

    private static final long serialVersionUID = 1L;

    /**
     * 数据查询任务id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 分组id
     */
	private Integer dataQueryGroupId;
    /**
     * 模板id
     */
	private Integer templateId;
    /**
     * 省
     */
	private String province;
    /**
     * 市
     */
	private String city;
    /**
     * 数据输出选择
     */
	private String outputFormat;
    /**
     * 备注
     */
	private String comment;
    /**
     * 短信接收人号码
     */
	private String phone;
	private Integer rdsId;
	private Date createTime;
	private Date updateTime;
    /**
     * 是否有组选择了，默认0：没有 1：有
     */
	private String status;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDataQueryGroupId() {
		return dataQueryGroupId;
	}

	public void setDataQueryGroupId(Integer dataQueryGroupId) {
		this.dataQueryGroupId = dataQueryGroupId;
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
			", dataQueryGroupId=" + dataQueryGroupId +
			", templateId=" + templateId +
			", province=" + province +
			", city=" + city +
			", outputFormat=" + outputFormat +
			", comment=" + comment +
			", phone=" + phone +
			", rdsId=" + rdsId +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			", status=" + status +
			"}";
	}
}
