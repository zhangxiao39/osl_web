package com.osl.mapper.entity;

import java.util.Date;

import com.osl.common.web.BaseEntity;

public class EntryEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2819299654968863572L;

	private long id;
	private String entryId;
	private Date operTime;
	private Date completeTime;
	private int skuNums;
	private int goodsNums;
	private int inputSkuNums;
	private int inputGoodsNums;
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

	public String getEntryId() {
		return entryId;
	}

	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}

	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
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

	public int getInputSkuNums() {
		return inputSkuNums;
	}

	public void setInputSkuNums(int inputSkuNums) {
		this.inputSkuNums = inputSkuNums;
	}

	public int getInputGoodsNums() {
		return inputGoodsNums;
	}

	public void setInputGoodsNums(int inputGoodsNums) {
		this.inputGoodsNums = inputGoodsNums;
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
