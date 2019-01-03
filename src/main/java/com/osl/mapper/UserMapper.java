package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.osl.mapper.entity.UserEntity;
import com.osl.model.UserModel;

@Mapper
public interface UserMapper {

	public List<UserEntity> findAll();
	
	public int insert(UserEntity userEntity);
	
	public UserModel login(String userId,String password);
	
	public List<UserModel> getAllUsers(int bid);
	
	public UserEntity findByUserId(String userId);
	
	public int stopUser(int id);
	
	public int recoveryUser(int id);
	
	public int deleteById(int id);
}
