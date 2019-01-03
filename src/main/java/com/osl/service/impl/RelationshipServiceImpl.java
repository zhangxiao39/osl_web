/**
 * 
 */
package com.osl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osl.mapper.RelationshipMapper;
import com.osl.mapper.entity.RelationshipEntity;
import com.osl.service.RelationshipService;

/**
 * @author zhangxiao
 *
 */

@Service
public class RelationshipServiceImpl implements RelationshipService{
	
	@Autowired
	private RelationshipMapper relationshipMapper;

	/**
	* <p>Title: changeShip</p>  
	* <p>Description: </p>  
	* @param relationshipEntity
	* @return  
	* @see com.osl.service.RelationshipService#changeShip(com.osl.mapper.entity.RelationshipEntity)  
	*/  
	@Override
	public int changeShip(RelationshipEntity relationshipEntity) {
		// TODO Auto-generated method stub
		return relationshipMapper.changeShip(relationshipEntity);
	}

}
