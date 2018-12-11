package com.osl.model;

import java.sql.Timestamp;

import com.osl.common.web.BaseModel;

public class ShelvesModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -436034603182963616L;

	private long id;
	private String shelvesId;
	private String name;
	private long depotId;
	private String depotName;
	private String position;
	private int layer;
	private int row;
	private int column;
	private double acreage;
	private String areacode;
	private double bearing;
	private long goods_category_id;
	private int isdele;
	private Timestamp addtime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShelvesId() {
		return shelvesId;
	}

	public void setShelvesId(String shelvesId) {
		this.shelvesId = shelvesId;
	}

	public long getDepotId() {
		return depotId;
	}

	public void setDepotId(long depotId) {
		this.depotId = depotId;
	}
	
	

	public String getDepotName() {
		return depotName;
	}

	public void setDepotName(String depotName) {
		this.depotName = depotName;
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

	public long getGoods_category_id() {
		return goods_category_id;
	}

	public void setGoods_category_id(long goods_category_id) {
		this.goods_category_id = goods_category_id;
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
