package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.osl.mapper.entity.GoodsEntity;
import com.osl.model.GoodsModel;

@Mapper
public interface GoodsMapper {
	
	public List<GoodsModel> find_goodsAll();
	
	public List<GoodsModel> find_goodsBusiness_All(int bid);
	
	public int insertGoods(GoodsEntity _goods);
	
	public int updateGoods(GoodsEntity _goods);
	
	public int deleteById(int id);
	
	public GoodsEntity findById(int id);
	
	public GoodsEntity findBySku(String sku);

}
