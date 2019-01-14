package com.osl.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osl.mapper.GoodsMapper;
import com.osl.mapper.StockMapper;
import com.osl.mapper.TakeStockMapper;
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
	
	@Autowired
	private TakeStockMapper takeStockMapper;
	
	@Override
	public List<StockModel> find_stockBusiness_All(int bid) {
		return stockMapper.find_stockBusiness_All(bid);
	}

	
	@Override
	public List<StockModel> find_stockBusiness_by_condition(List<String> skuList, StockModel stockModel) {
		List<StockModel> list =  stockMapper.find_stockBusiness_by_condition(skuList, stockModel);
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
	@Transactional
	public int update_stock(StockEntity stockEntity) {
		return stockMapper.update_stock(stockEntity);
	}

	@Override
	@Transactional
	public int update_stock_by_id(String manageId, int nums) {
		return stockMapper.update_stock_by_id(manageId, nums);
	}

	@Override
	@Transactional
	public int delete_stock_by_id(String manageId) {
		return stockMapper.delete_stock_by_id(manageId);
	}


	@Override
	public List<StockModel> find_stock_list(int bid) {
		return takeStockMapper.find_stock_list(bid);
	}


	@Override
	public List<StockModel> find_stock_list_by_condition(List<String> skuList, StockModel stockModel) {
		return takeStockMapper.find_stock_list_by_condition(skuList, stockModel);
	}


	@Override
	public List<StockModel> find_Business_All_Goods_Stock(int bid) {
		return stockMapper.find_Business_All_Goods_Stock(bid);
	}


	@Override
	public List<StockModel> find_Business_All_Goods_by_condition(List<String> skuList, StockModel stockModel) {
		return stockMapper.find_Business_All_Goods_by_condition(skuList, stockModel);
	}


	@Override
	public List<StockModel> find_stock_detail_by_output(StockModel stockModel) {
		// TODO Auto-generated method stub
		return stockMapper.find_stock_detail_by_output(stockModel);
	}

	
}
