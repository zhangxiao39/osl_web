package com.osl.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.osl.mapper.entity.UserEntity;
import com.osl.model.UserModel;

public interface UserService {
	
	public List<UserEntity> findAll();
	
	public int insert(UserEntity userEntity);
	
	public UserModel login(String userId,String password);
	
	public List<UserModel> getAllUsers(int bid);
	
	public int stopUser(int id);
	
	public int recoveryUser(int id);
	
	public int deleteById(int id);
}
