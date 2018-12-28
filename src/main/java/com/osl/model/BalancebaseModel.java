package com.osl.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.osl.common.web.BaseModel;

public class BalancebaseModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1363268128981085391L;

	private long id;
	private int grade;
	private long business_id;
	private long warehouse_id;
	private String bName;
	private double minVolume;
	private double maxVolume;
	private double minWeight;
	private double maxWeight;
	private int type;
	private BigDecimal price;
	private Timestamp addtime;
	public String typeText;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
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
		switch (type) {
		case 1:
			this.typeText = "入库";
			break;
		case 2:
			this.typeText = "出库";
			break;
		case 3:
			this.typeText = "保管";
			break;
		case 4:
			this.typeText = "废弃";
			break;
		}
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
