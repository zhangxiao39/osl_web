package com.osl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osl.mapper.SellshipMapper;
import com.osl.mapper.entity.SellshipEntity;
import com.osl.model.SellshipModel;
import com.osl.service.SellshipService;

@Service
public class SellshipServiceImpl implements SellshipService {

	@Autowired
	private SellshipMapper sellshipMapper;

	@Override
	public List<SellshipModel> findAll() {
		// TODO Auto-generated method stub
		return sellshipMapper.findAll();
	}

	@Override
	public List<SellshipModel> find_sellshipBusiness_All(SellshipModel sellshipModel) {
		// TODO Auto-generated method stub
		return sellshipMapper.find_sellshipBusiness_All(sellshipModel);
	}

	@Override
	public int insertSellship(SellshipEntity _sellship) {
		// TODO Auto-generated method stub
		SellshipModel _s = sellshipMapper.find_sellshipBusiness_sellId(_sellship.getBusinessId(), _sellship.getSellId(),
				_sellship.getPlatformId());
		if (_s == null) {
			return sellshipMapper.insertSellship(_sellship);
		} else {
			return -1;
		}
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
	public SellshipEntity findByGoodsId(String goodsId) {
		// TODO Auto-generated method stub
		return sellshipMapper.findByGoodsId(goodsId);
	}

	@Override
	public SellshipModel showById(int id) {
		// TODO Auto-generated method stub
		return sellshipMapper.showById(id);
	}

}
