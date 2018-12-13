package com.osl.mapper.entity;

import java.util.Date;

import com.osl.common.web.BaseEntity;

public class InputdetailEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2573774686422360672L;
	
	private long id;
	private String detailId;
	private String sku;
	private int nums;
	private long depotId;
	private long shelvesId;
	private int innerNums;
	private int innerGoodsNums;
	private int type;
	private long shipId;
	private int goodsType;
	private String inputId;
	private Date validityTime;
	private int status;
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

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

	public long getShipId() {
		return shipId;
	}

	public void setShipId(long shipId) {
		this.shipId = shipId;
	}

	public int getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(int goodsType) {
		this.goodsType = goodsType;
	}

	public String getInputId() {
		return inputId;
	}

	public void setInputId(String inputId) {
		this.inputId = inputId;
	}

	public Date getValidityTime() {
		return validityTime;
	}

	public void setValidityTime(Date validityTime) {
		this.validityTime = validityTime;
	}

}
