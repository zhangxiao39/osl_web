package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.osl.mapper.entity.StockEntity;
import com.osl.model.StockModel;;

@Mapper
public interface StockMapper {
	
	//�̼Ҷˣ������̼�id��ȡ����б�
	public List<StockModel> find_stockBusiness_All(int bid);
	
	//�̼Ҷˣ�������ѯ�����Ϣ�б�
	public List<StockModel> find_stockBusiness_by_condition(int bid,StockModel stockModel);
	
	//��Ӫ�̶ˣ�������Ӫ��id��ȡ�����Ϣ�б�
	public List<StockModel> find_adminStock_All(int bid);
	
	//��Ӫ�̶ˣ�������ѯ�����Ϣ�б�
	public List<StockModel> find_adminStock_by_condition(int bid,StockModel stockModel);
	
//	//�̼ң���ȡ������Ӧ��������Ʒsku
//	public List<String> find_businessSKU(int bid);
//	
//	//��Ӫ�̣���ȡ��ֿ���������Ʒ��sku
//	public List<String> find_adminSKU(int bid);
	
	
	
}
