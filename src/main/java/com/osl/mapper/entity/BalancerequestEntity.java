package com.osl.mapper.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.osl.common.web.BaseEntity;

public class BalancerequestEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -196575242289533304L;
	
	private long id;
	private String requestId;
	private String balanceId;
	private Date requestTime;
	private BigDecimal total;
	private int status;
	private long businessId;
	private long warehouseId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getBalanceId() {
		return balanceId;
	}

	public void setBalanceId(String balanceId) {
		this.balanceId = balanceId;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
