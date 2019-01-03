package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.osl.mapper.entity.StockEntity;
import com.osl.model.StockModel;;

@Mapper
public interface TakeStockMapper {
	
	public List<StockModel> find_stock_list(int bid);
	
	public List<StockModel> find_stock_list_by_condition(@Param("skuList") List<String> skuList,StockModel stockModel);
	
	
	
}
