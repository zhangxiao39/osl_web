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
import com.osl.mapper.entity.SellshipEntity;
import com.osl.model.SellplatformModel;
import com.osl.model.SellshipModel;
import com.osl.service.SellplatformService;
import com.osl.service.SellshipService;

@Controller
public class SellshipController extends BaseController<SellshipModel> {

	@Autowired
	private SellplatformService sellplatformService;

	@Autowired
	private SellshipService sellshipService;

	@RequestMapping(value = "/b/goods/sellship")
	public String b_g_selllistManage(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/business/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			List<SellplatformModel> _sellplatform = sellplatformService.find_sellplatform_All(myBusiness_id);
			model.addAttribute("sellplatformData", _sellplatform);
			List<SellshipModel> _sellshipInfo = sellshipService.find_sellshipBusiness_All(myBusiness_id);
			model.addAttribute("sellshipData", _sellshipInfo);
			model.addAttribute("nav_active2", 4);
			return "/c/goods/sellship";
		}
	}

	@RequestMapping(value = "/osl/sellship", method = RequestMethod.POST)
	@ResponseBody
	public String addProduct(Model model, HttpSession session, SellshipEntity _sellship) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			if (_sellship.getId() == 0) {
				int ok = sellshipService.insertSellship(_sellship);
				if (ok > 0) {
					return "ok";
				} else if (ok == -1) {
					return "exist";
				} else {
					return "fail";
				}
			} else {
				return "fail";
			}
		}
	}

	@RequestMapping(value = "/osl/sellship/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteProduct(@PathVariable(required = true) int id) {
		int ok = sellshipService.deleteById(id);
		if (ok > 0) {
			return "ok";
		} else {
			return "fail";
		}
	}

	@RequestMapping(value = "/osl/sellship/{id}", method = RequestMethod.GET)
	@ResponseBody
	public SellshipEntity showInfo(@PathVariable(required = true) int id) {
		SellshipEntity _info = new SellshipEntity();
		_info = sellshipService.findById(id);
		return _info;
	}

	@RequestMapping(value = "/osl/sellship", method = RequestMethod.PUT)
	@ResponseBody
	public String updateSellship(SellshipEntity _sellship) {
		if (_sellship.getId() > 0) {
			int ok = sellshipService.updateSellship(_sellship);
			if (ok > 0) {
				return "ok";
			} else {
				return "fail";
			}
		} else {
			return "fail";
		}
	}

	@Override
	protected String getPageId() {
		// TODO Auto-generated method stub
		return null;
	}

}
