package com.osl.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.osl.service.UserService;
import com.osl.common.web.BaseController;
import com.osl.mapper.entity.UserEntity;
import com.osl.model.UserModel;

@Controller
public class UserController extends BaseController<UserModel> {

	@Autowired
	private UserService user_service;

	/**
	 * 
	 * @Title: userList
	 * @Description: 运营商员工管理一览
	 * @param @param model
	 * @param @param session
	 * @param @return 参数
	 * @return String 返回类型
	 * @@author zhangxiao
	 */
	@RequestMapping(value = "/a/sys/userManage", method = RequestMethod.GET)
	public String userList(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			model.addAttribute("nav_active6", 1);
			List<UserModel> uList = user_service.getAllUsers(myBusiness_id);
			model.addAttribute("item", uList);
			return "/w/sys/userManage";
		}
	}

	/**
	 * 
	* @Title: addUser  
	* @Description: 添加员工
	* @param @param model
	* @param @param session
	* @param @param _user
	* @param @return    参数  
	* @return String    返回类型  
	* @@author zhangxiao
	 */
	@RequestMapping(value = "/osl/user", method = RequestMethod.POST)
	@ResponseBody
	public String addUser(Model model, HttpSession session, UserEntity _user) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/login";
		} else {
			if (_user.getId() == 0) {
				int ok = user_service.insert(_user);
				if (ok > 0) {
					return "ok";
				} else if (ok == -1) {
					return "exist";
				} else {
					return "fail";
				}
			} else {
				return "fail";
			}
		}
	}

	/**
	 * 
	* @Title: deleteUser  
	* @Description: 逻辑删除用户 
	* @param @param id
	* @param @return    参数  
	* @return String    返回类型  
	* @@author zhangxiao
	 */
	@RequestMapping(value = "/osl/user/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteUser(@PathVariable(required = true) int id) {
		int ok = user_service.deleteById(id);
		if (ok > 0) {
			return "ok";
		} else {
			return "fail";
		}
	}

	/**
	 * 
	* @Title: stopUser  
	* @Description: 停止员工操作
	* @param @param id
	* @param @return    参数  
	* @return String    返回类型  
	* @@author zhangxiao
	 */
	@RequestMapping(value = "/osl/user/stop/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String stopUser(@PathVariable(required = true) int id) {
		int ok = user_service.stopUser(id);
		if (ok > 0) {
			return "ok";
		} else {
			return "fail";
		}
	}

	/**
	 * 
	* @Title: recoveryUser  
	* @Description: 恢复员工操作  
	* @param @param id
	* @param @return    参数  
	* @return String    返回类型  
	* @@author zhangxiao
	 */
	@RequestMapping(value = "/osl/user/recovery/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String recoveryUser(@PathVariable(required = true) int id) {
		int ok = user_service.recoveryUser(id);
		if (ok > 0) {
			return "ok";
		} else {
			return "fail";
		}
	}

	/***
	 * 
	 * @Title: b_userManage
	 * @Description: 商家员工管理一览
	 * @param @param model
	 * @param @param session
	 * @param @return 参数
	 * @return String 返回类型
	 * @@author zhangxiao
	 */
	@RequestMapping(value = "/b/sys/userManage")
	public String b_userManage(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			model.addAttribute("nav_active6", 1);
			List<UserModel> uList = user_service.getAllUsers(myBusiness_id);
			model.addAttribute("item", uList);
			return "/c/sys/userManage";
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
