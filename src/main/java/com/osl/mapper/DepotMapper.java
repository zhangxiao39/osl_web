package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.osl.model.DepotModel;

@Mapper
public interface DepotMapper {
	public List<DepotModel> findDepotAll(int bid);
}
