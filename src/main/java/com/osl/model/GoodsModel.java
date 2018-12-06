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
	private int categoryId;
	private String categoryName;
	private String sku;
	private String barcode;
	private String name;
	private int shape;
	private double volume;
	private double length;
	private double width;
	private double height;
	private double weight;
	private int businessId;
	private String color;
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

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getShape() {
		return shape;
	}

	public void setShape(int shape) {
		this.shape = shape;
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

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getBusinessId() {
		return businessId;
	}

	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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
