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
import com.osl.mapper.entity.SellplatformEntity;
import com.osl.model.SellplatformModel;
import com.osl.service.SellplatformService;

@Controller
public class SellplatformController extends BaseController<SellplatformModel> {
	
	@Autowired
	private SellplatformService service;
	
	@RequestMapping(value = "/b/sys/sellplatform")
	public String b_sellplatformManage(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			List<SellplatformModel> _sellplatformList = service.find_sellplatform_All(myBusiness_id);
			model.addAttribute("item", _sellplatformList);
			model.addAttribute("nav_active6", 5);
			return "/c/sys/sellplatform";
		}
	}
	
	@RequestMapping(value = "/osl/sellplatform", method = RequestMethod.POST)
	@ResponseBody
	public String addSellplatform(Model model, HttpSession session, SellplatformEntity _sellplatform) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			if (_sellplatform.getId() == 0) {
				int ok = service.insertSellplatform(_sellplatform);
				if (ok > 0) {
					return "ok";
				} else {
					return "fail";
				}
			} else {
				return "fail";
			}
		}
	}
	
	@RequestMapping(value = "/osl/sellplatform/{id}", method = RequestMethod.GET)
	@ResponseBody
	public SellplatformEntity showInfo(@PathVariable(required = true) int id) {
		SellplatformEntity _info = new SellplatformEntity();
		_info = service.findById(id);
		return _info;
	}
	
	@RequestMapping(value = "/osl/sellplatform", method = RequestMethod.PUT)
	@ResponseBody
	public String updateProduct(SellplatformEntity _sellplatform) {
		if (_sellplatform.getId() > 0) {
			int ok = service.updateSellplatform(_sellplatform);
			if (ok > 0) {
				return "ok";
			} else {
				return "fail";
			}
		} else {
			return "fail";
		}
	}
	
	@RequestMapping(value = "/osl/sellplatform/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteProduct(@PathVariable(required = true) int id) {
		int ok = service.deleteById(id);
		if (ok > 0) {
			return "ok";
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
