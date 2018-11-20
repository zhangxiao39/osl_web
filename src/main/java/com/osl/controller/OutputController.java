package com.osl.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OutputController {

	@RequestMapping(value = "/b/output/request")
	public String b_outputRequest(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/business/login";
		} else {
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active1", 5);
			return "/c/output/request";
		}
	}
	
	@RequestMapping(value = "/b/output/list")
	public String b_outputList(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/business/login";
		} else {
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active1", 6);
			return "/c/output/list";
		}
	}
	
	@RequestMapping(value = "/b/output/detail/{id}")
	public String b_entrys_Detail(Model model, HttpSession session, @PathVariable(required = false) String id) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/business/login";
		} else {
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active1", 6);
			return "/c/output/detail";
		}
	}
	
	@RequestMapping(value = "/a/output/list")
	public String a_outputList(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active1", 5);
			return "/w/output/list";
		}
	}
}
