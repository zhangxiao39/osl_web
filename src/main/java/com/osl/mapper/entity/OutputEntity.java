package com.osl.mapper.entity;

import java.util.Date;

import com.osl.common.web.BaseEntity;

public class OutputEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7738523941206255545L;
	
	private long id;
	private String outputId;
	private Date outputTime;
	private int skuNums;
	private int goodsNums;
	private int status;
	private long businessId;
	private long warehoiseId;
	private String oper;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOutputId() {
		return outputId;
	}

	public void setOutputId(String outputId) {
		this.outputId = outputId;
	}

	public Date getOutputTime() {
		return outputTime;
	}

	public void setOutputTime(Date outputTime) {
		this.outputTime = outputTime;
	}

	public int getSkuNums() {
		return skuNums;
	}

	public void setSkuNums(int skuNums) {
		this.skuNums = skuNums;
	}

	public int getGoodsNums() {
		return goodsNums;
	}

	public void setGoodsNums(int goodsNums) {
		this.goodsNums = goodsNums;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}

	public long getWarehoiseId() {
		return warehoiseId;
	}

	public void setWarehoiseId(long warehoiseId) {
		this.warehoiseId = warehoiseId;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

}
