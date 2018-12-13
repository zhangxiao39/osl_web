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

}
