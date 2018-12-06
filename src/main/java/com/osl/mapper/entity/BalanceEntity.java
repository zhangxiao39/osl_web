package com.osl.mapper.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.osl.common.web.BaseEntity;

public class BalanceEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4448412349604280810L;
	
	private long id;
	private String balanceId;
	private Date date;
	private BigDecimal total;
	private BigDecimal outputPrice;
	private BigDecimal inputPrice;
	private BigDecimal keepPrice;
	private long businessId;
	private long warehouseId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBalanceId() {
		return balanceId;
	}

	public void setBalanceId(String balanceId) {
		this.balanceId = balanceId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getOutputPrice() {
		return outputPrice;
	}

	public void setOutputPrice(BigDecimal outputPrice) {
		this.outputPrice = outputPrice;
	}

	public BigDecimal getInputPrice() {
		return inputPrice;
	}

	public void setInputPrice(BigDecimal inputPrice) {
		this.inputPrice = inputPrice;
	}

	public BigDecimal getKeepPrice() {
		return keepPrice;
	}

	public void setKeepPrice(BigDecimal keepPrice) {
		this.keepPrice = keepPrice;
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
