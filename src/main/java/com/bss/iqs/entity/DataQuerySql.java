package com.bss.iqs.entity;

import com.baomidou.mybatisplus.enums.IdType;
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
	private Integer groupId;
    /**
     * sql内容
     */
	private String sqlContent;


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

	public String getSqlContent() {
		return sqlContent;
	}

	public void setSqlContent(String sqlContent) {
		this.sqlContent = sqlContent;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "DataQuerySql{" +
			"id=" + id +
			", groupId=" + groupId +
			", sqlContent=" + sqlContent +
			"}";
	}
}
