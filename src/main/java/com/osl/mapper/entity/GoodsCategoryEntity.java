package com.osl.mapper.entity;

import com.osl.common.web.BaseEntity;

public class GoodsCategoryEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8337607626880499633L;

	private long id;
	private String name;
	private long parentId;
	private int level;
	private String position;

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

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
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

}
