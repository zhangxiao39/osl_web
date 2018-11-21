package com.osl.model;

import java.sql.Timestamp;

import com.osl.common.web.BaseModel;

public class DepotModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2599925481362946040L;

	private long id;
	private String depot_id;
	private String name;
	private String address;
	private String postcode;
	private long business_id;
	private int isdele;
	private Timestamp addtime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDepot_id() {
		return depot_id;
	}

	public void setDepot_id(String depot_id) {
		this.depot_id = depot_id;
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

	public long getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(long business_id) {
		this.business_id = business_id;
	}

	public int getIsdele() {
		return isdele;
	}

	public void setIsdele(int isdele) {
		this.isdele = isdele;
	}

	public Timestamp getAddtime() {
		return addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}

}
