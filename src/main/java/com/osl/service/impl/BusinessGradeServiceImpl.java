package com.osl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osl.mapper.BusinessGradeMapper;
import com.osl.mapper.entity.BusinessGradeEntity;
import com.osl.model.BusinessGradeModel;
import com.osl.service.BusinessGradeService;

@Service
public class BusinessGradeServiceImpl implements BusinessGradeService{
	
	@Autowired
	private BusinessGradeMapper businessGradeMapper;

	@Override
	public List<BusinessGradeModel> findAll(int bid) {
		// TODO Auto-generated method stub
		return businessGradeMapper.findAll(bid);
	}

	@Override
	public int insert(BusinessGradeEntity _businessGrade) {
		// TODO Auto-generated method stub
		return businessGradeMapper.insert(_businessGrade);
	}

	@Override
	public int update(BusinessGradeEntity _businessGrade) {
		// TODO Auto-generated method stub
		return businessGradeMapper.update(_businessGrade);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return businessGradeMapper.delete(id);
	}

	@Override
	public BusinessGradeModel findById(int id) {
		// TODO Auto-generated method stub
		return businessGradeMapper.findById(id);
	}
	

}
