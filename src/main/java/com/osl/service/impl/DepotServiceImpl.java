package com.osl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osl.mapper.DepotMapper;
import com.osl.mapper.entity.DepotEntity;
import com.osl.model.DepotModel;
import com.osl.service.DepotService;

@Service
public class DepotServiceImpl implements DepotService {

	@Autowired
	private DepotMapper depotMapper;

	@Override
	public List<DepotModel> findDepotAll(int bid) {
		// TODO Auto-generated method stub
		return depotMapper.findDepotAll(bid);
	}

	/**
	 * <p>
	 * Title: insertGoods
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param _depot
	 * @return
	 * @see com.osl.service.DepotService#insertGoods(com.osl.mapper.entity.DepotEntity)
	 */
	@Override
	public int insertDepot(DepotEntity _depot) {
		// TODO Auto-generated method stub
		DepotEntity _tmpDepot = depotMapper.findById(_depot.getDepotId());
		if (_tmpDepot == null) {
			return depotMapper.insertDepot(_depot);
		} else {
			return -1;
		}
	}

	/**
	 * <p>
	 * Title: updateGoods
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param _depot
	 * @return
	 * @see com.osl.service.DepotService#updateGoods(com.osl.mapper.entity.DepotEntity)
	 */
	@Override
	public int updateDepot(DepotEntity _depot) {
		// TODO Auto-generated method stub
		return depotMapper.updateDepot(_depot);
	}

	/**
	 * <p>
	 * Title: deleteById
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param depotId
	 * @return
	 * @see com.osl.service.DepotService#deleteById(java.lang.String)
	 */
	@Override
	public int deleteById(String depotId) {
		// TODO Auto-generated method stub
		return depotMapper.deleteById(depotId);
	}

	/**
	 * <p>
	 * Title: findById
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param depotId
	 * @return
	 * @see com.osl.service.DepotService#findById(java.lang.String)
	 */
	@Override
	public DepotEntity findById(String depotId) {
		// TODO Auto-generated method stub
		return depotMapper.findById(depotId);
	}

}
