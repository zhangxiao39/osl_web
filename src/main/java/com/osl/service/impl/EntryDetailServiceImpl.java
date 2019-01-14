package com.osl.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osl.common.UtilConst;
import com.osl.mapper.EntryMapper;
import com.osl.mapper.EntrydetailMapper;
import com.osl.mapper.entity.EntrydetailEntity;
import com.osl.model.EntryModel;
import com.osl.model.EntrydetailModel;
import com.osl.service.EntryDetailService;

@Service
public class EntryDetailServiceImpl implements EntryDetailService {
	@Autowired
	private EntrydetailMapper entryDetailMapper;
	
	@Autowired
	private EntryMapper entryMapper;

	@Override
	public List<EntrydetailModel> bQueryEntryDetailListByEntryId(String entryId) {
		return entryDetailMapper.bQueryEntryDetailListByEntryId(entryId);
	}

	@Override
	public int bupdateStatusByEntryId(EntrydetailModel entryDetailModel) {
		return entryDetailMapper.bupdateStatusByEntryId(entryDetailModel);
	}

	@Override
	public int bDeleteEntryDetailById(String entryDetailId) {
		return entryDetailMapper.bDeleteEntryDetailById(entryDetailId);
	}

	@Override
	public int bPhysicsDeleteEntryDetailById(String entryDetailId) {
		return entryDetailMapper.bPhysicsDeleteEntryDetailById(entryDetailId);
	}

	@Override
	public List<EntrydetailModel> bQueryEntryDetailListByCondition(EntrydetailModel entryDetailModel) {
		return entryDetailMapper.bQueryEntryDetailListByCondition(entryDetailModel);
	}

	/**
	 * 更新纳品详情货运单号以及申请纳品数量
	 */
	@Override
	@Transactional
	public String bupdateInputNumsAndNumbers(EntrydetailModel entryDetailModel) {
		EntrydetailModel param1 = entryDetailMapper.bQueryEntryDetailListByCondition(entryDetailModel).get(0);	//补全纳品详情信息
		param1.setNums(entryDetailModel.getNums());
		param1.setSendId(entryDetailModel.getSendId());
		param1.setInputDiffNums(entryDetailModel.getInputDiffNums());
		Timestamp updateTime =  new Timestamp(new Date().getTime());
		param1.setUpdateDate(updateTime);
		//判断是否更新纳品数量，只有当status为3时不更新纳品数量
		if(UtilConst.ENTRY_STATUS_OPERATOR_ADMIT!=param1.getStatus())
		{
			if(entryDetailMapper.bupdateInputNumsAndNumbers(param1)>0)
			{
				//更改纳品中的纳品数量信息
				EntryModel param2 = new EntryModel();
				param2.setEntryId(param1.getEntryId());
				param2.setBusinessId(entryDetailModel.getBusinessId());
				param2.setStatus(param1.getStatus());
				param2.setUpdateDate(updateTime);	//更新时间
				param2.setOper(entryDetailModel.getOper());//操作人
				EntryModel entryModel  = entryMapper.bQueryEntryListByCondition(param2).get(0);
				int totalNum = entryModel.getGoodsNums();
				int diffNum = entryDetailModel.getInputDiffNums();
				param2.setGoodsNums(totalNum-diffNum); //更新入库数量
				if(entryMapper.bupdateEntryNums(param2)>0){
					return "ok";
				}
				else {
					return "fail";
				}
			}else {
				return "fail";
			}
		}else {
			//只需要更改纳品详情中的货运单号，不用更改纳品中的信息
			entryDetailModel.setNums(0);  //将纳品数量置为0 ，用作xml中标识，但是不更新
			if(entryDetailMapper.bupdateInputNumsAndNumbers(entryDetailModel)>0)
			{
				return "ok";
			}else {
				return "fail";
			}
		}
	}
	
