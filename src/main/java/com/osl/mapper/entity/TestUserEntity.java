package com.osl.mapper.entity;

import java.io.Serializable;
import java.util.Date;

public class TestUserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7609689265402079199L;
	
	private Integer id;
	
	private String username;
	
	private Date birthday;
	
	private String sex;
	
	private String address;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
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
