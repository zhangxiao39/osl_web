/**
 * 
 */
package com.osl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osl.mapper.UserMapper;
import com.osl.mapper.entity.UserEntity;
import com.osl.model.UserModel;
import com.osl.service.UserService;

/**
 * @author zhangxiao
 *
 */

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * <p>
	 * Title: findAll
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 * @see com.osl.service.UserService#findAll()
	 */
	@Override
	public List<UserEntity> findAll() {
		// TODO Auto-generated method stub
		return userMapper.findAll();
	}

	/**
	 * <p>
	 * Title: insert
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param userEntity
	 * @return
	 * @see com.osl.service.UserService#insert(com.osl.mapper.entity.UserEntity)
	 */
	@Override
	public int insert(UserEntity userEntity) {
		// TODO Auto-generated method stub
		UserEntity _user = userMapper.findByUserId(userEntity.getUserId());
		if (_user == null) {
			return userMapper.insert(userEntity);
		} else {
			return -1;
		}
	}

	/**
	 * <p>
	 * Title: login
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param user_id
	 * @param password
	 * @return
	 * @see com.osl.service.UserService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public UserModel login(String userId, String password) {
		// TODO Auto-generated method stub
		return userMapper.login(userId, password);
	}

	/**
	 * <p>
	 * Title: getAllUsers
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param bid
	 * @return
	 * @see com.osl.service.UserService#getAllUsers(int)
	 */
	@Override
	public List<UserModel> getAllUsers(int bid) {
		// TODO Auto-generated method stub
		return userMapper.getAllUsers(bid);
	}

	/**
	* <p>Title: stopUser</p>  
	* <p>Description: </p>  
	* @param id
	* @return  
	* @see com.osl.service.UserService#stopUser(int)  
	*/  
	@Override
	public int stopUser(int id) {
		// TODO Auto-generated method stub
		return userMapper.stopUser(id);
	}

	/**
	* <p>Title: recovery</p>  
	* <p>Description: </p>  
	* @param id
	* @return  
	* @see com.osl.service.UserService#recovery(int)  
	*/  
	@Override
	public int recoveryUser(int id) {
		// TODO Auto-generated method stub
		return userMapper.recoveryUser(id);
	}

	/**
	* <p>Title: deleteById</p>  
	* <p>Description: </p>  
	* @param id
	* @return  
	* @see com.osl.service.UserService#deleteById(int)  
	*/  
	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return userMapper.deleteById(id);
	}

}
