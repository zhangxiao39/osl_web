package com.osl.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import com.osl.common.web.BaseModel;

public class GoodsModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6377691010821265641L;

	private long id;
	private String sku;
	private String name;
	private double volume;
	private double length;
	private double width;
	private double height;
	private double weight;
	private BigDecimal price;
	private int business_id;
	private String color;
	private Date validity_time;
	private int isdele;
	private String remark;
	private Timestamp addtime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
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

	public int getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(int business_id) {
		this.business_id = business_id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getAddtime() {
		return addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}
}
