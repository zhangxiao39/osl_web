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
	
	/**
	 * @des 【商家端】，条件获取纳品类别
	 * @param entryModel
	 * @return List<EntryMode>
	 */
	public List<EntryModel> bQueryEntryListByCondition(EntryModel entryModel);
	
	/**
	 * @des 【商家端】，根据纳品id，将纳品状态更改为4，取消状态
	 * @param String
	 * @return int
	 */
	public int bupdateEntryStatusById(String entryId);
	
	/**
	 * @des 【商家端】，根据纳品id，将纳品数量信息
	 * @param String
	 * @return int
	 */
	public int bupdateEntryNums(EntryModel entryModel);
	
	
	
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
