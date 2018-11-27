package com.osl.service;

import java.util.List;

import com.osl.mapper.entity.TestUserEntity;
import com.osl.model.TestUserModel;

public interface TestUserService {

	public List<TestUserEntity> findUserAll();
	
	public List<TestUserModel> findUserList() throws Exception;
}
