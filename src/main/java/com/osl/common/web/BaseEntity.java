package com.osl.common.web;

import java.io.Serializable;
import java.sql.Timestamp;

public abstract class BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -690650996235708047L;

	private Timestamp newDate;
	
	private Timestamp updateDate;
	
	private int deleteFlg;

	public Timestamp getNewDate() {
		return newDate;
	}

	public void setNewDate(Timestamp newDate) {
		this.newDate = newDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public int getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(int deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
}
