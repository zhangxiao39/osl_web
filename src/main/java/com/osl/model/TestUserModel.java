package com.osl.model;

import com.osl.common.web.BaseModel;

public class TestUserModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6096328724556612055L;
	
	private String id;
	
	private String username;
	
	private String birthday;
	
	private String sex;
	
	private String address;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
