package com.osl.mapper.entity;

import com.osl.common.web.BaseEntity;

public class ReturndetailEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8149428640955128379L;

	private long id;
	private String detailId;
	private String sku;
	private int nums;
	private int innerNums;
	private int innerGoodsNums;
	private int type;
	private String shipId;
	private int goodsType;
	private String returnId;
	private String orderId;
	private String customer;
	private String sendId;
	private int status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public int getNums() {
		return nums;
	}

	public void setNums(int nums) {
		this.nums = nums;
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

	public String getReturnId() {
		return returnId;
	}

	public void setReturnId(String returnId) {
		this.returnId = returnId;
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

	public String getSendId() {
		return sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
