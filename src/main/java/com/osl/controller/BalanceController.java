package com.osl.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.osl.common.web.BaseController;
import com.osl.model.BalanceModel;
import com.osl.model.BalancebaseModel;
import com.osl.model.BusinessModel;
import com.osl.service.BalanceService;
import com.osl.service.BusinessService;

@Controller
public class BalanceController extends BaseController<BalanceModel>{
	
	@Autowired
	private BalanceService service;
	@Autowired
	private BusinessService business_service;

	@RequestMapping(value = "/a/balance/request")
	public String b_requestManage(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active4", 1);
			return "/w/balance/request";
		}
	}

	@RequestMapping(value = { "/a/balance/list/{bid}/{id}", "/a/balance/list" })
	public String b_listManage(Model model, HttpSession session, @PathVariable(required = false) String id) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active4", 2);
			return "/w/balance/balancelist";
		}
	}

	@RequestMapping(value = { "/a/balance/detail/{bid}/{id}", "/a/balance/detail" })
	public String b_detailManage(Model model, HttpSession session, @PathVariable(required = false) String id) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active4", 2);
			return "/w/balance/balanceDetail";
		}
	}

	@RequestMapping(value = "/b/balance/request")
	public String b_b_requestManage(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active4", 1);
			return "/c/balance/request";
		}
	}

	@RequestMapping(value = { "/b/balance/list/{id}", "/b/balance/list" })
	public String b_b_listManage(Model model, HttpSession session, @PathVariable(required = false) String id) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active4", 2);
			return "/c/balance/balancelist";
		}
	}

	@RequestMapping(value = { "/b/balance/detail/{id}", "/b/balance/detail" })
	public String b_b_detailManage(Model model, HttpSession session, @PathVariable(required = false) String id) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active4", 2);
			return "/c/balance/balanceDetail";
		}
	}
	
	@RequestMapping(value = "/b/balance/paylist")
	public String b_b_payListManage(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active4", 3);
			return "/c/balance/paylist";
		}
	}

	@Override
	protected String getPageId() {
		// TODO Auto-generated method stub
		return null;
	}
}
