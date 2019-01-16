/**
 * 
 */
package com.osl.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.osl.common.web.BaseController;
import com.osl.model.BusinessModel;
import com.osl.model.UserModel;
import com.osl.service.BusinessService;
import com.osl.service.UserService;

/**
 * @author zhangxiao
 *
 */

@Controller
public class MasterController extends BaseController<UserModel> {

	@Autowired
	private BusinessService business_service;
	@Autowired
	private UserService userService;

	@GetMapping("/master/login")
	public String Masterlogin(Model model) {
		return "/m/login";
	}

	@PostMapping(value = "/master/login")
	public String doMasterlogin(Model model, HttpSession session, @RequestParam("userId") String userId,
			@RequestParam("password") String password) {
		if (userId.isEmpty() && password.isEmpty()) {
			model.addAttribute("erorrMsg", "请输入用户名和密码");
			return "/m/login";
		} else {
			password = DigestUtils.md5DigestAsHex(password.getBytes());
			UserModel user1 = userService.Masterlogin(userId, password);
			if (user1 == null) {
				model.addAttribute("erorrMsg", "用户名或者密码不正确");
				return "/m/login";
			} else {
				session.setMaxInactiveInterval(30 * 60);
				this.setSessAttr("u_login", user1.getUsername());
				if (user1.getStatus() == 0) {
					if (user1.getBusinessId() == 0 && user1.getIsadmin() == -1) {
						this.setSessAttr("u_userId", user1.getUserId());
						this.setSessAttr("u_login", user1.getUsername());
						return "redirect:/master/index";
					} else {
						model.addAttribute("erorrMsg", "您不是管理员，不能登录");
						return "/m/login";
					}
				} else {
					model.addAttribute("erorrMsg", "您的登录ID已经停用");
					return "/m/login";
				}
			}
		}
	}
	
	@GetMapping("/master/index")
	public String masterIndex(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/master/login";
		} else {
			return "/m/index";
		}
	}
	
	@GetMapping("/master/loginout")
	public String doMasterloginout(HttpSession session) {
		this.clearSess();
		return "redirect:/master/login";
	}
	
	@RequestMapping(value = "/m/warehouse/list")
	public String m_wlistManage(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/master/login";
		} else {
			List<BusinessModel> _businessInfo = business_service.findWarehouseAll();
			model.addAttribute("item", _businessInfo);
			model.addAttribute("nav_active5", 1);
			return "/m/warehouselist";
		}
	}
	
	
	
	
	
	
	

	/**
	 * <p>
	 * Title: getPageId
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 * @see com.osl.common.web.BaseController#getPageId()
	 */
	@Override
	protected String getPageId() {
		// TODO Auto-generated method stub
		return null;
	}

}
