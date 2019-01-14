package com.osl.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.osl.common.web.BaseController;
import com.osl.mapper.entity.RelationshipEntity;
import com.osl.model.UserModel;
import com.osl.service.RelationshipService;
import com.osl.service.UserService;

@Controller
public class LoginController extends BaseController<UserModel> {
	@Autowired
	private UserService service;
	
	@Autowired
	private RelationshipService relationshipService;

	/**
	 * 
	 * @Title: login
	 * @Description: 用户登录
	 * @param @param model
	 * @param @return 参数
	 * @return String 返回类型
	 * @@author zhangxiao
	 */
	@GetMapping("/login")
	public String login(Model model) {
		return "/login";
	}

	/**
	 * 
	 * @Title: dologin
	 * @Description: 用户登录
	 * @param @param model
	 * @param @param session
	 * @param @param userId
	 * @param @param password
	 * @param @return 参数
	 * @return String 返回类型
	 * @@author zhangxiao
	 */
	@PostMapping(value = "/login")
	public String dologin(Model model, HttpSession session, @RequestParam("userId") String userId,
			@RequestParam("password") String password) {
		if (userId.isEmpty() && password.isEmpty()) {
			model.addAttribute("erorrMsg", "请输入用户名和密码");
			return "/login";
		} else {
			password = DigestUtils.md5DigestAsHex(password.getBytes());
			UserModel user1 = service.login(userId, password);
			if (user1 == null) {
				model.addAttribute("erorrMsg", "用户名或者密码不正确");
				return "/login";
			} else {
				List<RelationshipEntity> relationshipList = relationshipService.queryShipByBusinessId(user1.getBusinessId());
				session.setMaxInactiveInterval(30 * 60);
				this.setSessAttr("u_login", user1.getUsername());
				this.setSessAttr("u_bname", user1.getBname());
				this.setSessAttr("u_bid", user1.getBusinessId());
				this.setSessAttr("u_relationship", relationshipList);
				if (user1.getStatus() == 0) {
					if (user1.getbType() == 0) {
						return "redirect:/admin/index";
					} else {
						return "redirect:/business/index";
					}
				} else {
					model.addAttribute("erorrMsg", "您的登录ID已经停用");
					return "/login";
				}
			}
		}
	}

	/**
	 * 
	 * @Title: dologinout
	 * @Description: 用户登录注销
	 * @param @param session
	 * @param @return 参数
	 * @return String 返回类型
	 * @@author zhangxiao
	 */
	@GetMapping("/loginout")
	public String dologinout(HttpSession session) {
		this.clearSess();
		return "redirect:/login";
	}

	@GetMapping("/business/login")
	public String doBusinesslogin() {
		return "/login";
	}

	@PostMapping(value = "/business/login")
	public String login(Model model, HttpSession session, @RequestParam("user_id") String user_id,
			@RequestParam("password") String password, @RequestParam("burl") String burl) {
		if (burl.isEmpty()) {
			model.addAttribute("erorrMsg", "登录路径不正确");
			return "/c/login";
		} else if (user_id.isEmpty() && password.isEmpty()) {
			return "/c/login";
		} else {
			password = DigestUtils.md5DigestAsHex(password.getBytes());
			UserModel user1 = service.login(user_id, password);
			if (user1 == null) {
				model.addAttribute("erorrMsg", "用户名或者密码不正确");
				model.addAttribute("burl", burl);
				return "/c/login";
			} else {
				session.setMaxInactiveInterval(30 * 60);
				this.setSessAttr("u_login", user1.getUsername());
				this.setSessAttr("u_burl", burl);
				this.setSessAttr("u_bname", user1.getBname());
				this.setSessAttr("u_bid", user1.getBusinessId());
				return "redirect:/business/index";
			}
		}
	}

	@GetMapping("/business/register")
	public String register() {
		return "/register";
	}

	@GetMapping("/business/index")
	public String dashboard(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/business/login";
		} else {
			return "/c/index";
		}
	}

	@GetMapping("/business/loginout")
	public String loginout(HttpSession session, String url) {
		String burl = url;
		this.clearSess();
		return "redirect:/business/login?url=" + burl;
	}

	@Override
	protected String getPageId() {
		// TODO Auto-generated method stub
		return null;
	}
}
