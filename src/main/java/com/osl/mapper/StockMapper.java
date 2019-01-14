package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.osl.mapper.entity.StockEntity;
import com.osl.model.StockModel;;

@Mapper
public interface StockMapper {
	
	public List<StockModel> find_stockBusiness_All(int bid);
	
	public List<StockModel> find_Business_All_Goods_Stock(int bid);
	
	public List<StockModel> find_stockBusiness_by_condition(@Param("skuList") List<String> skuList,StockModel stockModel);
	
	public List<StockModel> find_Business_All_Goods_by_condition(@Param("skuList") List<String> skuList,StockModel stockModel);
	
	public List<StockModel> find_adminStock_All(int bid);
	
	public List<StockModel> find_adminStock_by_condition(@Param("skuList") List<String> skuList,StockModel stockModel);
	
	public List<StockModel> find_stock_detail(StockModel stockModel);

	public List<StockModel> find_stock_detail_condition(StockModel stockModel);
	
	public int inset_stock(StockEntity stockEntity);
	
	public int update_stock(StockEntity stockEntity);
	
	public int update_stock_by_id(@Param("manageId") String manageId, @Param("nums") int nums);
	
	public int delete_stock_by_id(String manageId);
	
	public int findStockModelAllByGoodsId(String goodsId);
	
	public List<StockModel> find_stock_detail_by_output(StockModel stockModel);
}
