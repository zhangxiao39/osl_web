package com.osl.mapper.entity;

import com.osl.common.web.BaseEntity;

public class UserpageEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6919326105892722442L;
	
	private long id;
	private long pageId;
	private long userId;
	private int right;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPageId() {
		return pageId;
	}

	public void setPageId(long pageId) {
		this.pageId = pageId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

}
