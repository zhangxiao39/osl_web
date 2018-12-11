package com.osl.service;

import java.util.List;

import com.osl.model.BalanceModel;
import com.osl.model.BalancebaseModel;

public interface BalanceService {
	public List<BalancebaseModel> findBalanceAll(int bid);

}
