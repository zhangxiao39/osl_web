package com.osl.service;

import java.util.List;

import com.osl.mapper.entity.BusinessGradeEntity;
import com.osl.model.BusinessGradeModel;

public interface BusinessGradeService {
	
	public List<BusinessGradeModel> findAll(int bid);
	
	public int insert(BusinessGradeEntity _businessGrade);
	
	public int update(BusinessGradeEntity _businessGrade);
	
	public int delete(int id);
	
	public BusinessGradeModel findById(int id);

}
