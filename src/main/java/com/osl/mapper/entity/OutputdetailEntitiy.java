package com.osl.mapper.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class OutputdetailEntitiy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6714357063323807397L;
	
	private long id;
	private String detail_id;
	private String sku;
	private int nums;
	private long depot_id;
	private long shelves_id;
	private int inner_nums;
	private int inner_goods_nums;
	private int type;
	private String ship_id;
	private int goods_type;
	private String output_id;
	private String customer;
	private String postcode;
	private String address1;
	private String address2;
	private String tele;
	private int send_type;
	private String send_id;
	private int transport_mode;
	private int iscombination;
	private int status;
	private long return_id;
	private String return_send_id;
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

	public String getOutput_id() {
		return output_id;
	}

	public void setOutput_id(String output_id) {
		this.output_id = output_id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public int getSend_type() {
		return send_type;
	}

	public void setSend_type(int send_type) {
		this.send_type = send_type;
	}

	public String getSend_id() {
		return send_id;
	}

	public void setSend_id(String send_id) {
		this.send_id = send_id;
	}

	public int getTransport_mode() {
		return transport_mode;
	}

	public void setTransport_mode(int transport_mode) {
		this.transport_mode = transport_mode;
	}

	public int getIscombination() {
		return iscombination;
	}

	public void setIscombination(int iscombination) {
		this.iscombination = iscombination;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getReturn_id() {
		return return_id;
	}

	public void setReturn_id(long return_id) {
		this.return_id = return_id;
	}

	public String getReturn_send_id() {
		return return_send_id;
	}

	public void setReturn_send_id(String return_send_id) {
		this.return_send_id = return_send_id;
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
