package com.osl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osl.mapper.BusinessMapper;
import com.osl.model.BusinessModel;
import com.osl.service.BusinessService;

@Service
public class BusinessServiceImpl implements BusinessService {

	@Autowired
	private BusinessMapper businessMapper;
	
	@Override
	public List<BusinessModel> findBusinessAll(int bid) {
		// TODO Auto-generated method stub
		return businessMapper.findBusinessAll(bid);
	}

}
