/**
 * 
 */
package com.osl.service;

import java.util.List;

import com.osl.mapper.entity.RelationshipEntity;

/**
 * @author zhangxiao
 *
 */
public interface RelationshipService {
	
	public int changeShip(RelationshipEntity relationshipEntity);

	public List<RelationshipEntity> queryShipByBusinessId(Long businessId);
}
