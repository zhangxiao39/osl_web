package com.osl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osl.mapper.GoodsMapper;
import com.osl.mapper.entity.GoodsEntity;
import com.osl.model.GoodsModel;
import com.osl.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {
	
	@Autowired
	private GoodsMapper goodsMapper;

	@Override
	public List<GoodsModel> find_goodsAll() {
		// TODO Auto-generated method stub
		return goodsMapper.find_goodsAll();
	}

	@Override
	public int insertGoods(GoodsEntity _goods) {
		// TODO Auto-generated method stub
		return goodsMapper.insertGoods(_goods);
	}

	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return goodsMapper.deleteById(id);
	}

	@Override
	public GoodsEntity findById(int id) {
		// TODO Auto-generated method stub
		return goodsMapper.findById(id);
	}

	@Override
	public int updateGoods(GoodsEntity _goods) {
		// TODO Auto-generated method stub
		return goodsMapper.updateGoods(_goods);
	}

	@Override
	public List<GoodsModel> find_goodsBusiness_All(int bid) {
		// TODO Auto-generated method stub
		return goodsMapper.find_goodsBusiness_All(bid);
	}

	@Override
	public GoodsEntity findBySku(String sku) {
		// TODO Auto-generated method stub
		return goodsMapper.findBySku(sku);
	}

}
