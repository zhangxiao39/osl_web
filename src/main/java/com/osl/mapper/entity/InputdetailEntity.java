package com.osl.mapper.entity;


import java.sql.Date;
import java.sql.Timestamp;

import com.osl.common.web.BaseEntity;

public class InputdetailEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2573774686422360672L;
	
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
	private String inputId;
	private Date validityTime;
	private int status;
	private int deleteFlag;
	private Timestamp newDate;
	private Timestamp updateDate;
	

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

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
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
