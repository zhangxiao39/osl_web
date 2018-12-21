package com.osl.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osl.mapper.CombinationMapper;
import com.osl.mapper.entity.CombinationEntity;
import com.osl.model.CombinationModel;
import com.osl.service.CombinationService;

@Service
public class CombinationServiceImpl implements CombinationService {

	@Autowired
	private CombinationMapper combinationMapper;

	@Override
	public List<CombinationModel> find_combination_All(CombinationModel combinationModel) {
		// TODO Auto-generated method stub
		return combinationMapper.find_combination_All(combinationModel);
	}

	@Override
	@Transactional
	public int insertCombinations(List<CombinationEntity> _combinations) throws IOException {
		// TODO Auto-generated method stub
		int ok = 0;
		for (int i = 0; i < _combinations.size(); i++) {
			CombinationEntity _c = _combinations.get(i);
			combinationMapper.insertCombination(_c);
		}
		ok = 1;
		return ok;
	}

	@Override
	public int doInsertCombinations(List<CombinationEntity> _combinations) throws IOException {
		int ok = 0;
		List<CombinationModel> _e = combinationMapper.find_combinationBusiness_combinationId(
				(int) _combinations.get(0).getBusinessId(), _combinations.get(0).getCombinationId());
		if (_e.size() > 0) {
			ok = -1;
		} else {

			ok = this.insertCombinations(_combinations);
		}

		return ok;
	}

	@Override
	public List<CombinationModel> find_combinationByCode(String combinationId, int bid) {
		// TODO Auto-generated method stub
		return combinationMapper.find_combinationByCode(combinationId, bid);
	}

	@Override
	@Transactional
	public int deleteByCode(String combinationId, int bid) throws IOException {
		// TODO Auto-generated method stub
		return combinationMapper.deleteByCode(combinationId, bid);
	}

	@Override
	@Transactional
	public int updateCombinations(List<CombinationEntity> _combinations) throws IOException {
		// TODO Auto-generated method stub
		int ok = 0;
		String combinationId = _combinations.get(0).getCombinationId();
		int bid = (int) _combinations.get(0).getBusinessId();
		combinationMapper.deleteByCode(combinationId, bid);
		for (int i = 0; i < _combinations.size(); i++) {
			CombinationEntity _c = _combinations.get(i);
			combinationMapper.insertCombination(_c);
		}
		ok = 1;

		return ok;
	}

}
