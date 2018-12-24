package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.osl.mapper.entity.BusinessGradeEntity;
import com.osl.model.BusinessGradeModel;

@Mapper
public interface BusinessGradeMapper {
	
	public List<BusinessGradeModel> findAll(int bid);
	
	public BusinessGradeModel findById(int id);
	
	public int insert(BusinessGradeEntity _businessGrade);
	
	public int update(BusinessGradeEntity _businessGrade);
	
	public int delete(int id);

}
