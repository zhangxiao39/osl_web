/**
 * 
 */
package com.osl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osl.mapper.BalanceBaseMapper;
import com.osl.mapper.entity.BalancebaseEntity;
import com.osl.model.BalancebaseModel;
import com.osl.service.BalanceBaseService;

/**
 * @author zhangxiao
 *
 */

@Service
public class BalanceBaseServiceImpl implements BalanceBaseService{
	
	@Autowired
	private BalanceBaseMapper balancebaseMapper;

	/**
	* <p>Title: findBalanceBaseAll</p>  
	* <p>Description: </p>  
	* @param bid
	* @return  
	* @see com.osl.service.BalanceBaseService#findBalanceBaseAll(int)  
	*/  
	@Override
	public List<BalancebaseModel> findBalanceBaseAll(int bid) {
		// TODO Auto-generated method stub
		return balancebaseMapper.findBalanceBaseAll(bid);
	}

	/**
	* <p>Title: insert</p>  
	* <p>Description: </p>  
	* @param _balancebaseEntity
	* @return  
	* @see com.osl.service.BalanceBaseService#insert(com.osl.mapper.entity.BalancebaseEntity)  
	*/  
	@Override
	public int insert(BalancebaseEntity _balancebaseEntity) {
		// TODO Auto-generated method stub
		return balancebaseMapper.insert(_balancebaseEntity);
	}

	/**
	* <p>Title: update</p>  
	* <p>Description: </p>  
	* @param _balancebaseEntity
	* @return  
	* @see com.osl.service.BalanceBaseService#update(com.osl.mapper.entity.BalancebaseEntity)  
	*/  
	@Override
	public int update(BalancebaseEntity _balancebaseEntity) {
		// TODO Auto-generated method stub
		return balancebaseMapper.update(_balancebaseEntity);
	}

	/**
	* <p>Title: deleteById</p>  
	* <p>Description: </p>  
	* @param id
	* @return  
	* @see com.osl.service.BalanceBaseService#deleteById(int)  
	*/  
	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return balancebaseMapper.deleteById(id);
	}

	/**
	* <p>Title: findById</p>  
	* <p>Description: </p>  
	* @param id
	* @return  
	* @see com.osl.service.BalanceBaseService#findById(int)  
	*/  
	@Override
	public BalancebaseEntity findById(int id) {
		// TODO Auto-generated method stub
		return balancebaseMapper.findById(id);
	}

}
