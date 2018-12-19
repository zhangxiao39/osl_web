package com.osl.service;

import java.util.List;

import com.osl.mapper.entity.EntrydetailEntity;
import com.osl.model.EntryModel;

public interface EntryService {

	public boolean entryUpload(EntryModel userModel);
	
	/**
	 * 按照状态查询纳品详细表信息
	 */
	public List<EntrydetailEntity> queryEntrydetailEntityListByStatus(int status);
	
	/**
	 * 按照detailId查询纳品详细表信息
	 */
	public EntrydetailEntity queryEntrydetailEntityByDetailId(String detailId);
}
