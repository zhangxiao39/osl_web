package com.osl.service;

import java.util.List;

import com.osl.mapper.entity.StockEntity;
import com.osl.model.StockModel;;

public interface StockService {
	
	//商家端，根据商家id获取库存列表
	public List<StockModel> find_stockBusiness_All(int bid);
	
	//商家端，条件查询库存信息列表
	public List<StockModel> find_stockBusiness_by_condition(int bid,StockModel stockModel);
	
	//运营商端，根据运营商id获取库存信息列表
	public List<StockModel> find_adminStock_All(int bid);
	
	//运营商端，条件查询库存信息列表
	public List<StockModel> find_adminStock_by_condition(List<String> skuList,StockModel stockModel);
	
	//运营商端，库存详情
	public List<StockModel> find_stock_detail(StockModel stockModel);
	
	//运营商端，条件查找库存详情
	public List<StockModel> find_stock_detail_condition(StockModel stockModel);
	
	//新建库存
	public int inset_stock(StockEntity stockEntity);
	
	//更新库存
	public int update_stock(StockEntity stockEntity);
	
	//根据管理id更改仓库数量
	public int update_stock_by_id(String manageId,int nums);
	
	//逻辑删除库存信息
	public int delete_stock_by_id(String manageId);
}
