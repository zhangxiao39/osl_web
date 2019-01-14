/**
 * 
 */
package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.osl.mapper.entity.RelationshipEntity;

/**
 * 
 * @author zhangxiao
 *
 */

@Mapper
public interface RelationshipMapper {
	
	public int insert(RelationshipEntity _relationship);
	
	public int update(RelationshipEntity _relationship);
	
	public int changeShip(RelationshipEntity _relationship);
	
	public List<RelationshipEntity> queryShipByBusinessId(Long businessId);

}
