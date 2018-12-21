package com.osl.service;

import java.util.List;

import com.osl.mapper.entity.GoodsCategoryEntity;
import com.osl.model.GoodsCategoryModel;

public interface GoodscategoryService {
	public List<GoodsCategoryModel> getCategoryAll();

	public List<GoodsCategoryModel> getCategoryByC1();

	public List<GoodsCategoryModel> getCategoryByC2();
	
	//��ȡҶ�ӽڵ����
	public List<GoodsCategoryModel> getCategoryMin();
	
	public String setCategoryHtml();
	
	public int insertGoodsCategory(GoodsCategoryEntity _goodscategory);
	
	public int updateGoodscategoryName(GoodsCategoryEntity _goodscategory);
	
	public int deleteById(int id);
	
	public String getGoodsCategorySelect(String qry_categoryId);
	
	public String getGoodsCategorySelect2();
}
