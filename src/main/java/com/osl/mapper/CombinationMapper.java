package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.osl.mapper.entity.CombinationEntity;
import com.osl.model.CombinationModel;

@Mapper
public interface CombinationMapper {

	public int insertCombinations(List<CombinationEntity> _combinations);

	public List<CombinationModel> find_combination_All(CombinationModel combinationModel);

	public int insertCombination(CombinationEntity _combination);

	public List<CombinationModel> find_combinationByCode(String combinationId, int bid);

	public int deleteByCode(String combinationId, int bid);

	public int updateCombinations(List<CombinationEntity> _combinations);

	public int updateCombination(CombinationEntity _combination, int bid);
	
	public List<CombinationModel> find_combinationBusiness_combinationId(int bid,String combinationId);

}
