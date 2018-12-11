package com.osl.service;

import java.util.List;

import com.osl.model.BusinessModel;

public interface BusinessService {
	public List<BusinessModel> findBusinessAll(int bid);
}
