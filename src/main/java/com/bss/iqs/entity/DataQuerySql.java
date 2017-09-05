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
 * @since 2017-09-01
 */
@TableName("data_query_sql")
public class DataQuerySql extends Model<DataQuerySql> {

    private static final long serialVersionUID = 1L;

    /**
     * sql_id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 分组id
     */
	private Integer dataQueryGroupId;
    /**
     * sql内容
     */
	private String sqlContent;
    /**
     * 说明
     */
	private String description;
	private Date createTime;
	private Date updateTime;


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

	public String getSqlContent() {
		return sqlContent;
	}

	public void setSqlContent(String sqlContent) {
		this.sqlContent = sqlContent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return "DataQuerySql{" +
			"id=" + id +
			", dataQueryGroupId=" + dataQueryGroupId +
			", sqlContent=" + sqlContent +
			", description=" + description +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
