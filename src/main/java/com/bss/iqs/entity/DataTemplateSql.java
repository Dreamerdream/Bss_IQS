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
@TableName("data_template_sql")
public class DataTemplateSql extends Model<DataTemplateSql> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * sqlId
     */
	private Integer sqlId;
    /**
     * 模板Id
     */
	private Integer templateId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSqlId() {
		return sqlId;
	}

	public void setSqlId(Integer sqlId) {
		this.sqlId = sqlId;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "DataTemplateSql{" +
			"id=" + id +
			", sqlId=" + sqlId +
			", templateId=" + templateId +
			"}";
	}
}
