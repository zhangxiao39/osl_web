package com.osl.mapper.entity;

import java.math.BigDecimal;

import com.osl.common.web.BaseEntity;

public class BalancebaseEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5720179160128725985L;

	private long id;
	private int grade;
	private long businessId;
	private long warehouseId;
	private double minVolume;
	private double maxVolume;
	private double minWeight;
	private double maxWeight;
	private int type;
	private BigDecimal price;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
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

	public double getMinVolume() {
		return minVolume;
	}

	public void setMinVolume(double minVolume) {
		this.minVolume = minVolume;
	}

	public double getMaxVolume() {
		return maxVolume;
	}

	public void setMaxVolume(double maxVolume) {
		this.maxVolume = maxVolume;
	}

	public double getMinWeight() {
		return minWeight;
	}

	public void setMinWeight(double minWeight) {
		this.minWeight = minWeight;
	}

	public double getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(double maxWeight) {
		this.maxWeight = maxWeight;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
