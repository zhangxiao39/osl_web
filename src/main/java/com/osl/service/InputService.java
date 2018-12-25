package com.osl.service;

import java.util.List;

import com.osl.model.InputModel;
import com.osl.model.InputdetailModel;

/**
 * 入库相关查询
 * @author zhangzy
 *
 */
public interface InputService {
	
	/**
	 * 入库列表查询(通过商家ID)
	 */
	public List<InputModel> findInputAll(InputModel iputModel);
	
	public InputModel findInputById(String inputId);
	
	/**
	 * 入库详情查询(通过id)
	 * @param entity
	 * @return
	 */
	public InputdetailModel findById(String detailId);
	
	/**
	 * 根据入库编号查询入库详情列表
	 * @param inputId
	 * @return
	 */
	public List<InputdetailModel> findDetailListById(String id);
	
	/**
	 * 逻辑删除input入库总表数据
	 * @param id
	 * @return
	 */
	public int deleteInputById(String inputId);
	
	/**
	 * 逻辑删除inputdetail入库详情表数据
	 * @param id
	 * @return
	 */
	public int deleteInputDetailByInputId(String inputId);
	
	/**
	 * 逻辑删除inputdetail入库详情表数据
	 * @param id
	 * @return
	 */
	public int deleteInputDetailByDetailId(String detailId);
	
	/**
	 * 新建保存入库详情表数据
	 * @param entity
	 * @return
	 */
	public int saveNewInputDetail(InputdetailModel model);
	
	/**
	 * 修改保存入库详情表数据
	 * @param entity
	 * @return
	 */
	public int updateInputDetail(InputdetailModel newModel);
	
	/**
	 * 删除入库详情表数据
	 * @param entity
	 * @return
	 */
	public int deleteInputDetail(InputdetailModel newModel);
}
