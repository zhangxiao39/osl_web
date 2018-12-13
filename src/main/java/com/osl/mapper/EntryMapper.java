package com.osl.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.osl.mapper.entity.EntryEntity;

@Mapper
public interface EntryMapper {

	public Integer insertEntry(EntryEntity entity);
}
