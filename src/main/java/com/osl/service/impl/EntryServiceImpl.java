package com.osl.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.osl.common.Util;
import com.osl.exception.ApplException;
import com.osl.mapper.EntryMapper;
import com.osl.mapper.entity.EntryEntity;
import com.osl.model.EntryModel;
import com.osl.service.EntryService;

@Service
public class EntryServiceImpl implements EntryService {
	@Autowired
	private EntryMapper entryMapper;
	
	/* 
	 * 纳品申请文件上传
	 * @see com.osl.service.EntryService#entryUpload(com.osl.model.EntryModel)
	 */
	@Override
	public boolean entryUpload(EntryModel userModel) {
		// TODO Auto-generated method stub
		MultipartFile applyFile = userModel.getApplyFile();
		if (applyFile == null) {
			throw new ApplException("请选择上传文件");
		}
		
		EntryEntity entity = new EntryEntity();
		entity.setEntryId("111");
		entity.setOperTime(new Date());
		entity.setCompleteTime(new Date());
		entity.setSkuNums(222);
		entity.setGoodsNums(32);
		entity.setInputSkuNums(33);
		entity.setInputGoodsNums(5454);
		entity.setStatus(45);
		entity.setBusinessId(10);
		entity.setWarehouseId(11);
		entity.setOper("test admin");
		entity.setNewDate(Util.getCurrentTimestamp());
		entryMapper.insertEntry(entity);
		
		return false;
	}

}
