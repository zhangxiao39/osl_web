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
	
	//�̼Ҷˣ������̼�id��ȡ����б�
	@Override
	public List<StockModel> find_stockBusiness_All(int bid) {
		return stockMapper.find_stockBusiness_All(bid);
	}

	//�̼Ҷˣ�������ѯ�����Ϣ�б�
	@Override
	public List<StockModel> find_stockBusiness_by_condition(int bid,StockModel stockModel) {
		List<StockModel> list =  stockMapper.find_stockBusiness_by_condition(bid, stockModel);
		return list;
	}
	
	//��Ӫ�̶ˣ�������Ӫ��id��ȡ�����Ϣ�б�
	@Override
	public List<StockModel> find_adminStock_All(int bid) {
		List<StockModel> list =  stockMapper.find_adminStock_All(bid);
		return list;
	}
	
	//��Ӫ�̶ˣ�������ѯ�����Ϣ�б�
	@Override
	public List<StockModel> find_adminStock_by_condition(int bid, StockModel stockModel) {
		List<StockModel> list =  stockMapper.find_adminStock_by_condition(bid, stockModel);
		return list;
	}
	
}
