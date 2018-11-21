package com.osl.mapper.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class RelationshipEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9065180641037261334L;

	private long id;
	private int business_id;
	private int warehouse_id;
	private int ship;
	private Timestamp addtime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(int business_id) {
		this.business_id = business_id;
	}

	public int getWarehouse_id() {
		return warehouse_id;
	}

	public void setWarehouse_id(int warehouse_id) {
		this.warehouse_id = warehouse_id;
	}

	public int getShip() {
		return ship;
	}

	public void setShip(int ship) {
		this.ship = ship;
	}

	public Timestamp getAddtime() {
		return addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}
}
