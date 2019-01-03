package com.osl.service;

import java.util.List;

import com.osl.mapper.entity.ShelvesEntity;
import com.osl.model.ShelvesModel;

public interface ShelvesService {
	public List<ShelvesModel> findShelvesAll(int bid);
	
	public List<ShelvesEntity> queryShelvesEntityListByDepotId (String depotId);
	
	//获取货架一览
	public List<ShelvesModel> queryShelvesGoodsByBid(int bid);
	
	//条件获取货架信息
	public List<ShelvesModel> queryShelvesGoodsCondition(ShelvesModel shelvesModel);
	
	//获取货架详情
	public List<ShelvesModel> queryShelvesDetail(ShelvesModel shelvesModel);
	
	//条件获取货架详情
	public List<ShelvesModel> queryShelvesDetailByCondition(ShelvesModel shelvesModel);
}
