package com.osl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osl.mapper.ShelvesMapper;
import com.osl.mapper.entity.ShelvesEntity;
import com.osl.model.ShelvesModel;
import com.osl.service.ShelvesService;

@Service
public class ShelvesServiceImpl implements ShelvesService{
	
	@Autowired
	private ShelvesMapper shelvesMapper;

	@Override
	public List<ShelvesModel> findShelvesAll(int bid) {
		// TODO Auto-generated method stub
		return shelvesMapper.findShelvesAll(bid);
	}

	@Override
	public List<ShelvesEntity> queryShelvesEntityListByDepotId(String depotId) {
		// TODO Auto-generated method stub
		return shelvesMapper.queryShelvesEntityListByDepotId(depotId);
	}

	@Override
	public List<ShelvesModel> queryShelvesGoodsByBid(int bid) {
		return shelvesMapper.queryShelvesGoodsByBid(bid);
	}

	@Override
	public List<ShelvesModel> queryShelvesDetail(ShelvesModel shelvesModel) {
		return shelvesMapper.queryShelvesDetail(shelvesModel);
	}

	@Override
	public List<ShelvesModel> queryShelvesDetailByCondition(ShelvesModel shelvesModel) {
		return shelvesMapper.queryShelvesDetailByCondition(shelvesModel);
	}

	@Override
	public List<ShelvesModel> queryShelvesGoodsCondition(ShelvesModel shelvesModel) {
		return shelvesMapper.queryShelvesGoodsCondition(shelvesModel);
	}

}
