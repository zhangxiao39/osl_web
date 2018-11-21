package com.osl.mapper.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class GoodsCategoryEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8337607626880499633L;

	private long id;
	private String name;
	private long parent_id;
	private int level;
	private String position;
	private Timestamp addtime;
	private int isdele;

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

	public long getParent_id() {
		return parent_id;
	}

	public void setParent_id(long parent_id) {
		this.parent_id = parent_id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Timestamp getAddtime() {
		return addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}

	public int getIsdele() {
		return isdele;
	}

	public void setIsdele(int isdele) {
		this.isdele = isdele;
	}

}
