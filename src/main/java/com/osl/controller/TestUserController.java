package com.osl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.osl.common.web.BaseController;
import com.osl.exception.ApplException;
import com.osl.mapper.entity.TestUserEntity;
import com.osl.model.TestUserModel;
import com.osl.service.TestUserService;

@Controller
public class TestUserController extends BaseController<TestUserModel> {
	
	@Autowired
	private TestUserService service;

	@RequestMapping("/osl/testUser")
	@ResponseBody
	public List<TestUserEntity> testUserFindAll() {
		return service.findUserAll();
		
	}
	
	@RequestMapping("/osl/testUserList")
	public String testUserFindList(Model model, TestUserModel userModel) throws Exception {
//		try {
//			
//			List<TestUserModel> userDataList = service.findUserList();
//			model.addAttribute("testUserList", userDataList);
//			
//		} catch (ApplException e) {
//			userModel.setErrorMsg(e.getMessage());
//		}
		this.ViewUrl = "/test/testUser.html";
		List<TestUserModel> userDataList = service.findUserList();
		model.addAttribute("testUserList", userDataList);
		model.addAttribute("usermodel", userModel);
		return this.ViewUrl;
	}
	
	@Override
	protected String getPageId() {
		// TODO Auto-generated method stub
		return "test_user_page";
	}

}
