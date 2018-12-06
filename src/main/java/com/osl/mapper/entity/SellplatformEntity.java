package com.osl.mapper.entity;

import com.osl.common.web.BaseEntity;

public class SellplatformEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1171583413496210880L;

	private long id;
	private String name;
	private long businessId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}

}
