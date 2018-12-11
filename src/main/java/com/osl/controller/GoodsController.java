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
import com.osl.mapper.entity.GoodsEntity;
import com.osl.model.BusinessModel;
import com.osl.model.GoodsModel;
import com.osl.service.BusinessService;
import com.osl.service.GoodsService;

@Controller
public class GoodsController extends BaseController<GoodsModel> {

	@Autowired
	private GoodsService service;
	@Autowired
	private BusinessService business_service;

	@RequestMapping(value = "/a/goods/goodslist", method = RequestMethod.GET)
	public String g_listManage(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			List<BusinessModel> _businessInfo = business_service.findBusinessAll(myBusiness_id);
			model.addAttribute("item_business", _businessInfo);
			List<GoodsModel> _goodsList = service.find_goodsAll();
			model.addAttribute("item", _goodsList);
			model.addAttribute("nav_active2", 2);
			return "/w/goods/goodslist";
		}
	}

	@RequestMapping(value = "/b/goods/goodslist", method = RequestMethod.GET)
	public String b_g_listManage(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/business/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			List<GoodsModel> _goodsList = service.find_goodsBusiness_All(this.myBusiness_id);
			model.addAttribute("item", _goodsList);
			model.addAttribute("nav_active2", 2);
			return "/c/goods/goodslist";
		}
	}

	@RequestMapping(value = "/osl/goods", method = RequestMethod.POST)
	@ResponseBody
	public String addProduct(Model model, HttpSession session, GoodsEntity _goods) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			if (_goods.getId() == 0) {
				int ok = service.insertGoods(_goods);
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

	@RequestMapping(value = "/osl/goods/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteProduct(@PathVariable(required = true) int id) {
		int ok = service.deleteById(id);
		if (ok > 0) {
			return "ok";
		} else {
			return "fail";
		}
	}

	@RequestMapping(value = "/osl/goods/{id}", method = RequestMethod.GET)
	@ResponseBody
	public GoodsEntity showInfo(@PathVariable(required = true) int id) {
		GoodsEntity _info = new GoodsEntity();
		_info = service.findById(id);
		return _info;
	}

	@RequestMapping(value = "/osl/goods", method = RequestMethod.PUT)
	@ResponseBody
	public String updateProduct(GoodsEntity _goods) {
		if (_goods.getId() > 0) {
			int ok = service.updateGoods(_goods);
			if (ok > 0) {
				return "ok";
			} else {
				return "fail";
			}
		} else {
			return "fail";
		}
	}

	@RequestMapping(value = "/osl/goods/sku/{sku}", method = RequestMethod.GET)
	@ResponseBody
	public GoodsEntity showInfoBySku(@PathVariable(required = true) String sku) {
		GoodsEntity _info = new GoodsEntity();
		_info = service.findBySku(sku);
		return _info;
	}

	@Override
	protected String getPageId() {
		// TODO Auto-generated method stub
		return "test_user_page";
	}

}
