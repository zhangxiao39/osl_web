package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.osl.mapper.entity.CombinationEntity;
import com.osl.model.CombinationModel;

@Mapper
public interface CombinationMapper {
	
	public int insertCombination(CombinationEntity _combination);
	
	public List<CombinationModel> find_combinationBusiness_All(int bid);

}
