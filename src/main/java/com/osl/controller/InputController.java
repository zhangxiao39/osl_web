package com.osl.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InputController {

	@RequestMapping(value = "/a/input/list")
	public String w_entrys_List(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active1", 4);
			return "/w/input/list";
		}
	}
	
	@RequestMapping(value = "/a/input/detail/{bid}/{eid}")
	public String w_entrys_Detail(Model model, HttpSession session, @PathVariable(required = false) String bid,
			@PathVariable(required = false) String eid) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active1", 4);
			return "/w/input/detail";
		}
	}
	
	@RequestMapping(value = "/b/input/list")
	public String b_entrys_List(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active1", 4);
			return "/c/input/list";
		}
	}
	
	@RequestMapping(value = "/b/input/detail/{id}/")
	public String b_entrys_Detail(Model model, HttpSession session, @PathVariable(required = false) String id,
			@PathVariable(required = false) String eid) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active1", 4);
			return "/c/input/detail";
		}
	}
}
