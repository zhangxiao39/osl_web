package com.osl.service;

import java.util.List;

import com.osl.mapper.entity.InputEntity;
import com.osl.mapper.entity.InputdetailEntity;
import com.osl.model.InputdetailModel;
import com.osl.model.IputModel;

/**
 * 入库相关查询
 * @author zhangzy
 *
 */
public interface InputService {
	
	/**
	 * 入库列表查询(通过商家ID)
	 */
	public List<IputModel> findInputAll(IputModel iputModel);
	
	/**
	 * 入库详情查询(通过id)
	 * @param entity
	 * @return
	 */
	public InputdetailEntity findById(int id);
	
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
	public int deleteInputById(int id);
	
	/**
	 * 逻辑删除inputdetail入库详情表数据
	 * @param id
	 * @return
	 */
	public int deleteInputDetailByInputId(int id);
}
