package com.osl.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class BalancebaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5720179160128725985L;

	private long id;
	private int grade;
	private long business_id;
	private long warehouse_id;
	private double min_volume;
	private double max_volume;
	private double min_weight;
	private double max_weight;
	private int type;
	private BigDecimal price;
	private Timestamp addtime;

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

	public long getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(long business_id) {
		this.business_id = business_id;
	}

	public long getWarehouse_id() {
		return warehouse_id;
	}

	public void setWarehouse_id(long warehouse_id) {
		this.warehouse_id = warehouse_id;
	}

	public double getMin_volume() {
		return min_volume;
	}

	public void setMin_volume(double min_volume) {
		this.min_volume = min_volume;
	}

	public double getMax_volume() {
		return max_volume;
	}

	public void setMax_volume(double max_volume) {
		this.max_volume = max_volume;
	}

	public double getMin_weight() {
		return min_weight;
	}

	public void setMin_weight(double min_weight) {
		this.min_weight = min_weight;
	}

	public double getMax_weight() {
		return max_weight;
	}

	public void setMax_weight(double max_weight) {
		this.max_weight = max_weight;
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

	public Timestamp getAddtime() {
		return addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}

}
