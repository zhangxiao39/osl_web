package com.osl.mapper.entity;

import com.osl.common.web.BaseEntity;

public class PageEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -839201864407990835L;
	
	private long id;
	private String name;
	private String right;
	private long menuId;
	private int type;

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

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}

	public long getMenuId() {
		return menuId;
	}

	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
