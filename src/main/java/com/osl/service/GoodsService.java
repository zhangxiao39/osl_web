package com.osl.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import com.osl.mapper.entity.GoodsEntity;
import com.osl.model.GoodsModel;

public interface GoodsService {
	
	public List<GoodsModel> find_goodsAll(GoodsModel goodsModel);
	
	public List<GoodsModel> find_goodsBusiness_All(int bid);
	
	public int insertGoods(GoodsEntity _goods);
	
	public int updateGoods(GoodsEntity _goods);
	
	public int deleteById(String goodsId);
	
	public GoodsEntity findById(String goodsId);
	
	public GoodsEntity findBySku(String sku);
	
	public GoodsModel find_goodsBusiness_sku(int bid,String sku);

}
