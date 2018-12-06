package com.osl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osl.mapper.CombinationMapper;
import com.osl.mapper.entity.CombinationEntity;
import com.osl.model.CombinationModel;
import com.osl.service.CombinationService;

@Service
public class CombinationServiceImpl implements CombinationService{
	
	@Autowired
	private CombinationMapper combinationMapper;

	@Override
	public int insertCombination(CombinationEntity _combination) {
		// TODO Auto-generated method stub
		return combinationMapper.insertCombination(_combination);
	}

	@Override
	public List<CombinationModel> find_combinationBusiness_All(int bid) {
		// TODO Auto-generated method stub
		return combinationMapper.find_combinationBusiness_All(bid);
	}

}
