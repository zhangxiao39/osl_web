package com.osl.service;

import java.util.List;

import com.osl.mapper.entity.CombinationEntity;
import com.osl.model.CombinationModel;

public interface CombinationService {
	
	public int insertCombination(CombinationEntity _combination);
	
	public List<CombinationModel> find_combinationBusiness_All(int bid);
}
