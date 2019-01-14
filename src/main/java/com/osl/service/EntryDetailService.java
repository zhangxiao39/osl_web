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
	 * @des 【商家端】，根据纳品id更改纳品详情状态
	 * @param EntrydetailModel entryDetailModel
	 * @return int
	 */
	public int bupdateStatusByEntryId(EntrydetailModel entryDetailModel);
	
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
	 * @return String
	 */
	public String bupdateInputNumsAndNumbers(EntrydetailModel entryDetailModel);
	
	/**
	 * @des 【商家端】，追加纳品商品
	 * @param EntrydetailModel :传递过来的纳品详情信息
	 * 		  newFlag:为“yes”时表示新添加一个商品，为“no”表示在原纳品信息上进行添加
	 * @return String
	 */
	public String additionalEntryGoods(EntrydetailModel entryDetailModel,String newFlag);
}
