package com.osl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osl.mapper.ShelvesMapper;
import com.osl.model.ShelvesModel;
import com.osl.service.ShelvesService;

@Service
public class ShelvesServiceImpl implements ShelvesService{
	
	@Autowired
	private ShelvesMapper shelvesMapper;

	@Override
	public List<ShelvesModel> findShelvesAll(int bid) {
		// TODO Auto-generated method stub
		return shelvesMapper.findShelvesAll(bid);
	}

}
