package com.osl.mapper.entity;

import java.util.Date;

import com.osl.common.web.BaseEntity;

public class ReturnEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 579287072342708218L;
	
	private long id;
	private String returnId;
	private Date applyTime;
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

	public String getReturnId() {
		return returnId;
	}

	public void setReturnId(String returnId) {
		this.returnId = returnId;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
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
