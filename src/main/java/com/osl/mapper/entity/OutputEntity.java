package com.osl.mapper.entity;

import java.sql.Timestamp;
import java.util.Date;

import com.osl.common.web.BaseEntity;

public class OutputEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7738523941206255545L;
	
	private String outputId;
	private Date outputTime;
	private int skuNums;
	private int goodsNums;
	private int status;
	private long businessId;
	private long warehouseId;
	private String oper;
	private Timestamp newDate;
	private Timestamp updateDate;
	private int deleteFlg;
	
	public String getOutputId() {
		return outputId;
	}
	public void setOutputId(String outputId) {
		this.outputId = outputId;
	}
	public Date getOutputTime() {
		return outputTime;
	}
	public void setOutputTime(Date outputTime) {
		this.outputTime = outputTime;
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
	public int getDeleteFlg() {
		return deleteFlg;
	}
	public void setDeleteFlg(int deleteFlg) {
		this.deleteFlg = deleteFlg;
	}


}
