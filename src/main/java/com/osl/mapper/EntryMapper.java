package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.osl.mapper.entity.EntryEntity;
import com.osl.mapper.entity.EntrydetailEntity;
import com.osl.model.EntryModel;

@Mapper
public interface EntryMapper {
	
	public List<EntryModel> bQueryEntryListByBusinessId(int bid);
	
	public List<EntryModel> bQueryEntryListByCondition(EntryModel entryModel);
	
	public int bupdateEntryNums(EntryModel entryModel);
	
	public int bupdateEntryStatusById(String entryId);
	
	public Integer insertEntry(EntryEntity entity);
	
	public EntryEntity queryEntryEntityByEntryId(String entryId);
	
	public Integer updateEntryByInputDetail(EntryEntity entity);
	
	public List<EntrydetailEntity> queryEntrydetailEntityListByStatus(int status);
	
	public EntrydetailEntity queryEntrydetailEntityByDetailId(String detailId);
	
	public Integer updateDetailEntryByInputDetail(EntrydetailEntity entity);
	
	
}
