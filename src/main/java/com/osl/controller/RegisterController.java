/**
 * 
 */
package com.osl.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.osl.common.web.BaseController;
import com.osl.mapper.entity.BusinessEntity;
import com.osl.model.UserModel;
import com.osl.service.BusinessService;

/**
 * @author zhangxiao
 *
 */
@Controller
public class RegisterController extends BaseController<UserModel>{
	
	@Autowired
	private BusinessService business_service;
	
	@GetMapping("/register")
	public String register(Model model) {
		return "/register";
	}
	
	@RequestMapping(value = "/osl/bussinessregister", method = RequestMethod.POST)
	@ResponseBody
	public String addBusiness(BusinessEntity _business) {
		_business.setGradeId(1);
		int ok = business_service.insert(_business);
		if (ok > 0) {
			return "ok";
		} else if (ok == -1) {
			return "exist";
		} else {
			return "fail";
		}
	}
	

	/**
	* <p>Title: getPageId</p>  
	* <p>Description: </p>  
	* @return  
	* @see com.osl.common.web.BaseController#getPageId()  
	*/  
	@Override
	protected String getPageId() {
		// TODO Auto-generated method stub
		return null;
	}

}
