package com.osl.mapper.entity;

import com.osl.common.web.BaseEntity;

public class DepotEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6584555484868362545L;

	private long id;
	private String depotId;
	private String name;
	private String address;
	private String postcode;
	private long businessId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDepotId() {
		return depotId;
	}

	public void setDepotId(String depotId) {
		this.depotId = depotId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}

}
