package com.osl.model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.osl.common.web.BaseModel;

public class EntryModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -557330886626093237L;

	private long id;
	private String entry_id;
	private Date oper_time;
	private Date complete_time;
	private int sku_nums;
	private int goods_nums;
	private int input_sku_nums;
	private int input_goods_nums;
	private int status;
	private long business_id;
	private long warehouse_id;
	private String oper;
	private MultipartFile applyFile;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEntry_id() {
		return entry_id;
	}

	public void setEntry_id(String entry_id) {
		this.entry_id = entry_id;
	}

	public Date getOper_time() {
		return oper_time;
	}

	public void setOper_time(Date oper_time) {
		this.oper_time = oper_time;
	}

	public Date getComplete_time() {
		return complete_time;
	}

	public void setComplete_time(Date complete_time) {
		this.complete_time = complete_time;
	}

	public int getSku_nums() {
		return sku_nums;
	}

	public void setSku_nums(int sku_nums) {
		this.sku_nums = sku_nums;
	}

	public int getGoods_nums() {
		return goods_nums;
	}

	public void setGoods_nums(int goods_nums) {
		this.goods_nums = goods_nums;
	}

	public int getInput_sku_nums() {
		return input_sku_nums;
	}

	public void setInput_sku_nums(int input_sku_nums) {
		this.input_sku_nums = input_sku_nums;
	}

	public int getInput_goods_nums() {
		return input_goods_nums;
	}

	public void setInput_goods_nums(int input_goods_nums) {
		this.input_goods_nums = input_goods_nums;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(long business_id) {
		this.business_id = business_id;
	}

	public long getWarehouse_id() {
		return warehouse_id;
	}

	public void setWarehouse_id(long warehouse_id) {
		this.warehouse_id = warehouse_id;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public MultipartFile getApplyFile() {
		return applyFile;
	}

	public void setApplyFile(MultipartFile applyFile) {
		this.applyFile = applyFile;
	}

}
