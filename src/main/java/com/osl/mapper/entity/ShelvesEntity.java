package com.osl.mapper.entity;

import com.osl.common.web.BaseEntity;

public class ShelvesEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2610604201828689346L;

	private String shelvesId;
	private String name;
	private String depotId;
	private String position;
	private int layer;
	private int row;
	private int column;
	private double acreage;
	private String areacode;
	private double bearing;
	private long goodsCategoryId;
	
	public String getShelvesId() {
		return shelvesId;
	}

	public void setShelvesId(String shelvesId) {
		this.shelvesId = shelvesId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepotId() {
		return depotId;
	}

	public void setDepotId(String depotId) {
		this.depotId = depotId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public double getAcreage() {
		return acreage;
	}

	public void setAcreage(double acreage) {
		this.acreage = acreage;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public double getBearing() {
		return bearing;
	}

	public void setBearing(double bearing) {
		this.bearing = bearing;
	}

	public long getGoodsCategoryId() {
		return goodsCategoryId;
	}

	public void setGoodsCategoryId(long goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}

}
