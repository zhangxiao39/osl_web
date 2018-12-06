package com.osl.mapper.entity;

import java.math.BigDecimal;

import com.osl.common.web.BaseEntity;

public class BalancedetailEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4027773832668439956L;
	
	private long id;
	private String detailId;
	private String sku;
	private int nums;
	private double volume;
	private double weight;
	private BigDecimal price;
	private int type;
	private String balanceId;

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

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getBalanceId() {
		return balanceId;
	}

	public void setBalanceId(String balanceId) {
		this.balanceId = balanceId;
	}

}
