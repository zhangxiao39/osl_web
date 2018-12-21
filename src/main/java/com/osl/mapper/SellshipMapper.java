package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.osl.mapper.entity.SellshipEntity;
import com.osl.model.SellshipModel;

@Mapper
public interface SellshipMapper {

	public List<SellshipModel> findAll();

	public List<SellshipModel> find_sellshipBusiness_All(SellshipModel sellshipModel);

	public int insertSellship(SellshipEntity _sellship);

	public int updateSellship(SellshipEntity _sellship);

	public int deleteById(int id);

	public SellshipEntity findById(int id);
	
	public SellshipModel showById(int id);

	public SellshipEntity findByGoodsId(String goodsId);

	public SellshipModel find_sellshipBusiness_sellId(long bid, String sellId, long platformId);
}
