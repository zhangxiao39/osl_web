package com.osl.service;

import java.util.List;

import com.osl.mapper.entity.SellplatformEntity;
import com.osl.model.SellplatformModel;

public interface SellplatformService {
	
	public List<SellplatformModel> find_sellplatform_All(int bid);
	
	public int insertSellplatform(SellplatformEntity _sellplatform);
	
	public int updateSellplatform(SellplatformEntity _sellplatform);
	
	public int deleteById(int id);
	
	public SellplatformEntity findById(int id);

}
