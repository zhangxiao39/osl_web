package com.osl.mapper.entity;

import java.sql.Timestamp;
import java.util.Date;

import com.osl.common.web.BaseEntity;

public class StockEntity extends BaseEntity {

	/**
	 * 库存实体
	 */
	private static final long serialVersionUID = -729969280894158993L;

	private String manageId;
	private String goodsId;
	private String depotId;
	private String shelvesId;
	private int nums;
	private int goodsType;
	private Date inputTime;
	private String inputDetailId;
	private double volumn;
	private Date produceTime;
	private Date validityTime;
	private int businessId;
	private int warehouseId;
	private Timestamp newDate;
	private Timestamp updateDate;
	private int deleteFlag;
	public String getManageId() {
		return manageId;
	}
	public void setManageId(String manageId) {
		this.manageId = manageId;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getDepotId() {
		return depotId;
	}
	public void setDepotId(String depotId) {
		this.depotId = depotId;
	}
	public String getShelvesId() {
		return shelvesId;
	}
	public void setShelvesId(String shelvesId) {
		this.shelvesId = shelvesId;
	}
	public int getNums() {
		return nums;
	}
	public void setNums(int nums) {
		this.nums = nums;
	}
	public int getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(int goodsType) {
		this.goodsType = goodsType;
	}
	public Date getInputTime() {
		return inputTime;
	}
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	public String getInputDetailId() {
		return inputDetailId;
	}
	public void setInputDetailId(String inputDetailId) {
		this.inputDetailId = inputDetailId;
	}
	public double getVolumn() {
		return volumn;
	}
	public void setVolumn(double volumn) {
		this.volumn = volumn;
	}
	
	public Date getProduceTime() {
		return produceTime;
	}
	public void setProduceTime(Date produceTime) {
		this.produceTime = produceTime;
	}
	public Date getValidityTime() {
		return validityTime;
	}
	public void setValidityTime(Date validityTime) {
		this.validityTime = validityTime;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public int getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
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
	public int getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	
	
}
