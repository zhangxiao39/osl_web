package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.osl.mapper.entity.ShelvesEntity;
import com.osl.model.ShelvesModel;

@Mapper
public interface ShelvesMapper {
	
	public List<ShelvesModel> findShelvesAll(int bid);

	public List<ShelvesEntity> queryShelvesEntityListByDepotId(String depotId);
	
}
