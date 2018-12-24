package com.osl.mapper.entity;

import com.osl.common.web.BaseEntity;

public class BusinessGradeEntity extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3838683790510000363L;
	
	
	private int id;
	private String name;
	private Double discount;
	private int businessId;
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
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	
	

}
