package com.osl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osl.mapper.SellplatformMapper;
import com.osl.mapper.entity.SellplatformEntity;
import com.osl.model.SellplatformModel;
import com.osl.service.SellplatformService;

@Service
public class SellplatformServiceImpl implements SellplatformService{
	
	@Autowired
	private SellplatformMapper sellplatformMapper;

	@Override
	public List<SellplatformModel> find_sellplatform_All(int bid) {
		// TODO Auto-generated method stub
		return sellplatformMapper.find_sellplatform_All(bid);
	}

	@Override
	public int insertSellplatform(SellplatformEntity _sellplatform) {
		// TODO Auto-generated method stub
		return sellplatformMapper.insertSellplatform(_sellplatform);
	}

	@Override
	public int updateSellplatform(SellplatformEntity _sellplatform) {
		// TODO Auto-generated method stub
		return sellplatformMapper.updateSellplatform(_sellplatform);
	}

	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return sellplatformMapper.deleteById(id);
	}

	@Override
	public SellplatformEntity findById(int id) {
		// TODO Auto-generated method stub
		return sellplatformMapper.findById(id);
	}

}
