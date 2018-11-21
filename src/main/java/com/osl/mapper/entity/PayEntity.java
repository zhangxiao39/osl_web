package com.osl.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class PayEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8455940346069851724L;
	
	private long id;
	private String pay_id;
	private Date date;
	private BigDecimal totle;
	private int status;
	private String request_id;
	private long business_id;
	private long warehouse_id;
	private Timestamp addtime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPay_id() {
		return pay_id;
	}

	public void setPay_id(String pay_id) {
		this.pay_id = pay_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getTotle() {
		return totle;
	}

	public void setTotle(BigDecimal totle) {
		this.totle = totle;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public long getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(long business_id) {
		this.business_id = business_id;
	}

	public long getWarehouse_id() {
		return warehouse_id;
	}

	public void setWarehouse_id(long warehouse_id) {
		this.warehouse_id = warehouse_id;
	}

	public Timestamp getAddtime() {
		return addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}

}
