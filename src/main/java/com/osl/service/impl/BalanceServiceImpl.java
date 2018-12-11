package com.osl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osl.mapper.BalanceMapper;
import com.osl.model.BalanceModel;
import com.osl.model.BalancebaseModel;
import com.osl.service.BalanceService;

@Service
public class BalanceServiceImpl implements BalanceService {
	
	@Autowired
	private BalanceMapper balanceMapper;

	@Override
	public List<BalancebaseModel> findBalanceAll(int bid) {
		// TODO Auto-generated method stub
		return balanceMapper.findBalanceAll(bid);
	}
}
