package com.osl.mapper.entity;

import java.io.Serializable;
import java.sql.Date;

public class StockEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -729969280894158993L;

	private long id;
	private String manage_id;
	private String sku;
	private long depot_id;
	private long shelves_id;
	private int nums;
	private int goods_type;
	private Date intime;
	private double volume;
	private Date produce_time;
	private Date validity_time;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getManage_id() {
		return manage_id;
	}

	public void setManage_id(String manage_id) {
		this.manage_id = manage_id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public long getDepot_id() {
		return depot_id;
	}

	public void setDepot_id(long depot_id) {
		this.depot_id = depot_id;
	}

	public long getShelves_id() {
		return shelves_id;
	}

	public void setShelves_id(long shelves_id) {
		this.shelves_id = shelves_id;
	}

	public int getNums() {
		return nums;
	}

	public void setNums(int nums) {
		this.nums = nums;
	}

	public int getGoods_type() {
		return goods_type;
	}

	public void setGoods_type(int goods_type) {
		this.goods_type = goods_type;
	}

	public Date getIntime() {
		return intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public Date getProduce_time() {
		return produce_time;
	}

	public void setProduce_time(Date produce_time) {
		this.produce_time = produce_time;
	}

	public Date getValidity_time() {
		return validity_time;
	}

	public void setValidity_time(Date validity_time) {
		this.validity_time = validity_time;
	}

}
