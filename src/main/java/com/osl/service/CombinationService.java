package com.osl.service;

import java.io.IOException;
import java.util.List;

import com.osl.mapper.entity.CombinationEntity;
import com.osl.model.CombinationModel;

public interface CombinationService {
	
	public int insertCombinations(List<CombinationEntity> _combinations) throws IOException;
	
	public List<CombinationModel> find_combinationBusiness_All(int bid);
	
	public List<CombinationModel> find_combinationByCode(String combinationId,int bid);
	
	public int deleteByCode(String combinationId,int bid);
	
	public int updateCombinations(List<CombinationEntity> _combinations) throws IOException;
}
