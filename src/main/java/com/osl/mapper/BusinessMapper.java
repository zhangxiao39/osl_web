package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.osl.model.BusinessModel;

@Mapper
public interface BusinessMapper {

	public List<BusinessModel> findBusinessAll(int bid);
}
