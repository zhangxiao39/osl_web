package com.osl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osl.mapper.GoodsMapper;
import com.osl.mapper.InputMapper;
import com.osl.mapper.entity.GoodsEntity;
import com.osl.mapper.entity.InputEntity;
import com.osl.mapper.entity.InputdetailEntity;
import com.osl.model.GoodsModel;
import com.osl.model.InputdetailModel;
import com.osl.model.IputModel;
import com.osl.service.GoodsService;
import com.osl.service.InputService;

@Service
public class InputServiceImpl implements InputService {
	
	@Autowired
	private InputMapper inputMapper;

	@Override
	public InputdetailEntity findById(String detailId) {
		// TODO Auto-generated method stub
		return inputMapper.findById(detailId);
	}

	@Override
	public List<IputModel> findInputAll(IputModel iputModel) {
		// TODO Auto-generated method stub
		return inputMapper.find_inputAll(iputModel);
	}

	@Override
	public List<InputdetailModel> findDetailListById(String id) {
		// TODO Auto-generated method stub
		return inputMapper.findDetailListById(id);
	}

	@Override
	@Transactional
	public int deleteInputById(int id) {
		// TODO Auto-generated method stub
		//逻辑删除本次入库编号对应的入库详情信息
		int flag = inputMapper.deleteInputById(id);
		flag = inputMapper.deleteInputDetailByInputId(id);
		return flag;
	}

	@Override
	public int deleteInputDetailByInputId(int id) {
		// TODO Auto-generated method stub
		return inputMapper.deleteInputDetailByInputId(id);
	}

	/**
	 * 处理逻辑：
	 * 1.按照入库详情对象中的shipId(对应纳品详情表的detail_id) 查询 纳品详情表 中 同一纳品id 的纳品信息是否都入库完了 ，
	 *   如果有其他纳品没有入库，那么当前 入库详情 对应的入库总表中状态为 入库中
	 *   
	 * 2.根据入库详情对象中的shipId，查询同一纳品id 对应的纳品详情列表 是否有入库记录，如果有则 将此入库记录中的 入库id 取出作为 此次入库详情的入库id
	 * 
	 * 3.确认好 入库总表 的id和 状态后 ，新增/修改  入库总表的信息（sku数量、商品总数、更新时间、状态）
	 * 
	 * 4.当入库详情对象中的状态为入库完了时，向库存表保存一条记录
	 * 
	 * 5.更新入库详情对象中shipId对应的纳品详情对象（入库数量、入库差异数量、更新时间、状态）
	 * 
	 * 6.根据此纳品详情中的纳品id，反查对应的纳品一览表中的状态，并且对比状态，更新纳品一览表（状态、更新时间、入库商品sku数量、入库商品数量）
	 * 
	 * 7.新增入库详情对象到数据库
	 */
	@Override
	public int saveNewInputDetail(InputdetailModel model) {
		// TODO Auto-generated method stub
		
		
		//保存入库详情表
		int flag = inputMapper.saveNewInputDetail(model);
		//
		return flag;
	}


}
