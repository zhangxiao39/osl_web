package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.osl.model.InputModel;
import com.osl.model.InputdetailModel;

@Mapper
public interface InputMapper {
	
	public InputModel findInputById(String inputId);
	
	public List<InputModel> find_inputAll(InputModel iputModel);

	public InputdetailModel findById(String detailId);
	
	public List<InputdetailModel> findDetailListById(String id);
	
	public int deleteInputById(String inputId);
	
	public int deleteInputDetailByInputId(String inputId);
	
	public int deleteInputDetailByDetailId(String detailId);
	
	public int saveNewInputDetail(InputdetailModel model);
	
	public int updateInputDetail(InputdetailModel model);
	
	public int updateInput(InputModel model);
}
