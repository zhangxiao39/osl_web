package com.osl.mapper.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserpageEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6919326105892722442L;
	
	private long id;
	private long page_id;
	private long user_id;
	private int right;
	private Timestamp addtime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPage_id() {
		return page_id;
	}

	public void setPage_id(long page_id) {
		this.page_id = page_id;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public Timestamp getAddtime() {
		return addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}

}
