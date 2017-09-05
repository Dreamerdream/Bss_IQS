package com.bss.iqs.entity;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author hgh
 * @since 2017-09-04
 */
@TableName("plan_task_record")
public class PlanTaskRecord extends Model<PlanTaskRecord> {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 所属计划任务
     */
	private String planTaskName;
    /**
     * 分组名称
     */
	private String dataQueryGroupName;
    /**
     * 分组Id
     */
	private Integer dataQueryGroupId;
    /**
     * 关联任务
     */
	private String dataQueryTaskName;
    /**
     * 输出格式
     */
	private String outputFormat;
    /**
     * 开始时间
     */
	private Date beginTime;
    /**
     * 执行结果
     */
	private String result;
    /**
     * 完成时间
     */
	private Date finishTime;
    /**
     * 关联任务Id
     */
	private Integer dataQueryTaskId;

	private Integer count;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlanTaskName() {
		return planTaskName;
	}

	public void setPlanTaskName(String planTaskName) {
		this.planTaskName = planTaskName;
	}

	public String getDataQueryGroupName() {
		return dataQueryGroupName;
	}

	public void setDataQueryGroupName(String dataQueryGroupName) {
		this.dataQueryGroupName = dataQueryGroupName;
	}

	public Integer getDataQueryGroupId() {
		return dataQueryGroupId;
	}

	public void setDataQueryGroupId(Integer dataQueryGroupId) {
		this.dataQueryGroupId = dataQueryGroupId;
	}

	public String getDataQueryTaskName() {
		return dataQueryTaskName;
	}

	public void setDataQueryTaskName(String dataQueryTaskName) {
		this.dataQueryTaskName = dataQueryTaskName;
	}

	public String getOutputFormat() {
		return outputFormat;
	}

	public void setOutputFormat(String outputFormat) {
		this.outputFormat = outputFormat;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
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
		return "PlanTaskRecord{" +
			"id=" + id +
			", planTaskName=" + planTaskName +
			", dataQueryGroupName=" + dataQueryGroupName +
			", dataQueryGroupId=" + dataQueryGroupId +
			", dataQueryTaskName=" + dataQueryTaskName +
			", outputFormat=" + outputFormat +
			", beginTime=" + beginTime +
			", result=" + result +
			", finishTime=" + finishTime +
			", dataQueryTaskId=" + dataQueryTaskId +
			"}";
	}
}
