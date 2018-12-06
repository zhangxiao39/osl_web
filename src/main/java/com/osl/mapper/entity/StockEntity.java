package com.osl.mapper.entity;

import java.util.Date;

import com.osl.common.web.BaseEntity;

public class StockEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -729969280894158993L;

	private long id;
	private String manageId;
	private String sku;
	private long depotId;
	private long shelvesId;
	private int nums;
	private int goodsType;
	private Date intime;
	private double volume;
	private Date produceTime;
	private Date validityTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getManageId() {
		return manageId;
	}

	public void setManageId(String manageId) {
		this.manageId = manageId;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
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

	public Date getIntime() {
		return intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
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

}
