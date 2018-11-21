package com.osl.mapper.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class ReturnEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 579287072342708218L;
	
	private long id;
	private String return_id;
	private Date apply_time;
	private int sku_nums;
	private int goods_nums;
	private int status;
	private long business_id;
	private long warehouse_id;
	private int isdele;
	private String oper;
	private Timestamp addtime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getReturn_id() {
		return return_id;
	}

	public void setReturn_id(String return_id) {
		this.return_id = return_id;
	}

	public Date getApply_time() {
		return apply_time;
	}

	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
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

	public long getWarehouse_id() {
		return warehouse_id;
	}

	public void setWarehouse_id(long warehouse_id) {
		this.warehouse_id = warehouse_id;
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
