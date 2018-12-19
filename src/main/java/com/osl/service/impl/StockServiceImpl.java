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
	
	//商家端，根据商家id获取库存列表
	@Override
	public List<StockModel> find_stockBusiness_All(int bid) {
		return stockMapper.find_stockBusiness_All(bid);
	}

	//商家端，条件查询库存信息列表
	@Override
	public List<StockModel> find_stockBusiness_by_condition(int bid,StockModel stockModel) {
		List<StockModel> list =  stockMapper.find_stockBusiness_by_condition(bid, stockModel);
		return list;
	}
	
	//运营商端，根据运营商id获取库存信息列表
	@Override
	public List<StockModel> find_adminStock_All(int bid) {
		List<StockModel> list =  stockMapper.find_adminStock_All(bid);
		return list;
	}
	
	//运营商端，条件查询库存信息列表
	@Override
	public List<StockModel> find_adminStock_by_condition(int bid, StockModel stockModel) {
		List<StockModel> list =  stockMapper.find_adminStock_by_condition(bid, stockModel);
		return list;
	}
	
}
