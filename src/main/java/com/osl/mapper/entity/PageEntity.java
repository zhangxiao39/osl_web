package com.osl.mapper.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class PageEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -839201864407990835L;
	
	private long id;
	private String name;
	private String right;
	private long menu_id;
	private int type;
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

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}

	public long getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(long menu_id) {
		this.menu_id = menu_id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Timestamp getAddtime() {
		return addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}

}
