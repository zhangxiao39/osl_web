package com.osl.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class SystemController {
	
	@RequestMapping(value = "/a/sys/warehouseManage")
	public String wareHouseManage(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active6", 3);
			return "/w/sys/warehouseManage";
		}
	}
	
	@RequestMapping(value = "/a/sys/shelvesManage")
	public String shelvesManage(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active6", 4);
			return "/w/sys/shelvesManage";
		}
	}
		
	@RequestMapping(value = "/b/sys/rightManage")
	public String b_rightManage(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/business/login";
		} else {
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active6", 2);
			return "/c/sys/rightManage";
		}
	}
	
	@RequestMapping(value = "/a/sys/rightManage")
	public String rightManage(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active6", 2);
			return "/w/sys/rightManage";
		}
	}

}
