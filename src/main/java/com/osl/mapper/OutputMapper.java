package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.osl.model.OutputModel;
import com.osl.model.OutputdetailModel;

@Mapper
public interface OutputMapper {
	
	public OutputModel findOutputById(String OutputId);
	
	public List<OutputModel> findOutputAll(OutputModel outputModel);

	public OutputdetailModel findById(String detailId);
	
	public List<OutputdetailModel> findDetailListById(OutputdetailModel outputdetailModel);
	
	public List<OutputdetailModel> countDetailStatus(OutputdetailModel outputdetailModel);
	
	public int deleteOutputById(String outputId);
	
	public int deleteOutputDetailByOutputId(String OutputId);
	
	public int deleteOutputDetailByDetailId(String detailId);
	
	public int saveNewOutput(OutputModel fModel);
	
	public int saveNewOutputDetail(List<OutputdetailModel> modelList);
	
	public int updateOutputDetail(OutputdetailModel model);
	
	public int updateOutputDetailStatus(OutputdetailModel model);
	
	public int updateOutput(OutputModel model);
	
	public int cancelOutputById(@Param("outputId") String outputId , @Param("status") int status);
	
	public int cancelOutputDetailByOutputId(@Param("outputId") String outputId , @Param("status") int status);
	
	public int cancelOutputDetailByDetailId(@Param("detailId") String detailId , @Param("status") int status);
	
	public int findAllOutputNumByGoodsId(String goodsId);
}
