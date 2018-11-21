package com.osl.model;

import java.sql.Timestamp;

import com.osl.common.web.BaseModel;

public class CombinationModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -632367102705708464L;

	private long id;
	private String combinaton_id;
	private String name;
	private String sku;
	private int nums;
	private long business_id;
	private int isdele;
	private Timestamp addtime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCombinaton_id() {
		return combinaton_id;
	}

	public void setCombinaton_id(String combinaton_id) {
		this.combinaton_id = combinaton_id;
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
