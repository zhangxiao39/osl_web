package com.osl.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.osl.common.web.BaseController;
import com.osl.model.ShelvesModel;
import com.osl.service.ShelvesService;

@Controller
public class ShelvesController extends BaseController<ShelvesModel> {

	@Autowired
	private ShelvesService service;

	@RequestMapping(value = "/a/sys/shelvesManage")
	public String shelvesManage(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			List<ShelvesModel> _shelvesInfo = service.findShelvesAll(myBusiness_id);
			model.addAttribute("item", _shelvesInfo);
			model.addAttribute("nav_active6", 4);
			return "/w/sys/shelvesManage";
		}
	}

	@Override
	protected String getPageId() {
		// TODO Auto-generated method stub
		return null;
	}

}
