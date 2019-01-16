package com.osl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osl.mapper.BusinessMapper;
import com.osl.mapper.RelationshipMapper;
import com.osl.mapper.entity.BusinessEntity;
import com.osl.mapper.entity.RelationshipEntity;
import com.osl.model.BusinessModel;
import com.osl.service.BusinessService;

@Service
public class BusinessServiceImpl implements BusinessService {

	@Autowired
	private BusinessMapper businessMapper;
	@Autowired
	private RelationshipMapper relationshipMapper;

	@Override
	public List<BusinessModel> findBusinessAll(int bid, int ship) {
		// TODO Auto-generated method stub
		return businessMapper.findBusinessAll(bid, ship);
	}

	@Override
	public BusinessModel findById(int id) {
		// TODO Auto-generated method stub
		return businessMapper.findById(id);
	}

	@Override
	@Transactional
	public int insert(BusinessEntity _business, int warehouseId) {
		// TODO Auto-generated method stub
		int ok = 0;
		ok = businessMapper.insert(_business);
		RelationshipEntity relationshipEntity = new RelationshipEntity();
		relationshipEntity.setBusinessId((int) _business.getId());
		relationshipEntity.setWarehouseId(warehouseId);
		relationshipEntity.setShip(1);
		ok = relationshipMapper.insert(relationshipEntity);
		return ok;
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

	/**
	* <p>Title: findWarehouseAll</p>  
	* <p>Description: </p>  
	* @param ship
	* @return  
	* @see com.osl.service.BusinessService#findWarehouseAll(int)  
	*/  
	@Override
	public List<BusinessModel> findWarehouseAll() {
		// TODO Auto-generated method stub
		return businessMapper.findWarehouseAll();
	}

	/**
	* <p>Title: insert</p>  
	* <p>Description: </p>  
	* @param _business
	* @return  
	* @see com.osl.service.BusinessService#insert(com.osl.mapper.entity.BusinessEntity)  
	*/  
	@Override
	public int insert(BusinessEntity _business) {
		// TODO Auto-generated method stub
		int ok = 0;
		ok = businessMapper.insert(_business);
		return ok;
	}

}
