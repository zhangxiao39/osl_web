package com.osl.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.osl.common.web.BaseController;
import com.osl.model.BusinessModel;
import com.osl.service.BusinessService;

@Controller
public class BusinessController extends BaseController<BusinessModel>{
	
	@Autowired
	private BusinessService business_service;
	
	@RequestMapping(value = "/a/business/grade")
	public String b_gradeManage(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			model.addAttribute("nav_active5", 1);
			return "/w/business/grade";
		}
	}
	
	@RequestMapping(value = "/a/business/list")
	public String b_listManage(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			List<BusinessModel> _businessInfo = business_service.findBusinessAll(myBusiness_id);
			model.addAttribute("item", _businessInfo);
			model.addAttribute("nav_active5", 2);
			return "/w/business/businesslist";
		}
	}

	@Override
	protected String getPageId() {
		// TODO Auto-generated method stub
		return null;
	}

}
