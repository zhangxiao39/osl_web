package com.osl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osl.mapper.ShelvesMapper;
import com.osl.mapper.entity.ShelvesEntity;
import com.osl.model.ShelvesModel;
import com.osl.service.ShelvesService;

@Service
public class ShelvesServiceImpl implements ShelvesService {

	@Autowired
	private ShelvesMapper shelvesMapper;

	@Override
	public List<ShelvesModel> findShelvesAll(int bid) {
		// TODO Auto-generated method stub
		return shelvesMapper.findShelvesAll(bid);
	}

	@Override
	public List<ShelvesEntity> queryShelvesEntityListByDepotId(String depotId) {
		// TODO Auto-generated method stub
		return shelvesMapper.queryShelvesEntityListByDepotId(depotId);
	}

	@Override
	public List<ShelvesModel> queryShelvesGoodsByBid(int bid) {
		return shelvesMapper.queryShelvesGoodsByBid(bid);
	}

	@Override
	public List<ShelvesModel> queryShelvesDetail(ShelvesModel shelvesModel) {
		return shelvesMapper.queryShelvesDetail(shelvesModel);
	}

	@Override
	public List<ShelvesModel> queryShelvesDetailByCondition(ShelvesModel shelvesModel) {
		return shelvesMapper.queryShelvesDetailByCondition(shelvesModel);
	}

	@Override
	public List<ShelvesModel> queryShelvesGoodsCondition(ShelvesModel shelvesModel) {
		return shelvesMapper.queryShelvesGoodsCondition(shelvesModel);
	}

	/**
	 * <p>
	 * Title: insertShelves
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param _shelves
	 * @return
	 * @see com.osl.service.ShelvesService#insertShelves(com.osl.mapper.entity.ShelvesEntity)
	 */
	@Override
	public int insertShelves(ShelvesEntity _shelves) {
		// TODO Auto-generated method stub
		ShelvesEntity _tmpShelves = shelvesMapper.findById(_shelves.getShelvesId());
		if (_tmpShelves == null) {
			return shelvesMapper.insertShelves(_shelves);
		} else {
			return -1;
		}
	}

	/**
	 * <p>
	 * Title: updateShelves
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param _shelves
	 * @return
	 * @see com.osl.service.ShelvesService#updateShelves(com.osl.mapper.entity.ShelvesEntity)
	 */
	@Override
	public int updateShelves(ShelvesEntity _shelves) {
		// TODO Auto-generated method stub
		return shelvesMapper.updateShelves(_shelves);
	}

	/**
	 * <p>
	 * Title: deleteById
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param shelvesId
	 * @return
	 * @see com.osl.service.ShelvesService#deleteById(java.lang.String)
	 */
	@Override
	public int deleteById(String shelvesId) {
		// TODO Auto-generated method stub
		return shelvesMapper.deleteById(shelvesId);
	}

	/**
	 * <p>
	 * Title: findById
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param shelvesId
	 * @return
	 * @see com.osl.service.ShelvesService#findById(java.lang.String)
	 */
	@Override
	public ShelvesEntity findById(String shelvesId) {
		// TODO Auto-generated method stub
		return shelvesMapper.findById(shelvesId);
	}

}
