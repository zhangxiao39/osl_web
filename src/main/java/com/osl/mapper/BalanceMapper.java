package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.osl.model.BalanceModel;
import com.osl.model.BalancebaseModel;

@Mapper
public interface BalanceMapper {
	public List<BalancebaseModel> findBalanceAll(int bid);
}
