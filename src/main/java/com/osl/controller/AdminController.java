package com.osl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.osl.model.UserModel;
import com.osl.model.Users;
import com.osl.service.UserService;
import com.osl.service.UsersService;

@Controller

public class AdminController {

	@Autowired
	private UserService service;

	@GetMapping("/admin/login")
	public String dologin(Model model, String url) {
		if (url == null || url.isEmpty()) {
			model.addAttribute("erorrMsg", "登录路径不正确");
			return "/w/login";
		} else {
			model.addAttribute("burl", url);
			return "/w/login";
		}
	}

	@PostMapping(value = "/admin/login")
	public String login(Model model, HttpSession session, @RequestParam("user_id") String user_id,
			@RequestParam("password") String password, @RequestParam("burl") String burl) {
		if (burl.isEmpty()) {
			model.addAttribute("erorrMsg", "登录路径不正确");
			return "/w/login";
		} else if (user_id.isEmpty() && password.isEmpty()) {
			return "/w/login";
		} else {
			password = DigestUtils.md5DigestAsHex(password.getBytes());
			UserModel user1 = service.findByUserName_admin(user_id, password, burl);
			if (user1 == null) {
				model.addAttribute("erorrMsg", "用户名或者密码不正确");
				model.addAttribute("burl", burl);
				return "/w/login";
			} else {
				session.setMaxInactiveInterval(30 * 60);
				session.setAttribute("u_login", user1.getUsername());
				session.setAttribute("u_burl", burl);
				session.setAttribute("u_bname", user1.getBname());
				session.setAttribute("u_bid", user1.getBusinessId());
				return "redirect:/admin/index";
			}
		}
	}

	@GetMapping("/admin/register")
	public String register() {
		return "/register";
	}

	@GetMapping("/admin/index")
	public String dashboard(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			return "/w/index";
		}
	}

	@GetMapping("/admin/loginout")
	public String loginout(HttpSession session, String url) {
		String burl = url;
		session.setAttribute("u_login", null);
		session.setAttribute("u_burl", null);
		session.setAttribute("u_bname", null);
		return "redirect:/admin/login?url=" + burl;
	}
}
