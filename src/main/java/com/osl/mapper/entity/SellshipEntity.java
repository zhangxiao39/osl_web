package com.osl.mapper.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class SellshipEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4041629551203973953L;

	private long id;
	private String sell_id;
	private String sku;
	private long business_id;
	private long platform_id;
	private int type;
	private Date deletime;
	private int isdele;
	private Timestamp addtime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSell_id() {
		return sell_id;
	}

	public void setSell_id(String sell_id) {
		this.sell_id = sell_id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public long getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(long business_id) {
		this.business_id = business_id;
	}

	public long getPlatform_id() {
		return platform_id;
	}

	public void setPlatform_id(long platform_id) {
		this.platform_id = platform_id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getDeletime() {
		return deletime;
	}

	public void setDeletime(Date deletime) {
		this.deletime = deletime;
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
