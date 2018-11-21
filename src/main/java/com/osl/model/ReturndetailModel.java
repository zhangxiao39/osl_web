package com.osl.model;

import java.sql.Timestamp;

import com.osl.common.web.BaseModel;

public class ReturndetailModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1406574417962329783L;

	private long id;
	private String detail_id;
	private String sku;
	private int nums;
	private int inner_nums;
	private int inner_goods_nums;
	private int type;
	private String ship_id;
	private int goods_type;
	private String return_id;
	private String order_id;
	private String customer;
	private String send_id;
	private int stutas;
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

	public String getShip_id() {
		return ship_id;
	}

	public void setShip_id(String ship_id) {
		this.ship_id = ship_id;
	}

	public int getGoods_type() {
		return goods_type;
	}

	public void setGoods_type(int goods_type) {
		this.goods_type = goods_type;
	}

	public String getReturn_id() {
		return return_id;
	}

	public void setReturn_id(String return_id) {
		this.return_id = return_id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getSend_id() {
		return send_id;
	}

	public void setSend_id(String send_id) {
		this.send_id = send_id;
	}

	public int getStutas() {
		return stutas;
	}

	public void setStutas(int stutas) {
		this.stutas = stutas;
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
