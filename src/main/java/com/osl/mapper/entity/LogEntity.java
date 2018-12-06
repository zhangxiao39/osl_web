package com.osl.mapper.entity;

import java.util.Date;

import com.osl.common.web.BaseEntity;

public class LogEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8954634063951074797L;
	
	private long id;
	private long userId;
	private Date operTime;
	private String operIp;
	private String type;
	private String remark;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	public String getOperIp() {
		return operIp;
	}

	public void setOperIp(String operIp) {
		this.operIp = operIp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
