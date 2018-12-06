package com.osl.mapper.entity;

import org.springframework.util.DigestUtils;

import com.osl.common.web.BaseEntity;

public class UserEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 446756133368831879L;
	
	private long id;
    private String userId;
    private String password;
    private String username;
    private String right;
    private long businessId;
    private int status;
    private String url;
    private String bname;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

    public long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(long businessId) {
		this.businessId = businessId;
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
