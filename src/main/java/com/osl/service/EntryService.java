package com.osl.service;

import java.util.List;

import com.osl.mapper.entity.EntryEntity;
import com.osl.mapper.entity.EntrydetailEntity;
import com.osl.model.EntryModel;

public interface EntryService {
	
	
	 /**
	  * @des 【商家端】，获取纳品列表
	  * @param bid
	  * @return List<EntryModel>
	  */
	public List<EntryModel> bQueryEntryListByBusinessId(int bid);
	
	public boolean entryUpload(EntryModel userModel);
	
	public EntryEntity queryEntryEntityByEntryId(String entryId);
	
	public Integer updateEntryByInputDetail(EntryEntity entity);
	/**
	 * 按照状态查询纳品详细表信息
	 */
	public List<EntrydetailEntity> queryEntrydetailEntityListByStatus(int status);
	
	/**
	 * 按照detailId查询纳品详细表信息
	 */
	public EntrydetailEntity queryEntrydetailEntityByDetailId(String detailId);
	
	public Integer updateDetailEntryByInputDetail(EntrydetailEntity entity);
}
