package com.osl.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osl.mapper.GoodsMapper;
import com.osl.mapper.StockMapper;
import com.osl.mapper.entity.GoodsEntity;
import com.osl.mapper.entity.StockEntity;
import com.osl.model.GoodsModel;
import com.osl.model.StockModel;
import com.osl.service.GoodsService;
import com.osl.service.StockService;

@Service
public class StockServiceImpl implements StockService {
	
	@Autowired
	private StockMapper stockMapper;
	
	@Override
	public List<StockModel> find_stockBusiness_All(int bid) {
		return stockMapper.find_stockBusiness_All(bid);
	}

	@Override
	public List<StockModel> find_stockBusiness_by_condition(int bid,StockModel stockModel) {
		List<StockModel> list =  stockMapper.find_stockBusiness_by_condition(bid, stockModel);
		return list;
	}
	
	@Override
	public List<StockModel> find_adminStock_All(int bid) {
		List<StockModel> list =  stockMapper.find_adminStock_All(bid);
		return list;
	}
	
	@Override
	public List<StockModel> find_adminStock_by_condition(List<String> skuList, StockModel stockModel) {
		List<StockModel> list =  stockMapper.find_adminStock_by_condition(skuList, stockModel);
		return list;
	}

	@Override
	public List<StockModel> find_stock_detail(StockModel stockModel) {
		List<StockModel> list =  stockMapper.find_stock_detail(stockModel);
		return list;
	}

	@Override
	public List<StockModel> find_stock_detail_condition(StockModel stockModel) {
		List<StockModel> list =  stockMapper.find_stock_detail_condition(stockModel);
		return list;
	}

	@Override
	public int inset_stock(StockEntity stockEntity) {
		return stockMapper.inset_stock(stockEntity);
	}

	@Override
	public int update_stock(StockEntity stockEntity) {
		return stockMapper.update_stock(stockEntity);
	}

	@Override
	public int update_stock_by_id(String manageId, int nums) {
		return stockMapper.update_stock_by_id(manageId, nums);
	}

	@Override
	public int delete_stock_by_id(String manageId) {
		return stockMapper.delete_stock_by_id(manageId);
	}
	
}
