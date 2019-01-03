package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.osl.model.OutputModel;
import com.osl.model.OutputdetailModel;

@Mapper
public interface OutputMapper {
	
	public OutputModel findOutputById(String OutputId);
	
	public List<OutputModel> findOutputAll(OutputModel outputModel);

	public OutputdetailModel findById(String detailId);
	
	public List<OutputdetailModel> findDetailListById(OutputdetailModel outputdetailModel);
	
	public int deleteOutputById(String outputId);
	
	public int deleteOutputDetailByOutputId(String OutputId);
	
	public int deleteOutputDetailByDetailId(String detailId);
	
	public int saveNewOutputDetail(OutputdetailModel model);
	
	public int updateOutputDetail(OutputdetailModel model);
	
	public int updateOutputDetailStatus(OutputdetailModel model);
	
	public int updateOutput(OutputModel model);
	
	public int cancelOutputById(String outputId);
	
	public int cancelOutputDetailByOutputId(String OutputId);
	
	public int cancelOutputDetailByDetailId(String detailId);
	
	public int findAllOutputNumByGoodsId(String goodsId);
}
