package com.osl.mapper.entity;

import com.osl.common.web.BaseEntity;

public class CombinationEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7035246633898565039L;

	private long id;
	private String combinationId;
	private String name;
	private String sku;
	private int nums;
	private long businessId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getCombinationId() {
		return combinationId;
	}

	public void setCombinationId(String combinationId) {
		this.combinationId = combinationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}

}
