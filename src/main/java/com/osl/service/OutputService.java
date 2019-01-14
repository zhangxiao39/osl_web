package com.osl.service;

import java.util.List;

import com.osl.model.OutputModel;
import com.osl.model.OutputdetailModel;

/**
 * 出库相关查询
 * @author zhangzy
 *
 */
public interface OutputService {
	
	/**
	 * 出库列表查询(通过商家ID)
	 */
	public List<OutputModel> findOutputAll(OutputModel outputModel);
	
	public OutputModel findOutputById(String OutputId);
	
	/**
	 * 出库详情查询(通过id)
	 * @param entity
	 * @return
	 */
	public OutputdetailModel findById(String detailId);
	
	/**
	 * 根据出库编号查询出库详情列表
	 * @param OutputId
	 * @return
	 */
	public List<OutputdetailModel> findDetailListById(OutputdetailModel outputdetailModel);
	
	/**
	 * 物理删除Output出库总表数据
	 * @param id
	 * @return
	 */
	public int deleteOutputById(String outputId);
	public int cancelOutputById(String outputId);
	
	/**
	 * 物理删除Outputdetail出库详情表数据
	 * @param id
	 * @return
	 */
	public int deleteOutputDetailByOutputId(String OutputId);
	public int cancelOutputDetailByOutputId(String OutputId);
	
	/**
	 * 物理删除Outputdetail出库详情表数据
	 * @param id
	 * @return
	 */
	public int deleteOutputDetailByDetailId(String detailId);
	public int cancelOutputDetailByDetailId(String detailId);
	
	/**
	 * 新建保存出库详情表数据
	 * @param entity
	 * @return
	 */
	public int saveNewOutputDetail(List<OutputdetailModel> modelList , OutputModel fModel);
	
	/**
	 * 修改保存出库详情表数据
	 * @param entity
	 * @return
	 */
	public int updateOutputDetail(OutputdetailModel newModel);
	public int updateOutputDetailStatus(OutputdetailModel newModel);
	
	/**
	 * 删除出库详情表数据
	 * @param entity
	 * @return
	 */
	public int deleteOutputDetail(OutputdetailModel newModel);
	public int cancelOutputDetail(OutputdetailModel newModel);
}
