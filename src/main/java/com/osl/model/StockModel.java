package com.osl.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.osl.common.web.BaseModel;

public class StockModel extends BaseModel {

	/**
	 * 库存对应model
	 */
	private static final long serialVersionUID = -6378104285046437881L;

	private String manageId;
	private String goodsId;
	private String depotId;
	private String shelvesId;
	private int nums;
	private int goodsType;
	private Date inputTime;
	private String inputDetailId;
	private double volume;
	private Date productTime;
	private Date validityTime;
	private int businessId;
	private int warehouseId;
	private Timestamp newDate;
	private Timestamp updateDate;
	private int deleteFlag;
	
	private String bUserName;
	private String sku;
	private String goodsName;
	private int categoryId;
	private String categoryName;
	private String barCode;
	private Timestamp startDate;
	private Timestamp endDate;
	//录入开始日期
	private String startNewDate;
	//录入结束日期
	private String endNewDate;
	
	private List<String> skuList;
	
	private int innerNums;
	private int innerGoodsNums;
	
	
	public int getInnerNums() {
		return innerNums;
	}
	public void setInnerNums(int innerNums) {
		this.innerNums = innerNums;
	}
	public int getInnerGoodsNums() {
		return innerGoodsNums;
	}
	public void setInnerGoodsNums(int innerGoodsNums) {
		this.innerGoodsNums = innerGoodsNums;
	}
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
	
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	
	public Date getProductTime() {
		return productTime;
	}
	public void setProductTime(Date productTime) {
		this.productTime = productTime;
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
	public String getbUserName() {
		return bUserName;
	}
	public void setbUserName(String bUserName) {
		this.bUserName = bUserName;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	public String getStartNewDate() {
		return startNewDate;
	}
	public void setStartNewDate(String startNewDate) {
		this.startNewDate = startNewDate;
	}
	public String getEndNewDate() {
		return endNewDate;
	}
	public void setEndNewDate(String endNewDate) {
		this.endNewDate = endNewDate;
	}
	public List<String> getSkuList() {
		return skuList;
	}
	public void setSkuList(List<String> skuList) {
		this.skuList = skuList;
	}
	
	
	
	
}
