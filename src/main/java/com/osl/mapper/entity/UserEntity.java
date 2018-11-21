package com.osl.mapper.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.util.DigestUtils;

public class UserEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 446756133368831879L;
	
	private long id;
    private String user_id;
    private String password;
    private String username;
    private String right;
    private long business_id;
    private Timestamp addTime;
    private int status;
    private String url;
    private String bname;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

	public String getuser_id() {
        return user_id;
    }

    public void setuser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = DigestUtils.md5DigestAsHex(password.getBytes());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }
    
    public long getbusiness_id() {
        return business_id;
    }

    public void setbusiness_id(long business_id) {
        this.business_id = business_id;
    }

    public Timestamp getAddtime() {
        return addTime;
    }

    public void setAddtime(Timestamp addTime) {
        this.addTime = addTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

}
