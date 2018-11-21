package com.osl.model;

import java.sql.Timestamp;

import com.osl.common.web.BaseModel;

public class UserpageModel extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 179660991133878121L;
	
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
