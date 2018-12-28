/**
 * 
 */
package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.osl.mapper.entity.BalancebaseEntity;
import com.osl.model.BalancebaseModel;

/**
 * @author zhangxiao
 *
 */

@Mapper
public interface BalanceBaseMapper {
	
	public List<BalancebaseModel> findBalanceBaseAll(int bid);
	
	public int insert(BalancebaseEntity _balancebaseEntity);

	public int update(BalancebaseEntity _balancebaseEntity);

	public int deleteById(int id);

	public BalancebaseEntity findById(int id);

}
