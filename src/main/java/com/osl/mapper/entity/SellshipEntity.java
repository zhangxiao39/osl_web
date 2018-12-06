package com.osl.mapper.entity;

import com.osl.common.web.BaseEntity;

public class SellshipEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4041629551203973953L;

	private long id;
	private String sellId;
	private String sku;
	private long businessId;
	private long platformId;
	private int type;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSellId() {
		return sellId;
	}

	public void setSellId(String sellId) {
		this.sellId = sellId;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}

	public long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(long platformId) {
		this.platformId = platformId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
