package com.osl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osl.mapper.BusinessMapper;
import com.osl.mapper.entity.BusinessEntity;
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

	@Override
	public BusinessModel findById(int id) {
		// TODO Auto-generated method stub
		return businessMapper.findById(id);
	}

	@Override
	public int insert(BusinessEntity _business) {
		// TODO Auto-generated method stub
		return businessMapper.insert(_business);
	}

	@Override
	public int update(BusinessEntity _business) {
		// TODO Auto-generated method stub
		return businessMapper.update(_business);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return businessMapper.delete(id);
	}

}
