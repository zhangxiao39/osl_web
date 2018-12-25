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
import com.osl.mapper.InputMapper;
import com.osl.mapper.StockMapper;
import com.osl.mapper.entity.EntryEntity;
import com.osl.mapper.entity.EntrydetailEntity;
import com.osl.mapper.entity.StockEntity;
import com.osl.model.InputModel;
import com.osl.model.InputdetailModel;
import com.osl.model.StockModel;
import com.osl.service.InputService;

@Service
public class InputServiceImpl implements InputService {
	
	@Autowired
	private InputMapper inputMapper;
	
	@Autowired
	private StockMapper stockMapper;
	
	@Autowired
	private EntryMapper entryMapper;

	@Override
	public InputdetailModel findById(String detailId) {
		// TODO Auto-generated method stub
		return inputMapper.findById(detailId);
	}

	@Override
	public List<InputModel> findInputAll(InputModel iputModel) {
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
	public int deleteInputById(String inputId) {
		// TODO Auto-generated method stub
		//逻辑删除本次入库编号对应的入库详情信息
		//flag = inputMapper.deleteInputDetailByInputId(inputId);
		List<InputdetailModel> detailList = inputMapper.findDetailListById(inputId);
		for(InputdetailModel model : detailList) {
			deleteInputDetail(model);
		}
		int flag = inputMapper.deleteInputById(inputId);
		return flag;
	}

	@Override
	public int deleteInputDetailByInputId(String inputId) {
		// TODO Auto-generated method stub
		return inputMapper.deleteInputDetailByInputId(inputId);
	}

	/**
	 * 新增库存详情信息
	 * @author zhangzy
	 * 
	 * 处理逻辑：
	 * 1.入库操作后，详情状态为“入库中”
	 *   
	 * 2.根据入库详情对象中的shipId，查询同一纳品id 对应入库表中是否有已有记录，如果有则 将此入库记录中的 入库id 取出作为 此次入库详情的入库id
	 * 
	 * 3.确认好 入库总表 的id和 状态后 ，新增/修改 入库总表的信息（sku数量、商品总数、更新时间、状态）
	 * 
	 * 4.更新入库详情对象中shipId对应的纳品详情对象（入库数量、入库差异数量、更新时间、状态）
	 * 
	 * 5.根据此纳品详情中的纳品id，反查对应的纳品一览表中的状态，并且对比状态，更新纳品一览表（状态、更新时间、入库商品sku数量、入库商品数量）
	 * 
	 * 6.新增入库详情对象到数据库
	 */
	@Override
	public int saveNewInputDetail(InputdetailModel model) {
		// TODO Auto-generated method stub
		//保存入库详情表
		int flag = inputMapper.saveNewInputDetail(model);
		//
		return flag;
	}

	/**
	 * 更新入库详情信息
	 * @author zhangzy
	 * 
	 * 1.查看本次修改的入库详情中的入库数量 与 数据库中的入库数量的 差异数
	 * 
	 * 2.根据入库详情id，查询出 对应的库存信息
	 * 
	 * 3.在已有库存信息中 更新 （数量，仓库，货架，更新时间）
	 * 
	 * 4.更新对用纳品详细中的信息（入库数量、入库差异数量、更新时间、状态）
	 * 
	 * 5.更新纳品一览表（状态、更新时间、入库商品数量）
	 * 
	 * 6.更新入库详情信息（数量，仓库，货架，状态变更为：入库中，更新时间）
	 * 
	 * 7.更新入库信息（数量，状态变更为：入库中，更新时间）
	 */
	@Override
	@Transactional
	public int updateInputDetail(InputdetailModel newModel) {
		// TODO Auto-generated method stub
		int ok = 0;
		//更新时间戳
		Timestamp tmpTimestamp = new Timestamp(new Date().getTime());
		String inputDetailId = newModel.getDetailId();
		InputdetailModel oldInputdetailModel = inputMapper.findById(inputDetailId);
		//差异数
		int diff = newModel.getNums() - oldInputdetailModel.getNums();
		//查询库存信息
		StockModel stockModel = new StockModel();
		stockModel.setInputDetailId(inputDetailId);
		stockModel.setWarehouseId(newModel.getWarehouseId());
		stockModel.setGoodsType(newModel.getGoodsType());
		List<StockModel> stockModelList = stockMapper.find_stock_detail(stockModel);
		stockModel = stockModelList.get(0);
		//库存信息赋值
		stockModel.setDepotId(newModel.getDepotId());
		stockModel.setShelvesId(newModel.getShelvesId());
		stockModel.setNums(stockModel.getNums() + diff);
		stockModel.setGoodsType(newModel.getGoodsType());
		stockModel.setValidityTime(newModel.getValidityTime());
		//更新库存
		StockEntity stockEntity = new StockEntity();
		try {
			BeanUtils.copyProperties(stockEntity, stockModel);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ok = stockMapper.update_stock(stockEntity);
		
		//查询纳品详情信息并更新
		EntrydetailEntity entrydetailEntity = entryMapper.queryEntrydetailEntityByDetailId(newModel.getShipId());
		entrydetailEntity.setInputNums(entrydetailEntity.getInputNums() + diff);
		entrydetailEntity.setInputDiff(entrydetailEntity.getInputDiff() - diff);
		entrydetailEntity.setUpdateDate(tmpTimestamp);
		ok = entryMapper.updateDetailEntryByInputDetail(entrydetailEntity);
		
		//查询纳品一览信息并更新
		EntryEntity entryEntity = entryMapper.queryEntryEntityByEntryId(entrydetailEntity.getEntryId());
		entryEntity.setInputGoodsNums(entryEntity.getInputGoodsNums() + diff);
		entryEntity.setUpdateDate(tmpTimestamp);
		ok = entryMapper.updateEntryByInputDetail(entryEntity);
		
		//更新入库详情
		newModel.setUpdateDate(tmpTimestamp);
		ok = inputMapper.updateInputDetail(newModel);
		
		//查询更新入库表信息
		InputModel inputModel = inputMapper.findInputById(newModel.getInputId());
		inputModel.setGoodsNums(inputModel.getGoodsNums() + diff);
		inputModel.setUpdateDate(tmpTimestamp);
		ok = inputMapper.updateInput(inputModel);
		return ok;
	}

	/**
	 * 删除入库详情信息
	 * @author zhangzy
	 * 
	 * 1.查看本次修改的入库详情中的入库数量 与 数据库中的入库数量的 差异数
	 * 
	 * 2.根据入库详情id，查询出 对应的库存信息
	 * 
	 * 3.在已有库存信息中 更新 （数量，仓库，货架，更新时间）
	 * 
	 * 4.更新对用纳品详细中的信息（入库数量、入库差异数量、更新时间、状态）
	 * 
	 * 5.更新纳品一览表（状态、更新时间、入库商品数量）
	 * 
	 * 6.更新入库详情信息（数量，仓库，货架，状态变更为：入库中，更新时间）
	 * 
	 * 7.更新入库信息（数量，状态变更为：入库中，更新时间）
	 */
	@Override
	@Transactional
	public int deleteInputDetail(InputdetailModel newModel) {
		// TODO Auto-generated method stub
		int ok = 0;
		//更新时间戳
		Timestamp tmpTimestamp = new Timestamp(new Date().getTime());
		String inputDetailId = newModel.getDetailId();
		InputdetailModel oldInputdetailModel = inputMapper.findById(inputDetailId);
		//差异数
		int diff = 0 - oldInputdetailModel.getNums();
		//查询库存信息
		StockModel stockModel = new StockModel();
		stockModel.setInputDetailId(inputDetailId);
		stockModel.setWarehouseId(newModel.getWarehouseId());
		stockModel.setGoodsType(newModel.getGoodsType());
		List<StockModel> stockModelList = stockMapper.find_stock_detail(stockModel);
		stockModel = stockModelList.get(0);
		//库存信息赋值
		stockModel.setDeleteFlag(1);
		//删除库存
		ok = stockMapper.delete_stock_by_id(stockModel.getManageId());
		
		//查询纳品详情信息并更新
		EntrydetailEntity entrydetailEntity = entryMapper.queryEntrydetailEntityByDetailId(newModel.getShipId());
		entrydetailEntity.setInputNums(entrydetailEntity.getInputNums() + diff);
		entrydetailEntity.setInputDiff(entrydetailEntity.getInputDiff() - diff);
		entrydetailEntity.setUpdateDate(tmpTimestamp);
		entrydetailEntity.setDeleteFlg(1);
		ok = entryMapper.updateDetailEntryByInputDetail(entrydetailEntity);
		
		//查询纳品一览信息并更新
		EntryEntity entryEntity = entryMapper.queryEntryEntityByEntryId(entrydetailEntity.getEntryId());
		entryEntity.setInputGoodsNums(entryEntity.getInputGoodsNums() + diff);
		entryEntity.setInputSkuNums((entryEntity.getInputSkuNums() - 1) < 0 ? 0 : (entryEntity.getInputSkuNums() - 1));
		entryEntity.setUpdateDate(tmpTimestamp);
		ok = entryMapper.updateEntryByInputDetail(entryEntity);
		
		//删除入库详情
		ok = inputMapper.deleteInputDetailByDetailId(newModel.getDetailId());
		
		//查询更新入库表信息
		InputModel inputModel = inputMapper.findInputById(newModel.getInputId());
		inputModel.setGoodsNums(inputModel.getGoodsNums() + diff);
		inputModel.setSkuNums((inputModel.getSkuNums() - 1) < 0 ? 0 : (inputModel.getSkuNums() - 1));
		inputModel.setUpdateDate(tmpTimestamp);
		ok = inputMapper.updateInput(inputModel);
		return ok;
	}
	
	@Override
	public InputModel findInputById(String inputId) {
		// TODO Auto-generated method stub
		return inputMapper.findInputById(inputId);
	}

	@Override
	public int deleteInputDetailByDetailId(String detailId) {
		// TODO Auto-generated method stub
		return inputMapper.deleteInputDetailByDetailId(detailId);
	}


}
