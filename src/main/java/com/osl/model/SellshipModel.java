package com.osl.model;

import java.sql.Date;
import java.sql.Timestamp;

import com.osl.common.web.BaseModel;

public class SellshipModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4033050999939859802L;

	private long id;
	private String sellId;
	private String goodsId;
	private long businessId;
	private long platformId;
	private String platformName;
	private int type;
	private Date deletime;
	private int isdele;
	private Timestamp addtime;
	public String typeText;
	private String sku;
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSellId() {
		return sellId;
	}
	
	

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSellId(String sellId) {
		this.sellId = sellId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
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

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		switch (type) {
		case 0:
			this.typeText = "单品";
			break;
		case 1:
			this.typeText = "组合品";
			break;
		}
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
