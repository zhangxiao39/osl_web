package com.osl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osl.mapper.EntrydetailMapper;
import com.osl.model.EntrydetailModel;
import com.osl.service.EntryDetailService;

@Service
public class EntryDetailServiceImpl implements EntryDetailService {
	@Autowired
	private EntrydetailMapper entryDetailMapper;

	@Override
	public List<EntrydetailModel> bQueryEntryDetailListByEntryId(String entryId) {
		return entryDetailMapper.bQueryEntryDetailListByEntryId(entryId);
	}

	@Override
	public int bupdateStatusByEntryId(String entryId) {
		return entryDetailMapper.bupdateStatusByEntryId(entryId);
	}

	@Override
	public int bDeleteEntryDetailById(String entryDetailId) {
		return entryDetailMapper.bDeleteEntryDetailById(entryDetailId);
	}

	@Override
	public int bPhysicsDeleteEntryDetailById(String entryDetailId) {
		return entryDetailMapper.bPhysicsDeleteEntryDetailById(entryDetailId);
	}

	@Override
	public List<EntrydetailModel> bQueryEntryDetailListByCondition(EntrydetailModel entryDetailModel) {
		return entryDetailMapper.bQueryEntryDetailListByCondition(entryDetailModel);
	}

	@Override
	public int bupdateInputNumsAndNumbers(EntrydetailModel entryDetailModel) {
		return entryDetailMapper.bupdateInputNumsAndNumbers(entryDetailModel);
	}
	
	
}
