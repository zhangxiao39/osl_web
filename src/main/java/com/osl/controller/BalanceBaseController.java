/**
 * 
 */
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

import com.osl.common.web.BaseController;
import com.osl.mapper.entity.BalancebaseEntity;
import com.osl.mapper.entity.GoodsEntity;
import com.osl.model.BalancebaseModel;
import com.osl.model.BusinessModel;
import com.osl.service.BalanceBaseService;
import com.osl.service.BusinessService;

/**
 * @author zhangxiao
 *
 */

@Controller
public class BalanceBaseController extends BaseController<BalancebaseModel> {

	@Autowired
	private BalanceBaseService service;
	@Autowired
	private BusinessService business_service;

	@RequestMapping(value = "/a/balance/setting", method = RequestMethod.GET)
	public String b_settingManage(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			List<BalancebaseModel> _balanceInfo = service.findBalanceBaseAll(myBusiness_id);
			model.addAttribute("item", _balanceInfo);
			List<BusinessModel> _businessInfo = business_service.findBusinessAll(myBusiness_id, 1);
			model.addAttribute("item_business", _businessInfo);
			model.addAttribute("nav_active4", 4);
			return "/w/balance/setting";
		}
	}

	@RequestMapping(value = "/osl/balancebase", method = RequestMethod.POST)
	@ResponseBody
	public String addBalanceBase(Model model, HttpSession session, BalancebaseEntity _balancebaseEntity) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			int ok = service.insert(_balancebaseEntity);
			if (ok > 0) {
				return "ok";
			} else if (ok == -1) {
				return "exist";
			} else {
				return "fail";
			}
		}

	}

	@RequestMapping(value = "/osl/balancebase/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteBalanceBase(@PathVariable(required = true) int id) {
		int ok = service.deleteById(id);
		if (ok > 0) {
			return "ok";
		} else {
			return "fail";
		}
	}

	@RequestMapping(value = "/osl/balancebase/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BalancebaseEntity showInfo(@PathVariable(required = true) int id) {
		BalancebaseEntity _info = new BalancebaseEntity();
		_info = service.findById(id);
		return _info;
	}

	@RequestMapping(value = "/osl/balancebase", method = RequestMethod.PUT)
	@ResponseBody
	public String updateBalanceBase(BalancebaseEntity _balancebaseEntity) {
		if (_balancebaseEntity.getId() > 0) {
			int ok = service.update(_balancebaseEntity);
			if (ok > 0) {
				return "ok";
			} else {
				return "fail";
			}
		} else {
			return "fail";
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
