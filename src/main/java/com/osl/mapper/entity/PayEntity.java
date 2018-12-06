package com.osl.mapper.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.osl.common.web.BaseEntity;

public class PayEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8455940346069851724L;
	
	private long id;
	private String payId;
	private Date date;
	private BigDecimal totle;
	private int status;
	private String requestId;
	private long businessId;
	private long warehouseId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
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

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}

	public long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(long warehouseId) {
		this.warehouseId = warehouseId;
	}

}
