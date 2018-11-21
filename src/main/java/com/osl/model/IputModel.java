package com.osl.model;

import java.sql.Date;
import java.sql.Timestamp;

import com.osl.common.web.BaseModel;

public class IputModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4075837601944893951L;
	
	private long id;
	private String input_id;
	private Date intime;
	private int sku_nums;
	private int goods_nums;
	private int status;
	private long business_id;
	private long warehouse_id;
	private String oper;
	private int isdele;
	private Timestamp addtime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getInput_id() {
		return input_id;
	}

	public void setInput_id(String input_id) {
		this.input_id = input_id;
	}

	public Date getIntime() {
		return intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
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

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
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
