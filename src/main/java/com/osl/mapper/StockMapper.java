package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.osl.mapper.entity.StockEntity;
import com.osl.model.StockModel;;

@Mapper
public interface StockMapper {
	
	//商家端，根据商家id获取库存列表
	public List<StockModel> find_stockBusiness_All(int bid);
	
	//商家端，条件查询库存信息列表
	public List<StockModel> find_stockBusiness_by_condition(int bid,StockModel stockModel);
	
	//运营商端，根据运营商id获取库存信息列表
	public List<StockModel> find_adminStock_All(int bid);
	
	//运营商端，条件查询库存信息列表
	public List<StockModel> find_adminStock_by_condition(int bid,StockModel stockModel);
	
//	//商家，获取其所对应的所有商品sku
//	public List<String> find_businessSKU(int bid);
//	
//	//运营商，获取其仓库中所有商品的sku
//	public List<String> find_adminSKU(int bid);
	
	
	
}
