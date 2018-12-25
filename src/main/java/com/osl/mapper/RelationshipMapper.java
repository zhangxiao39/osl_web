/**
 * 
 */
package com.osl.mapper;

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

}
