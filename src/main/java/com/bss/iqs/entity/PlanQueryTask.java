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
 * @since 2017-09-07
 */
@TableName("plan_query_task")
public class PlanQueryTask extends Model<PlanQueryTask> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 计划任务Id
     */
	private Integer planTaskId;
    /**
     * 数据查询任务Id
     */
	private Integer dataQueryTaskId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPlanTaskId() {
		return planTaskId;
	}

	public void setPlanTaskId(Integer planTaskId) {
		this.planTaskId = planTaskId;
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
		return "PlanQueryTask{" +
			"id=" + id +
			", planTaskId=" + planTaskId +
			", dataQueryTaskId=" + dataQueryTaskId +
			"}";
	}
}
