package com.osl.model;

import java.sql.Timestamp;

import com.osl.common.web.BaseModel;

public class SellplatformModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3346402568011650966L;

	private long id;
	private String platformId;
	private String name;
	private long business_id;
	private int isdele;
	private Timestamp addtime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
