package com.osl.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.osl.common.web.BaseController;
import com.osl.mapper.entity.UserEntity;
import com.osl.model.UserModel;
import com.osl.service.UserService;

@Controller

public class SystemController extends BaseController<UserModel>{

	@Autowired
	private UserService user_service;

	@RequestMapping(value = "/b/sys/rightManage")
	public String b_rightManage(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/business/login";
		} else {
			model.addAttribute("nav_active6", 2);
			return "/c/sys/rightManage";
		}
	}

	@RequestMapping(value = "/a/sys/rightManage")
	public String rightManage(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/login";
		} else {
			model.addAttribute("nav_active6", 2);
			return "/w/sys/rightManage";
		}
	}

	@RequestMapping(value = "/a/sys/modifypwd")
	public String a_modifypwd(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/login";
		} else {
			model.addAttribute("nav_active6", 5);
			return "/w/sys/modifypwd";
		}
	}
	
	@RequestMapping(value = "/b/sys/modifypwd")
	public String b_modifypwd(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/login";
		} else {
			model.addAttribute("nav_active6", 6);
			return "/c/sys/modifypwd";
		}
	}
	
	@RequestMapping(value = "/m/sys/modifypwd")
	public String m_modifypwd(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/login";
		} else {
			model.addAttribute("nav_active6", 1);
			return "/m/modifypwd";
		}
	}

	@RequestMapping(value = "/osl/sys/modifypwd", method = RequestMethod.POST)
	@ResponseBody
	public String doModifypwd(Model model, HttpSession session, @RequestParam("oldpwd") String oldpwd,
			@RequestParam("newpwd") String newpwd) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/login";
		} else {
			String _oldpwd = DigestUtils.md5DigestAsHex(DigestUtils.md5DigestAsHex(oldpwd.getBytes()).getBytes());
			String _newpwd = DigestUtils.md5DigestAsHex(newpwd.getBytes());
			String userId = (String) session.getAttribute("u_userId");
			int ok = user_service.modifypwd(_oldpwd, _newpwd, userId);
			if (ok > 0) {
				return "ok";
			} else if (ok == -1) {
				return "errorpwd";
			} else {
				return "fail";
			}
		}
	}

	/**
	* <p>Title: getPageId</p>  
	* <p>Description: </p>  
	* @return  
	* @see com.osl.common.web.BaseController#getPageId()  
	*/  
	@Override
	protected String getPageId() {
		// TODO Auto-generated method stub
		return null;
	}

}
