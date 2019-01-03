package com.osl.service;

import java.util.List;

import com.osl.mapper.entity.DepotEntity;
import com.osl.mapper.entity.GoodsEntity;
import com.osl.model.DepotModel;

public interface DepotService {

	public List<DepotModel> findDepotAll(int bid);
	
	public int insertDepot(DepotEntity _depot);
	
	public int updateDepot(DepotEntity _depot);
	
	public int deleteById(String depotId);
	
	public DepotEntity findById(String depotId);
}
