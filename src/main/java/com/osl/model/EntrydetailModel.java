package com.osl.model;

import java.sql.Date;
import java.sql.Timestamp;

import com.osl.common.web.BaseModel;

public class EntrydetailModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5035303454422659768L;

	private String detailId;
	private String goodsId;
	private int nums;
	private double packageSize;
	private int innerNums;
	private int innerGoodsNums;
	private String sendId;
	private String entryId;
	private int inputNums;
	private int inputDiff;
	private Date produceTime;
	private int maxNums;
	private int status;
	private int deleteFlg;
	private Timestamp newDate;
	private Timestamp updateDate;
	private Date validityTime;
	
	private String sku;
	private String categoryName;
	private String goodsName;
	private int inputDiffNums;
	private int businessId;
	private String oper;
	private int stockNums;
	
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public int getNums() {
		return nums;
	}
	public void setNums(int nums) {
		this.nums = nums;
	}
	public double getPackageSize() {
		return packageSize;
	}
	public void setPackageSize(double packageSize) {
		this.packageSize = packageSize;
	}
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
	public String getSendId() {
		return sendId;
	}
	public void setSendId(String sendId) {
		this.sendId = sendId;
	}
	
	public String getEntryId() {
		return entryId;
	}
	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}
	public int getInputNums() {
		return inputNums;
	}
	public void setInputNums(int inputNums) {
		this.inputNums = inputNums;
	}
	public int getInputDiff() {
		return inputDiff;
	}
	public void setInputDiff(int inputDiff) {
		this.inputDiff = inputDiff;
	}
	public Date getProduceTime() {
		return produceTime;
	}
	public void setProduceTime(Date produceTime) {
		this.produceTime = produceTime;
	}
	public int getMaxNums() {
		return maxNums;
	}
	public void setMaxNums(int maxNums) {
		this.maxNums = maxNums;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getDeleteFlg() {
		return deleteFlg;
	}
	public void setDeleteFlg(int deleteFlg) {
		this.deleteFlg = deleteFlg;
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
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getInputDiffNums() {
		return inputDiffNums;
	}
	public void setInputDiffNums(int inputDiffNums) {
		this.inputDiffNums = inputDiffNums;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	public int getStockNums() {
		return stockNums;
	}
	public void setStockNums(int stockNums) {
		this.stockNums = stockNums;
	}
	public Date getValidityTime() {
		return validityTime;
	}
	public void setValidityTime(Date validityTime) {
		this.validityTime = validityTime;
	}
	
	
	
	
}