	/**
	 * @des 纳品状态为：申请中，商家承认完了，追加纳品操作
	 * @author sun-hongyu
	 * @param entryDetailModel,newFlag:新增，在原纳品基础上增加
	 * @return String
	 */
	@Override
	@Transactional
	public String additionalEntryGoods(EntrydetailModel entryDetailModel, String newFlag) {
		//获取当前时间
		Timestamp NowTime =  new Timestamp(new Date().getTime());
		//newFlag:为“yes”时表示新添加一个商品，为“no”表示在原纳品信息上进行添加
		if("yes".equals(newFlag))
		{
			List<EntrydetailEntity> list = new ArrayList<EntrydetailEntity>();
			EntrydetailEntity entryDetailEntity= new EntrydetailEntity();
			//根据传入的detailModel构建detailEntity
			entryDetailEntity.setDetailId(entryDetailModel.getDetailId());
			entryDetailEntity.setEntryId(entryDetailModel.getEntryId());
			entryDetailEntity.setGoodsId(entryDetailModel.getGoodsId());
			entryDetailEntity.setNums(entryDetailModel.getNums());
			entryDetailEntity.setInnerNums(entryDetailModel.getInnerNums());
			entryDetailEntity.setInnerGoodsNums(entryDetailModel.getInnerGoodsNums());
			entryDetailEntity.setPackageSize(entryDetailModel.getPackageSize());
			entryDetailEntity.setValidityTime(entryDetailModel.getValidityTime());
			entryDetailEntity.setNewDate(NowTime);
			entryDetailEntity.setStatus(UtilConst.ENTRY_STATUS_APPLYING);
			entryDetailEntity.setDeleteFlg(0);
			list.add(entryDetailEntity);
			//新建纳品详情，向纳品详情表插入数据
			if(entryDetailMapper.insertEntryDetail(list)>0)
			{
				//更新其所对应的纳品信息，更新纳品表,纳品数量，sku数量
				EntryModel param2 = new EntryModel();
				param2.setEntryId(entryDetailModel.getEntryId());
				param2.setBusinessId(entryDetailModel.getBusinessId());
				EntryModel entryModel  = entryMapper.bQueryEntryListByCondition(param2).get(0);
				int totalNum = entryModel.getGoodsNums();
				int diffNum = entryDetailModel.getNums();
				entryModel.setGoodsNums(totalNum+diffNum); //更新入库数量
				entryModel.setSkuNums(entryModel.getSkuNums()+1);	//更新sku数量
				entryModel.setUpdateDate(NowTime);	//更新时间
				entryModel.setOper(entryDetailModel.getOper());//操作人
				entryModel.setStatus(UtilConst.ENTRY_STATUS_APPLYING);	//状态更新为申请中
				//更新纳品信息以及对应所有的纳品详情状态为->申请中
				EntrydetailModel entryDetailTemp = new EntrydetailModel();
				entryDetailTemp.setEntryId(entryModel.getEntryId());
				entryDetailTemp.setStatus(UtilConst.ENTRY_STATUS_APPLYING);
				if(entryMapper.bupdateEntryNums(entryModel)>0&&entryDetailMapper.bupdateStatusByEntryId(entryDetailTemp)>0){
					return "ok";
				}
				else {
					return "fail";
				}
			}
			
		}
		else
		{
			//更新纳品详情信息，更新纳品详情表数据
			EntrydetailModel paramTemp = entryDetailMapper.bQueryEntryDetailListByCondition(entryDetailModel).get(0);	//补全纳品详情信息
			paramTemp.setNums(entryDetailModel.getNums());
			paramTemp.setInnerGoodsNums(entryDetailModel.getInnerGoodsNums());
			paramTemp.setInnerNums(entryDetailModel.getInnerNums());
			paramTemp.setPackageSize(entryDetailModel.getPackageSize());
			paramTemp.setValidityTime(entryDetailModel.getValidityTime());
			paramTemp.setOper(entryDetailModel.getOper());
			paramTemp.setUpdateDate(NowTime);
			paramTemp.setStatus(UtilConst.ENTRY_STATUS_APPLYING);
			if(entryDetailMapper.bupdateInputNumsAndNumbers(paramTemp)>0)
			{
				//更新其所对应的纳品信息，更新纳品表,纳品数量，sku数量
				EntryModel param2 = new EntryModel();
				param2.setEntryId(entryDetailModel.getEntryId());
				param2.setBusinessId(entryDetailModel.getBusinessId());
				EntryModel entryModel  = entryMapper.bQueryEntryListByCondition(param2).get(0);
				int totalNum = entryModel.getGoodsNums();
				int diffNum = entryDetailModel.getInputDiffNums();
				entryModel.setGoodsNums(totalNum-diffNum); //更新入库数量
				entryModel.setUpdateDate(NowTime);	//更新时间
				entryModel.setOper(entryDetailModel.getOper());//操作人
				entryModel.setStatus(UtilConst.ENTRY_STATUS_APPLYING);	//状态更新为申请中
				EntrydetailModel entryDetailTemp = new EntrydetailModel();
				entryDetailTemp.setEntryId(entryModel.getEntryId());
				entryDetailTemp.setStatus(UtilConst.ENTRY_STATUS_APPLYING);
				if(entryMapper.bupdateEntryNums(entryModel)>0&&entryDetailMapper.bupdateStatusByEntryId(entryDetailTemp)>0){
					return "ok";
				}
				else {
					return "fail";
				}
			}
		}
		return "fail";
	}
	
	
}
