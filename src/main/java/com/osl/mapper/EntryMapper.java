package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.osl.mapper.entity.EntryEntity;
import com.osl.mapper.entity.EntrydetailEntity;

@Mapper
public interface EntryMapper {

	public Integer insertEntry(EntryEntity entity);
	
	public EntryEntity queryEntryEntityByEntryId(String entryId);
	
	public Integer updateEntryByInputDetail(EntryEntity entity);
	
	public List<EntrydetailEntity> queryEntrydetailEntityListByStatus(int status);
	
	public EntrydetailEntity queryEntrydetailEntityByDetailId(String detailId);
	
	public Integer updateDetailEntryByInputDetail(EntrydetailEntity entity);
}
