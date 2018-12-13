package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.osl.mapper.entity.GoodsEntity;
import com.osl.mapper.entity.InputEntity;
import com.osl.mapper.entity.InputdetailEntity;
import com.osl.model.GoodsModel;
import com.osl.model.InputdetailModel;
import com.osl.model.IputModel;

@Mapper
public interface InputMapper {
	
	public List<IputModel> find_inputAll(IputModel iputModel);

	public InputdetailEntity findById(int id);
	
	public List<InputdetailModel> findDetailListById(String id);
	
	public int deleteInputById(int id);
	
	public int deleteInputDetailByInputId(int id);
}
