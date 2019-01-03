package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.osl.mapper.entity.DepotEntity;
import com.osl.model.DepotModel;

@Mapper
public interface DepotMapper {
	public List<DepotModel> findDepotAll(int bid);
	
	public int insertDepot(DepotEntity _depot);
	
	public int updateDepot(DepotEntity _depot);
	
	public int deleteById(String depotId);
	
	public DepotEntity findById(String depotId);
}
