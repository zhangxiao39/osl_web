package com.osl.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osl.mapper.EntryMapper;
import com.osl.mapper.OutputMapper;
import com.osl.mapper.StockMapper;
import com.osl.mapper.entity.EntryEntity;
import com.osl.mapper.entity.EntrydetailEntity;
import com.osl.mapper.entity.StockEntity;
import com.osl.model.InputModel;
import com.osl.model.InputdetailModel;
import com.osl.model.OutputModel;
import com.osl.model.OutputdetailModel;
import com.osl.model.StockModel;
import com.osl.service.OutputService;

@Service
public class OutputServiceImpl implements OutputService {
	
	@Autowired
	private OutputMapper outputMapper;
	
	@Autowired
	private StockMapper stockMapper;
	
	@Autowired
	private EntryMapper entryMapper;

	@Override
	public OutputdetailModel findById(String detailId) {
		// TODO Auto-generated method stub
		return outputMapper.findById(detailId);
	}

	@Override
	public List<OutputModel> findOutputAll(OutputModel outputModel) {
		// TODO Auto-generated method stub
		return outputMapper.findOutputAll(outputModel);
	}

	@Override
	public List<OutputdetailModel> findDetailListById(OutputdetailModel outputdetailModel) {
		// TODO Auto-generated method stub
		return outputMapper.findDetailListById(outputdetailModel);
	}

	@Override
	@Transactional
	public int deleteOutputById(String outputId) {
		// TODO Auto-generated method stub
		//逻辑删除本次出库编号对应的出库详情信息
		//flag = outputMapper.deleteOutputDetailByOutputId(OutputId);
		String[] ids = outputId.split(";");
		int ok = 0;
		for(int i=0 ; i < ids.length ; i++) {
			OutputdetailModel outputdetailModel = new OutputdetailModel();
			outputdetailModel.setOutputId(ids[i]);
			List<OutputdetailModel> detailList = outputMapper.findDetailListById(outputdetailModel);
			for(OutputdetailModel model : detailList) {
				deleteOutputDetail(model);
			}
			ok = outputMapper.deleteOutputById(ids[i]);
		}
		return ok;
	}

	@Override
	public int deleteOutputDetailByOutputId(String OutputId) {
		// TODO Auto-generated method stub
		return outputMapper.deleteOutputDetailByOutputId(OutputId);
	}

	/**
	 * 新增库存详情信息
	 * @author zhangzy
	 * 
	 */
	@Override
	public int saveNewOutputDetail(OutputdetailModel model) {
		// TODO Auto-generated method stub
		//保存入库详情表
		int flag = outputMapper.saveNewOutputDetail(model);
		//
		return flag;
	}

	/**
	 * 更新出库详情信息
	 * @author zhangzy
	 * 
	 * 1.对比当前对象跟数据库中对象的差异数量
	 * 
	 * 2.查询当前准备出库的商品的剩余库存是否满足修改后的数量
	 * 
	 * 3.更新出库详情信息
	 * 
	 * 4.更新出库信息
	 */
	@Override
	@Transactional
	public int updateOutputDetail(OutputdetailModel newModel) {
		// TODO Auto-generated method stub
		int ok = 0;
		String inputDetailId = newModel.getDetailId();
		OutputdetailModel oldInputdetailModel = outputMapper.findById(inputDetailId);
		//差异数
		int diff = newModel.getNums() - oldInputdetailModel.getNums();
		//查询库存信息
		int allStockNum = stockMapper.findStockModelAllByGoodsId(oldInputdetailModel.getGoodsId());
		int allOutputNum = outputMapper.findAllOutputNumByGoodsId(oldInputdetailModel.getGoodsId());
		if((allOutputNum+diff) > allStockNum) {
			//需要出库的数量 + 差异  > 现有库存总数
			return 2;
		}
		//更新出库详情
		ok = outputMapper.updateOutputDetail(newModel);
		
		//查询更新出库表信息
		OutputModel outputModel = outputMapper.findOutputById(oldInputdetailModel.getOutputId());
		outputModel.setGoodsNums(outputModel.getGoodsNums() + diff);
		outputModel.setUpdateDate(newModel.getUpdateDate());
		ok = outputMapper.updateOutput(outputModel);
		return ok;
	}

	/**
	 * 删除出库详情信息
	 * @author zhangzy
	 * 
	 */
	@Override
	@Transactional
	public int deleteOutputDetail(OutputdetailModel newModel) {
		return outputMapper.deleteOutputDetailByDetailId(newModel.getDetailId());
	}
	
	@Override
	public OutputModel findOutputById(String OutputId) {
		// TODO Auto-generated method stub
		return outputMapper.findOutputById(OutputId);
	}

	@Override
	@Transactional
	public int deleteOutputDetailByDetailId(String detailId) {
		// TODO Auto-generated method stub
		String[] ids = detailId.split(";");
		int ok = 0;
		for(int i=0 ; i < ids.length ; i++) {
			ok = outputMapper.deleteOutputDetailByDetailId(ids[i]);
		}
		return ok;
	}

	@Override
	@Transactional
	public int cancelOutputById(String outputId) {
		// TODO Auto-generated method stub
		String[] ids = outputId.split(";");
		int ok = 0;
		for(int i=0 ; i < ids.length ; i++) {
			OutputdetailModel outputdetailModel = new OutputdetailModel();
			outputdetailModel.setOutputId(ids[i]);
			List<OutputdetailModel> detailList = outputMapper.findDetailListById(outputdetailModel);
			for(OutputdetailModel model : detailList) {
				cancelOutputDetail(model);
			}
			ok = outputMapper.cancelOutputById(ids[i]);
		}
		return ok;
	}

	@Override
	public int cancelOutputDetailByOutputId(String outputId) {
		// TODO Auto-generated method stub
		return outputMapper.cancelOutputDetailByOutputId(outputId);
	}

	@Override
	@Transactional
	public int cancelOutputDetailByDetailId(String detailId) {
		// TODO Auto-generated method stub
		String[] ids = detailId.split(";");
		int ok = 0;
		for(int i=0 ; i < ids.length ; i++) {
			ok = outputMapper.cancelOutputDetailByDetailId(ids[i]);
		}
		return ok;
	}

	@Override
	public int cancelOutputDetail(OutputdetailModel newModel) {
		// TODO Auto-generated method stub
		return outputMapper.cancelOutputDetailByDetailId(newModel.getDetailId());
	}

	@Override
	public int updateOutputDetailStatus(OutputdetailModel newModel) {
		// TODO Auto-generated method stub
		int ok = outputMapper.updateOutputDetailStatus(newModel);
		return ok;
	}


}
