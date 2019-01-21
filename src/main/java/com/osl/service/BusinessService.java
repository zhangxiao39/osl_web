package com.osl.service;

import java.util.List;

import com.osl.mapper.entity.BusinessEntity;
import com.osl.mapper.entity.RelationshipEntity;
import com.osl.model.BusinessModel;

public interface BusinessService {

	public List<BusinessModel> findBusinessAll(int bid, int ship);

	public List<BusinessModel> findWarehouseAll(int type);

	public BusinessModel findById(int id);

	public int insert(BusinessEntity _business, int warehouseId);
	
	public int insert(BusinessEntity _business);

	public int update(BusinessEntity _business);

	public int delete(int id);

}
