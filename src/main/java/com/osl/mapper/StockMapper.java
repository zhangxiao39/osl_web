package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.osl.mapper.entity.StockEntity;
import com.osl.model.StockModel;;

@Mapper
public interface StockMapper {
	
	public List<StockModel> find_stockBusiness_All(int bid);
	
	public List<StockModel> find_stockBusiness_by_condition(int bid,StockModel stockModel);
	
	public List<StockModel> find_adminStock_All(int bid);
}
