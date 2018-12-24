package com.osl.model;

import java.sql.Timestamp;

import com.osl.common.web.BaseModel;

public class BusinessModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2892011105224748264L;

	private long id;
	private String name;
	private String address;
	private String postcode;
	private String url;
	private int parent_id;
	private String tele;
	private String tax;
	private String email;
	private String contacts;
	private int type;
	private Timestamp addtime;
	private String right;
	private int gradeId;
	private String gradeName;
	private int ship;
	public String shipText;
	private int userId;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
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

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public int getShip() {
		return ship;
	}

	public void setShip(int ship) {
		switch (ship) {
		case 0:
			this.shipText = "申请中";
			break;
		case 1:
			this.shipText = "已关联";
			break;
		case 2:
			this.shipText = "关联停止";
			break;
		}
		this.ship = ship;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
