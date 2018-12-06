package com.osl.mapper.entity;

import java.util.Date;

import com.osl.common.web.BaseEntity;

public class InputEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8229796137196111548L;

	private long id;
	private String inputId;
	private Date intime;
	private int skuNums;
	private int goodsNums;
	private int status;
	private long businessId;
	private long warehouseId;
	private String oper;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getInputId() {
		return inputId;
	}

	public void setInputId(String inputId) {
		this.inputId = inputId;
	}

	public Date getIntime() {
		return intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

	public int getSkuNums() {
		return skuNums;
	}

	public void setSkuNums(int skuNums) {
		this.skuNums = skuNums;
	}

	public int getGoodsNums() {
		return goodsNums;
	}

	public void setGoodsNums(int goodsNums) {
		this.goodsNums = goodsNums;
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

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

}
