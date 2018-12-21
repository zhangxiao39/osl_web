package com.osl.service;

import java.util.List;

import com.osl.mapper.entity.SellshipEntity;
import com.osl.model.SellshipModel;

public interface SellshipService {
	public List<SellshipModel> findAll();

	public List<SellshipModel> find_sellshipBusiness_All(SellshipModel sellshipModel);

	public int insertSellship(SellshipEntity _sellship);

	public int updateSellship(SellshipEntity _sellship);

	public int deleteById(int id);

	public SellshipEntity findById(int id);

	public SellshipEntity findByGoodsId(String goodsId);
	
	public SellshipModel showById(int id);
}
