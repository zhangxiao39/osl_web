package com.osl.model;

import java.sql.Date;
import java.sql.Timestamp;

import com.osl.common.web.BaseModel;

public class InputdetailModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4398144474494325708L;
	
	private long id;
	private String detail_id;
	private String sku;
	private int nums;
	private long depot_id;
	private long shelves_id;
	private int inner_nums;
	private int inner_goods_nums;
	private int type;
	private long ship_id;
	private int goods_type;
	private String input_id;
	private Date validity_time;
	private int isdele;
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

	public int getInner_nums() {
		return inner_nums;
	}

	public void setInner_nums(int inner_nums) {
		this.inner_nums = inner_nums;
	}

	public int getInner_goods_nums() {
		return inner_goods_nums;
	}

	public void setInner_goods_nums(int inner_goods_nums) {
		this.inner_goods_nums = inner_goods_nums;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getShip_id() {
		return ship_id;
	}

	public void setShip_id(long ship_id) {
		this.ship_id = ship_id;
	}

	public int getGoods_type() {
		return goods_type;
	}

	public void setGoods_type(int goods_type) {
		this.goods_type = goods_type;
	}

	public String getInput_id() {
		return input_id;
	}

	public void setInput_id(String input_id) {
		this.input_id = input_id;
	}

	public Date getValidity_time() {
		return validity_time;
	}

	public void setValidity_time(Date validity_time) {
		this.validity_time = validity_time;
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
