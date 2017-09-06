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
@TableName("plan_task")
public class PlanTask extends Model<PlanTask> {

    private static final long serialVersionUID = 1L;

    /**
     * 计划任务id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 计划任务名称
     */
	private String name;
    /**
     * 分组任务id
     */
	private Integer dataQueryGroupId;
    /**
     * 数据查询任务id
     */
	private String dataQueryTaskId;
    /**
     * 内容开始时间
     */
	private Date beginTime;
    /**
     * 循环执行
     */
	private String cycleStatus;
    /**
     * 周期
     */
	private String period;
    /**
     * 开始状态
     */
	private String status;
    /**
     * 任务命名
     */
	private String taskNamed;
    /**
     * 分钟
     */
	private Integer minute;
    /**
     * 小时
     */
	private Integer hour;
    /**
     * 天
     */
	private Integer day;
    /**
     * 月
     */
	private Integer month;
	private Date createTime;
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

	public Integer getDataQueryGroupId() {
		return dataQueryGroupId;
	}

	public void setDataQueryGroupId(Integer dataQueryGroupId) {
		this.dataQueryGroupId = dataQueryGroupId;
	}

	public String getDataQueryTaskId() {
		return dataQueryTaskId;
	}

	public void setDataQueryTaskId(String dataQueryTaskId) {
		this.dataQueryTaskId = dataQueryTaskId;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public String getCycleStatus() {
		return cycleStatus;
	}

	public void setCycleStatus(String cycleStatus) {
		this.cycleStatus = cycleStatus;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTaskNamed() {
		return taskNamed;
	}

	public void setTaskNamed(String taskNamed) {
		this.taskNamed = taskNamed;
	}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
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
		return "PlanTask{" +
			"id=" + id +
			", name=" + name +
			", dataQueryGroupId=" + dataQueryGroupId +
			", dataQueryTaskId=" + dataQueryTaskId +
			", beginTime=" + beginTime +
			", cycleStatus=" + cycleStatus +
			", period=" + period +
			", status=" + status +
			", taskNamed=" + taskNamed +
			", minute=" + minute +
			", hour=" + hour +
			", day=" + day +
			", month=" + month +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
