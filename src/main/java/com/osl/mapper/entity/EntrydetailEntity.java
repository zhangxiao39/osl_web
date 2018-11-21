package com.osl.mapper.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class EntrydetailEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8846629377542417784L;

	private long id;
	private String detail_id;
	private String sku;
	private int nums;
	private double package_size;
	private int inner_nums;
	private int inner_goods_nums;
	private String sendid;
	private long entry_id;
	private int input_nums;
	private int input_diff;
	private Date produce_time;
	private int max_nums;
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

	public double getPackage_size() {
		return package_size;
	}

	public void setPackage_size(double package_size) {
		this.package_size = package_size;
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

	public String getSendid() {
		return sendid;
	}

	public void setSendid(String sendid) {
		this.sendid = sendid;
	}

	public long getEntry_id() {
		return entry_id;
	}

	public void setEntry_id(long entry_id) {
		this.entry_id = entry_id;
	}

	public int getInput_nums() {
		return input_nums;
	}

	public void setInput_nums(int input_nums) {
		this.input_nums = input_nums;
	}

	public int getInput_diff() {
		return input_diff;
	}

	public void setInput_diff(int input_diff) {
		this.input_diff = input_diff;
	}

	public Date getProduce_time() {
		return produce_time;
	}

	public void setProduce_time(Date produce_time) {
		this.produce_time = produce_time;
	}

	public int getMax_nums() {
		return max_nums;
	}

	public void setMax_nums(int max_nums) {
		this.max_nums = max_nums;
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
