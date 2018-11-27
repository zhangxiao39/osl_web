package com.osl.common.web;

import java.io.Serializable;

public class BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5280765075459181608L;
	
	private String errorMsg;

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
