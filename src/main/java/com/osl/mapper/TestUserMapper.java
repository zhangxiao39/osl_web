package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.osl.mapper.entity.TestUserEntity;

@Mapper
public interface TestUserMapper {

	public List<TestUserEntity> findAll();
}
