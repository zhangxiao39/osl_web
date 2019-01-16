package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.osl.mapper.entity.BusinessEntity;
import com.osl.model.BusinessModel;

@Mapper
public interface BusinessMapper {

	public List<BusinessModel> findBusinessAll(int bid,int ship);
	
	public List<BusinessModel> findWarehouseAll();
	
	public BusinessModel findById(int id);
	
	public int insert(BusinessEntity _businessGrade);
	
	public int update(BusinessEntity _businessGrade);
	
	public int delete(int id);
	
	public int insertRelation(int bid,int wid);
}
