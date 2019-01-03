package com.osl.mapper.entity;

import java.sql.Timestamp;
import java.util.Date;

import com.osl.common.web.BaseEntity;

public class InputEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8229796137196111548L;

	private long id;
	private String inputId;
	private Date inputTime;
	private int skuNums;
	private int goodsNums;
	private int status;
	private long businessId;
	private long warehouseId;
	private int type;
	private String shipId;
	private String oper;
	private int deleteFlag;
	private Timestamp newDate;
	private Timestamp updateDate;

	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getShipId() {
		return shipId;
	}

	public void setShipId(String shipId) {
		this.shipId = shipId;
	}

	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Timestamp getNewDate() {
		return newDate;
	}

	public void setNewDate(Timestamp newDate) {
		this.newDate = newDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

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

	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
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
