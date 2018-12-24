package com.osl.model;

import com.osl.common.web.BaseModel;

public class BusinessGradeModel extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3074285028697579810L;
	private int id;
	private String name;
	private Double discount;
	private int buisnessId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public int getBuisnessId() {
		return buisnessId;
	}
	public void setBuisnessId(int buisnessId) {
		this.buisnessId = buisnessId;
	}
	
	
	

}
