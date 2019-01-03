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
	private String depotId;
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
	
	
	//添加字段
	private int warehouseId;
	private int totalNum;
	private String goodsId;
	private String goodsName;
	private String sku;
	private String barcode;
	private String businessId;
	private String businessName;
	private int nums;
	private int isEmpty;
	
	
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

	
	
	

	public String getDepotId() {
		return depotId;
	}

	public void setDepotId(String depotId) {
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

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public int getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public int getNums() {
		return nums;
	}

	public void setNums(int nums) {
		this.nums = nums;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public int getIsEmpty() {
		return isEmpty;
	}

	public void setIsEmpty(int isEmpty) {
		this.isEmpty = isEmpty;
	}
	
	
}
