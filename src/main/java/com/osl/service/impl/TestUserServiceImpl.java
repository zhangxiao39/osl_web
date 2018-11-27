package com.osl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osl.common.UtilConv;
import com.osl.exception.ApplException;
import com.osl.mapper.TestUserMapper;
import com.osl.mapper.entity.TestUserEntity;
import com.osl.model.TestUserModel;
import com.osl.service.TestUserService;

@Service
public class TestUserServiceImpl implements TestUserService {
	
	@Autowired
	private TestUserMapper testUserMapper;

	@Override
	public List<TestUserEntity> findUserAll() {
		// TODO Auto-generated method stub
		return testUserMapper.findAll();
	}

	@Override
	public List<TestUserModel> findUserList() throws Exception {
		List<TestUserModel> testUserList = null;
		throw new Exception("testError");
		//throw new ApplException("testError");
//		List<TestUserEntity> userDataList = testUserMapper.findAll();
//		if (userDataList != null && userDataList.size() > 0) {
//			testUserList = new ArrayList<TestUserModel>();
//			for (int i = 0; i < userDataList.size(); i++) {
//				TestUserEntity entity = userDataList.get(i);
//				TestUserModel item = new TestUserModel();
//				item.setId(UtilConv.objToStr(entity.getId()));
//				item.setUsername(entity.getUsername());
//				item.setSex(entity.getSex());
//				item.setBirthday(UtilConv.date2Str(entity.getBirthday(), UtilConv.DATE_PAT_KANJI));
//				item.setAddress(entity.getAddress());
//				testUserList.add(item);
//			}
//		}
//		return testUserList;
	}

}
