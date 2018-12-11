package com.osl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osl.mapper.SellshipMapper;
import com.osl.mapper.entity.SellshipEntity;
import com.osl.model.SellshipModel;
import com.osl.service.SellshipService;

@Service
public class SellshpServiceImpl implements SellshipService{
	
	@Autowired
	private SellshipMapper sellshipMapper;

	@Override
	public List<SellshipModel> findAll() {
		// TODO Auto-generated method stub
		return sellshipMapper.findAll();
	}

	@Override
	public List<SellshipModel> find_sellshipBusiness_All(int bid) {
		// TODO Auto-generated method stub
		return sellshipMapper.find_sellshipBusiness_All(bid);
	}

	@Override
	public int insertSellship(SellshipEntity _sellship) {
		// TODO Auto-generated method stub
		return sellshipMapper.insertSellship(_sellship);
	}

	@Override
	public int updateSellship(SellshipEntity _sellship) {
		// TODO Auto-generated method stub
		return sellshipMapper.updateSellship(_sellship);
	}

	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return sellshipMapper.deleteById(id);
	}

	@Override
	public SellshipEntity findById(int id) {
		// TODO Auto-generated method stub
		return sellshipMapper.findById(id);
	}

	@Override
	public SellshipEntity findBySku(String sku) {
		// TODO Auto-generated method stub
		return sellshipMapper.findBySku(sku);
	}

}
