package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.osl.mapper.entity.UserEntity;

@Mapper
public interface UserMapper {

	public List<UserEntity> findAll();
	
	public int insert(UserEntity userEntity);
}
