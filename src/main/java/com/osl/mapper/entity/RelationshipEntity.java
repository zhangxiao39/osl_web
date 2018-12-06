package com.osl.mapper.entity;

import com.osl.common.web.BaseEntity;

public class RelationshipEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9065180641037261334L;

	private long id;
	private int businessId;
	private int warehouseId;
	private int ship;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getBusinessId() {
		return businessId;
	}

	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}

	public int getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	public int getShip() {
		return ship;
	}

	public void setShip(int ship) {
		this.ship = ship;
	}

}
