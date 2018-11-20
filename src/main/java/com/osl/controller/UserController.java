package com.osl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.osl.service.UsersService;
import com.osl.model.Users;

@Controller
public class UserController {

	@Autowired
	private UsersService service;

	@RequestMapping(value = "/a/sys/userManage/getlist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getUserList(HttpServletResponse rsp) {
		rsp.addHeader("Access-Control-Allow-Origin", "*");
		List<Map> uList = service.getAllUsers("1");
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("data", uList);
		info.put("count", uList.size());
		return info;
	}

	@RequestMapping(value = "/a/sys/userManage", method = RequestMethod.GET)
	public String userList(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			model.addAttribute("nav_active6", 1);
			List<Map> uList = service.getAllUsers(session.getAttribute("u_bid").toString());
			model.addAttribute("item", uList);
			return "/w/sys/userManage";
		}
	}

	@RequestMapping(value = "/a/sys/userManage/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Users showUser(@PathVariable(required = true) String id) {
		Users _user = new Users();
		_user = service.findById(id);
		return _user;
	}

	@RequestMapping(value = "/a/sys/userManage", method = RequestMethod.POST)
	@ResponseBody
	public String addUser(Model model, HttpSession session, Users _user) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			if (_user.getId() == 0) {
				int ok = service.insert(_user);
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

	@RequestMapping(value = "/a/sys/userManage", method = RequestMethod.PUT)
	@ResponseBody
	public String updateUser(Users _user) {
		if (_user.getId() > 0) {
			int ok = service.updateUserById(_user);
			if (ok > 0) {
				return "ok";
			} else {
				return "fail";
			}
		} else {
			return "fail";
		}
	}
	
	@RequestMapping(value = "/a/sys/userManage/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteUser(@PathVariable(required = true) int id) {
		int ok = service.deleteById(id);
		if (ok > 0) {
			return "ok";
		} else {
			return "fail";
		}
	}

	@RequestMapping(value = "/b/sys/userManage")
	public String b_userManage(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/business/login";
		} else {
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active6", 1);
			return "/c/sys/userManage";
		}
	}
}
