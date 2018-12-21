package com.osl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osl.common.Util;
import com.osl.common.UtilConst;
import com.osl.mapper.GoodsMapper;
import com.osl.mapper.entity.GoodsEntity;
import com.osl.model.GoodsModel;
import com.osl.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsMapper goodsMapper;

	@Override
	public List<GoodsModel> find_goodsAll(GoodsModel goodsModel) {
		// TODO Auto-generated method stub
		return goodsMapper.find_goodsAll(goodsModel);
	}

	@Override
	public int insertGoods(GoodsEntity _goods) {
		// TODO Auto-generated method stub
		GoodsModel _g = goodsMapper.find_goodsBusiness_sku(_goods.getBusinessId(), _goods.getSku());
		if (_g == null) {
			String _goodsId = Util.generateTableIdByDB(UtilConst.TABLE_KEY_TO_GOODS, _goods.getBusinessId(), 1);
			String tmpId = goodsMapper.getGoodsId(_goods.getBusinessId(), _goodsId.substring(0, 14));
			if (tmpId != null) {
				int _id = Integer.valueOf(tmpId.replace(_goodsId.substring(0, 14), "")) + 1;
				_goodsId = Util.generateTableIdByDB(UtilConst.TABLE_KEY_TO_GOODS, _goods.getBusinessId(), _id);
			}
			_goods.setGoodsId(_goodsId);
			return goodsMapper.insertGoods(_goods);
		} else {
			return -1;
		}
	}

	@Override
	public int deleteById(String goodsId) {
		// TODO Auto-generated method stub
		return goodsMapper.deleteById(goodsId);
	}

	@Override
	public GoodsEntity findById(String goodsId) {
		// TODO Auto-generated method stub
		return goodsMapper.findById(goodsId);
	}

	@Override
	public int updateGoods(GoodsEntity _goods) {
		// TODO Auto-generated method stub
		return goodsMapper.updateGoods(_goods);
	}

	@Override
	public List<GoodsModel> find_goodsBusiness_All(int bid) {
		// TODO Auto-generated method stub
		return goodsMapper.find_goodsBusiness_All(bid);
	}

	@Override
	public GoodsEntity findBySku(String sku) {
		// TODO Auto-generated method stub
		return goodsMapper.findBySku(sku);
	}

	@Override
	public GoodsModel find_goodsBusiness_sku(int bid, String sku) {
		// TODO Auto-generated method stub
		return goodsMapper.find_goodsBusiness_sku(bid, sku);
	}

}
