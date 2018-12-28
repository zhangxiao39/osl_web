/**
 * 
 */
package com.osl.service;

import java.util.List;

import com.osl.mapper.entity.BalancebaseEntity;
import com.osl.model.BalancebaseModel;

/**
 * @author zhangxiao
 *
 */
public interface BalanceBaseService {

	public List<BalancebaseModel> findBalanceBaseAll(int bid);

	public int insert(BalancebaseEntity _balancebaseEntity);

	public int update(BalancebaseEntity _balancebaseEntity);

	public int deleteById(int id);

	public BalancebaseEntity findById(int id);

}
