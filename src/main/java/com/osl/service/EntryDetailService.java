package com.osl.service;

import java.util.List;

import com.osl.mapper.entity.EntryEntity;
import com.osl.mapper.entity.EntrydetailEntity;
import com.osl.model.EntryModel;
import com.osl.model.EntrydetailModel;

public interface EntryDetailService {
	
	
	 /**
	  * @des 【商家端】，根据纳品id获取纳品详情列表
	  * @param entryId
	  * @return List<EntryDetailModel>
	  */
	public List<EntrydetailModel> bQueryEntryDetailListByEntryId(String entryId);
	
	/**
	 * @des 【商家端】，根据纳品id更改纳品详情状态为取消
	 * @param entryId
	 * @return int
	 */
	public int bupdateStatusByEntryId(String entryId);
	
	/**
	 * @des 【商家端】，逻辑删除纳品详情
	 * @param entryDetailId
	 * @return int
	 */
	public int  bDeleteEntryDetailById(String entryDetailId);
	
	/**
	 * @des 【商家端】，物理删除纳品详情
	 * @param entryDetailId
	 * @return int
	 */
	public int bPhysicsDeleteEntryDetailById(String entryDetailId);
	
	/**
	 * @des 【商家端】，物理删除纳品详情
	 * @param EntrydetailModel
	 * @return List<EntrydetailModel>
	 */
	public List<EntrydetailModel> bQueryEntryDetailListByCondition(EntrydetailModel entryDetailModel);
	
	/**
	 * @des 【商家端】，更新入库数量以及货运单号
	 * @param EntrydetailModel
	 * @return int
	 */
	public int bupdateInputNumsAndNumbers(EntrydetailModel entryDetailModel);
	
}
