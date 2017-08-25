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
@TableName("data_query_group")
public class DataQueryGroup extends Model<DataQueryGroup> {

    private static final long serialVersionUID = 1L;

    /**
     * 数据查询任务分组id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 数据查询任务分组名称
     */
	private String name;
    /**
     * 群组logo
     */
	private String logo;
    /**
     * 关联任务id
     */
	private Integer dataQueryTaskId;


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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getDataQueryTaskId() {
		return dataQueryTaskId;
	}

	public void setDataQueryTaskId(Integer dataQueryTaskId) {
		this.dataQueryTaskId = dataQueryTaskId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "DataQueryGroup{" +
			"id=" + id +
			", name=" + name +
			", logo=" + logo +
			", dataQueryTaskId=" + dataQueryTaskId +
			"}";
	}
}
