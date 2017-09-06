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
@TableName("data_template")
public class DataTemplate extends Model<DataTemplate> {

    private static final long serialVersionUID = 1L;

    /**
     * 模板id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 模板任务
     */
	private String name;
    /**
     * 模板内容
     */
	private String content;
    /**
     * sql语句
     */
	private String sqlId;
    /**
     * 数据查询任务分组
     */
	private Integer dataQueryGroupId;
    /**
     * 输出格式
     */
	private String outputFormat;
    /**
     * 添加时间
     */
	private Date createTime;
    /**
     * 更新时间
     */
	private Date updateTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSqlId() {
		return sqlId;
	}

	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}

	public Integer getDataQueryGroupId() {
		return dataQueryGroupId;
	}

	public void setDataQueryGroupId(Integer dataQueryGroupId) {
		this.dataQueryGroupId = dataQueryGroupId;
	}

	public String getOutputFormat() {
		return outputFormat;
	}

	public void setOutputFormat(String outputFormat) {
		this.outputFormat = outputFormat;
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
		return "DataTemplate{" +
			"id=" + id +
			", name=" + name +
			", content=" + content +
			", sqlId=" + sqlId +
			", dataQueryGroupId=" + dataQueryGroupId +
			", outputFormat=" + outputFormat +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
