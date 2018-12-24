package com.osl.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.osl.common.web.BaseController;
import com.osl.mapper.entity.BusinessEntity;
import com.osl.mapper.entity.BusinessGradeEntity;
import com.osl.model.BusinessGradeModel;
import com.osl.model.BusinessModel;
import com.osl.model.Users;
import com.osl.service.BusinessGradeService;
import com.osl.service.BusinessService;

@Controller
public class BusinessController extends BaseController<BusinessModel> {

	@Autowired
	private BusinessService business_service;
	@Autowired
	private BusinessGradeService businessGradeService;

	@RequestMapping(value = "/a/business/list")
	public String b_listManage(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			List<BusinessModel> _businessInfo = business_service.findBusinessAll(myBusiness_id);
			model.addAttribute("item", _businessInfo);
			List<BusinessGradeModel> _businessGrade = businessGradeService.findAll(myBusiness_id);
			model.addAttribute("item_grade", _businessGrade);
			model.addAttribute("nav_active5", 2);
			return "/w/business/businesslist";
		}
	}
	
	@RequestMapping(value = "/osl/bussiness", method = RequestMethod.POST)
	@ResponseBody
	public String addBusiness(Model model, HttpSession session, BusinessEntity _business) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			int ok = business_service.insert(_business);
			if (ok > 0) {
				return "ok";
			} else if (ok == -1) {
				return "exist";
			} else {
				return "fail";
			}
		}

	}
	
	@RequestMapping(value = "/osl/bussiness/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteBusiness(@PathVariable(required = true) int id) {
		int ok = business_service.delete(id);
		if (ok > 0) {
			return "ok";
		} else {
			return "fail";
		}
	}

	@RequestMapping(value = "/osl/bussiness/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BusinessModel showInfo(@PathVariable(required = true) int id) {
		BusinessModel _info = new BusinessModel();
		_info = business_service.findById(id);
		return _info;
	}

	@RequestMapping(value = "/osl/bussiness", method = RequestMethod.PUT)
	@ResponseBody
	public String updateBusiness(BusinessEntity _info) {
		if (_info.getId()>0) {
			int ok = business_service.update(_info);
			if (ok > 0) {
				return "ok";
			} else {
				return "fail";
			}
		} else {
			return "fail";
		}
	}

	/*
	 * 
	 * @Des:��ȡ�к�����ϵ���̼�
	 */
	@RequestMapping(value = "/a/businessList", method = RequestMethod.POST)
	@ResponseBody
	public List<BusinessModel> getRelationBusiness(HttpSession session) {
		this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
		List<BusinessModel> _businessInfo = business_service.findBusinessAll(myBusiness_id);
		return _businessInfo;
	}

	@Override
	protected String getPageId() {
		// TODO Auto-generated method stub
		return null;
	}

}
