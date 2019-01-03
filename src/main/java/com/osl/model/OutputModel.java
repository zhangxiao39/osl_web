package com.osl.model;

import java.sql.Date;
import java.sql.Timestamp;

import com.osl.common.web.BaseModel;

public class OutputModel extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -983500654804680236L;
	
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
	
	//sku
	private String sku;
	//商品名称
	private String goodsName;
	//条形码
	private String barcode;
	//录入开始日期
	private String startNewDate;
	//录入结束日期
	private String endNewDate;
	//查询flag
	private String searchFlag;
	//商家名称
	private String businessName;
	//收件人
	private String customer;
	//请求日期
	private Timestamp requestDate;
	//仓库id
	private String depotId;
	
	
	public String getDepotId() {
		return depotId;
	}
	public void setDepotId(String depotId) {
		this.depotId = depotId;
	}
	public Timestamp getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Timestamp requestDate) {
		this.requestDate = requestDate;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
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
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
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
	public String getSearchFlag() {
		return searchFlag;
	}
	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
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
