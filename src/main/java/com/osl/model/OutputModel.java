package com.osl.model;

import java.sql.Date;
import java.sql.Timestamp;

import com.osl.common.web.BaseModel;

public class OutputModel extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -983500654804680236L;
	
	private long id;
	private String output_id;
	private Date output_time;
	private int sku_nums;
	private int goods_nums;
	private int status;
	private long business_id;
	private long warehoise_id;
	private int isdele;
	private String oper;
	private Timestamp addtime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOutput_id() {
		return output_id;
	}

	public void setOutput_id(String output_id) {
		this.output_id = output_id;
	}

	public Date getOutput_time() {
		return output_time;
	}

	public void setOutput_time(Date output_time) {
		this.output_time = output_time;
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

	public long getWarehoise_id() {
		return warehoise_id;
	}

	public void setWarehoise_id(long warehoise_id) {
		this.warehoise_id = warehoise_id;
	}

	public int getIsdele() {
		return isdele;
	}

	public void setIsdele(int isdele) {
		this.isdele = isdele;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public Timestamp getAddtime() {
		return addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}

}
