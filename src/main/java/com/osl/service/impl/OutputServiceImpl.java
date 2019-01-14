package com.osl.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osl.common.UtilConst;
import com.osl.mapper.OutputMapper;
import com.osl.mapper.StockMapper;
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
	@Transactional
	public int saveNewOutputDetail(List<OutputdetailModel> modelList , OutputModel fModel) {
		// TODO Auto-generated method stub
		int ok = 0;
		ok = outputMapper.saveNewOutput(fModel);
		//保存出库详情表
		ok = outputMapper.saveNewOutputDetail(modelList);
		return ok;
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
			outputdetailModel.setStatus(-1);
			outputdetailModel.setSellplatformId("-1");
			List<OutputdetailModel> detailList = outputMapper.findDetailListById(outputdetailModel);
			for(OutputdetailModel model : detailList) {
				model.setStatus(UtilConst.OUTPUT_STATUS_CANCLE);
				cancelOutputDetail(model);
			}
			ok = outputMapper.cancelOutputById(ids[i] , UtilConst.OUTPUT_STATUS_CANCLE);
		}
		return ok;
	}

	@Override
	public int cancelOutputDetailByOutputId(String outputId) {
		// TODO Auto-generated method stub
		return outputMapper.cancelOutputDetailByOutputId(outputId , UtilConst.OUTPUT_STATUS_CANCLE);
	}

	@Override
	@Transactional
	public int cancelOutputDetailByDetailId(String detailId) {
		// TODO Auto-generated method stub
		String[] ids = detailId.split(";");
		int ok = 0;
		for(int i=0 ; i < ids.length ; i++) {
			ok = outputMapper.cancelOutputDetailByDetailId(ids[i] , UtilConst.OUTPUT_STATUS_CANCLE);
		}
		return ok;
	}

	@Override
	public int cancelOutputDetail(OutputdetailModel newModel) {
		// TODO Auto-generated method stub
		return outputMapper.cancelOutputDetailByDetailId(newModel.getDetailId() , UtilConst.OUTPUT_STATUS_CANCLE);
	}

	@Override
	@Transactional
	public int updateOutputDetailStatus(OutputdetailModel newModel) {
		int ok = 0;
		// TODO Auto-generated method stub
		//出库完了
		if(UtilConst.OUTPUT_STATUS_FINISH == newModel.getStatus()) {
			//出库规则：有有效日期的，日期在前面的先出；没有有效日期的部分，按照仓库区分，多的先出
			int goodsNum = newModel.getNums();
			StockModel stockModel = new StockModel();
			stockModel.setGoodsType(0);
			stockModel.setGoodsId(newModel.getGoodsId());
			List<StockModel> stockModelList = stockMapper.find_stock_detail_by_output(stockModel);
			for(StockModel tmpStockModel : stockModelList) {
				if(goodsNum > tmpStockModel.getNums()) {
					goodsNum = goodsNum - tmpStockModel.getNums();
					tmpStockModel.setNums(0);
					stockMapper.update_stock_by_id(tmpStockModel.getManageId() , tmpStockModel.getNums());
				}else {
					tmpStockModel.setNums(tmpStockModel.getNums() - goodsNum);
					stockMapper.update_stock_by_id(tmpStockModel.getManageId() , tmpStockModel.getNums());
					newModel.setInnerNums(tmpStockModel.getInnerNums());
					newModel.setInnerGoodsNums(tmpStockModel.getInnerGoodsNums());
					newModel.setStatus(UtilConst.OUTPUT_STATUS_FINISH);
					ok = outputMapper.updateOutputDetailStatus(newModel);
					break;
				}
			}
			newModel = outputMapper.findById(newModel.getDetailId());
			//获取当前出库详情中拥有多少种状态
			List<OutputdetailModel> detailList = outputMapper.countDetailStatus(newModel);
			//状态标记
			boolean flag = false;
			for(OutputdetailModel tmp : detailList) {
				if(UtilConst.OUTPUT_STATUS_FINISH != tmp.getStatus()) {
					flag = true;
					break;
				}
			}
			//如果返回数据中只要有一条数据状态 非出库完成，则更新出库一览表中的状态为 出库中
			//否则更新出库一览状态为 出库完成
			if(flag) {
				OutputModel outputModel = outputMapper.findOutputById(newModel.getOutputId());
				outputModel.setStatus(UtilConst.OUTPUT_STATUS_ING);
				outputModel.setUpdateDate(newModel.getUpdateDate());
				ok = outputMapper.updateOutput(outputModel);
			}else {
				OutputModel outputModel = outputMapper.findOutputById(newModel.getOutputId());
				outputModel.setStatus(UtilConst.OUTPUT_STATUS_FINISH);
				outputModel.setUpdateDate(newModel.getUpdateDate());
				ok = outputMapper.updateOutput(outputModel);
			}
		}
		//出库中
		else if(UtilConst.OUTPUT_STATUS_ING == newModel.getStatus()) {
			newModel.setStatus(UtilConst.OUTPUT_STATUS_ING);
			ok = outputMapper.updateOutputDetailStatus(newModel);
			
			//查询更新出库表信息
			newModel = outputMapper.findById(newModel.getDetailId());
			OutputModel outputModel = outputMapper.findOutputById(newModel.getOutputId());
			outputModel.setStatus(UtilConst.OUTPUT_STATUS_ING);
			outputModel.setUpdateDate(newModel.getUpdateDate());
			ok = outputMapper.updateOutput(outputModel);
		}
		return ok;
	}

}
