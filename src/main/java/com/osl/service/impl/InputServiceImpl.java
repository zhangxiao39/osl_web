package com.osl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public InputdetailEntity findById(int id) {
		// TODO Auto-generated method stub
		return inputMapper.findById(id);
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
	public int deleteInputById(int id) {
		// TODO Auto-generated method stub
		return inputMapper.deleteInputById(id);
	}

	@Override
	public int deleteInputDetailByInputId(int id) {
		// TODO Auto-generated method stub
		return inputMapper.deleteInputDetailByInputId(id);
	}


}
