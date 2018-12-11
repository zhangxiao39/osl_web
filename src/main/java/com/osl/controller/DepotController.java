package com.osl.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.osl.common.web.BaseController;
import com.osl.model.BusinessModel;
import com.osl.model.DepotModel;
import com.osl.service.DepotService;

@Controller
public class DepotController extends BaseController<DepotModel> {

	@Autowired
	private DepotService service;

	@RequestMapping(value = "/a/sys/warehouseManage")
	public String wareHouseManage(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			List<DepotModel> _depotInfo = service.findDepotAll(myBusiness_id);
			model.addAttribute("item", _depotInfo);
			model.addAttribute("nav_active6", 3);
			return "/w/sys/warehouseManage";
		}
	}

	@Override
	protected String getPageId() {
		// TODO Auto-generated method stub
		return null;
	}

}
