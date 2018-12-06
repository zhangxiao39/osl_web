package com.osl.mapper.entity;

import com.osl.common.web.BaseEntity;

public class OutputdetailEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6714357063323807397L;
	
	private long id;
	private String detailId;
	private String sku;
	private int nums;
	private long depotId;
	private long shelvesId;
	private int innerNums;
	private int innerGoodsNums;
	private int type;
	private String shipId;
	private int goodsType;
	private String outputId;
	private String customer;
	private String postcode;
	private String address1;
	private String address2;
	private String tele;
	private int sendType;
	private String sendId;
	private int transportMode;
	private int iscombination;
	private int status;
	private long returnId;
	private String returnSendId;

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

	public long getDepotId() {
		return depotId;
	}

	public void setDepotId(long depotId) {
		this.depotId = depotId;
	}

	public long getShelvesId() {
		return shelvesId;
	}

	public void setShelvesId(long shelvesId) {
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getReturnId() {
		return returnId;
	}

	public void setReturnId(long returnId) {
		this.returnId = returnId;
	}

	public String getReturnSendId() {
		return returnSendId;
	}

	public void setReturnSendId(String returnSendId) {
		this.returnSendId = returnSendId;
	}

}
