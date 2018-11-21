package com.osl.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.osl.common.web.BaseModel;

public class BalancedetailModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7744623581990388151L;

	private long id;
	private String detail_id;
	private String sku;
	private int nums;
	private double volume;
	private double weight;
	private BigDecimal price;
	private int type;
	private String balance_id;
	private Timestamp addtime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDetail_id() {
		return detail_id;
	}

	public void setDetail_id(String detail_id) {
		this.detail_id = detail_id;
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

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getBalance_id() {
		return balance_id;
	}

	public void setBalance_id(String balance_id) {
		this.balance_id = balance_id;
	}

	public Timestamp getAddtime() {
		return addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}

}
