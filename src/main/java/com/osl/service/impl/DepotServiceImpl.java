package com.osl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osl.mapper.DepotMapper;
import com.osl.model.DepotModel;
import com.osl.service.DepotService;

@Service
public class DepotServiceImpl implements DepotService{
	
	@Autowired
	private DepotMapper depotMapper;

	@Override
	public List<DepotModel> findDepotAll(int bid) {
		// TODO Auto-generated method stub
		return depotMapper.findDepotAll(bid);
	}

}
