package com.osl.model;

import java.sql.Timestamp;

import com.osl.common.web.BaseModel;

public class OutputdetailModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6865989049544998983L;
	
	private String detailId;
	private String goodsId;
	private int nums;
	private String depotId;
	private String shelvesId;
	private int innerNums;
	private int innerGoodsNums;
	private int type;
	private String shipId;
	private int goodsType;
	private String outputId;
	private String orderId;
	private String customer;
	private String postcode;
	private String address1;
	private String address2;
	private String tele;
	private int sendType;
	private String sendId;
	private int transportMode;
	private int iscombination;
	private int combinationId;
	private int status;
	private String returnId;
	private String returnSendId;
	private int deleteFlg;
	private Timestamp newDate;
	private Timestamp updateDate;
	
	//销售平台
	private String sellplatformName;
	//销售平台id
	private String sellplatformId;
	//分类名称
	private String categoryName;
	//组合品名称
	private String combinationName;
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
	//请求日期
	private Timestamp requestDate;
	
	
	public String getSellplatformId() {
		return sellplatformId;
	}
	public void setSellplatformId(String sellplatformId) {
		this.sellplatformId = sellplatformId;
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
	public Timestamp getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Timestamp requestDate) {
		this.requestDate = requestDate;
	}
	public String getCombinationName() {
		return combinationName;
	}
	public void setCombinationName(String combinationName) {
		this.combinationName = combinationName;
	}
	public String getSellplatformName() {
		return sellplatformName;
	}
	public void setSellplatformName(String sellplatformName) {
		this.sellplatformName = sellplatformName;
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
	public int getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(int goodsType) {
		this.goodsType = goodsType;
	}
	public String getOutputId() {
		return outputId;
	}
	public void setOutputId(String outputId) {
		this.outputId = outputId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getTele() {
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
	}
	public int getSendType() {
		return sendType;
	}
	public void setSendType(int sendType) {
		this.sendType = sendType;
	}
	public String getSendId() {
		return sendId;
	}
	public void setSendId(String sendId) {
		this.sendId = sendId;
	}
	public int getTransportMode() {
		return transportMode;
	}
	public void setTransportMode(int transportMode) {
		this.transportMode = transportMode;
	}
	public int getIscombination() {
		return iscombination;
	}
	public void setIscombination(int iscombination) {
		this.iscombination = iscombination;
	}
	public int getCombinationId() {
		return combinationId;
	}
	public void setCombinationId(int combinationId) {
		this.combinationId = combinationId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getReturnId() {
		return returnId;
	}
	public void setReturnId(String returnId) {
		this.returnId = returnId;
	}
	public String getReturnSendId() {
		return returnSendId;
	}
	public void setReturnSendId(String returnSendId) {
		this.returnSendId = returnSendId;
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
}
