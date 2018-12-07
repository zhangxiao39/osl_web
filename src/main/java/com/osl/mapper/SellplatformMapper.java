package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.osl.mapper.entity.SellplatformEntity;
import com.osl.model.SellplatformModel;


@Mapper
public interface SellplatformMapper {
	
	public List<SellplatformModel> find_sellplatform_All(int bid);
	
	public int insertSellplatform(SellplatformEntity _sellplatform);
	
	public int updateSellplatform(SellplatformEntity _sellplatform);
	
	public int deleteById(int id);
	
	public SellplatformEntity findById(int id);

}
