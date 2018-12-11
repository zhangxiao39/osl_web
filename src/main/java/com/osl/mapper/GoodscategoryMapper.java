package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.osl.mapper.entity.GoodsCategoryEntity;
import com.osl.model.GoodsCategoryModel;

@Mapper
public interface GoodscategoryMapper {

	public List<GoodsCategoryModel> getCategoryAll();
	
	public List<GoodsCategoryModel> getCategoryByC1();
	
	public List<GoodsCategoryModel> getCategoryByC2();
	
	public int insertGoodsCategory(GoodsCategoryEntity _goodscategory);
	
	public GoodsCategoryEntity findById(long l);
	
	public int updateGoodscategoryName(long id,String name);
	
	public int deleteById(int id);
}
