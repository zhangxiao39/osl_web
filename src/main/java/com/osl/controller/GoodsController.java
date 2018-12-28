package com.osl.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.osl.common.Util;
import com.osl.common.UtilConst;
import com.osl.common.web.BaseController;
import com.osl.mapper.entity.GoodsEntity;
import com.osl.model.BusinessModel;
import com.osl.model.GoodsCategoryModel;
import com.osl.model.GoodsModel;
import com.osl.service.BusinessService;
import com.osl.service.GoodsService;
import com.osl.service.GoodscategoryService;

@Controller
public class GoodsController extends BaseController<GoodsModel> {

	@Autowired
	private GoodsService service;
	@Autowired
	private BusinessService business_service;
	@Autowired
	private GoodscategoryService goods_category_service;

	/**
	 * 
	* @Title: g_listManage  
	* @Description: 运营商  商品一览显示  检索查询
	* @param @param model
	* @param @param session
	* @param @param qry_categoryId  分类ID检索
	* @param @param qry_sku  SKU检索
	* @param @param qry_barcode  条码枷锁
	* @param @param qry_name   名称教唆
	* @param @param qry_businessId   商家枷锁
	* @param @return    参数  
	* @return String    返回类型  
	* @@author zhangxiao
	 */
	@RequestMapping(value = "/a/goods/goodslist", method = RequestMethod.GET)
	public String g_listManage(Model model, HttpSession session, @RequestParam(required = false) String qry_categoryId,
			@RequestParam(required = false) String qry_sku, @RequestParam(required = false) String qry_barcode,
			@RequestParam(required = false) String qry_name,@RequestParam(required = false) String qry_businessId) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			List<BusinessModel> _businessInfo = business_service.findBusinessAll(myBusiness_id,1);
			model.addAttribute("item_business", _businessInfo);
			GoodsModel goodsModel = new GoodsModel();
			if (!Util.isEmpty(qry_categoryId)) {
				goodsModel.setCategoryId(Integer.valueOf(qry_categoryId));
			}
			if (!Util.isEmpty(qry_businessId)) {
				goodsModel.setBusinessId(Integer.valueOf(qry_businessId));
			}
			goodsModel.setSku(qry_sku);
			goodsModel.setBarcode(qry_barcode);
			goodsModel.setName(qry_name);
			List<GoodsModel> _goodsList = service.find_goodsAll(goodsModel);
			model.addAttribute("item", _goodsList);
			
			//商品分类显示
			String _goodsCategrory = goods_category_service.getGoodsCategorySelect(qry_categoryId);
			model.addAttribute("item_gc", _goodsCategrory);
			model.addAttribute("nav_active2", 2);
			return "/w/goods/goodslist";
		}
	}

	/**
	 * 
	* @Title: b_g_listManage  
	* @Description: 商家 商品显示一览 检索查询
	* @param @param model
	* @param @param session
	* @param @param qry_categoryId  商品分类检索
	* @param @param qry_sku  商品SKU检索
	* @param @param qry_barcode   商品条码检索
	* @param @param qry_name  商品名称检索
	* @param @return    参数  
	* @return String    返回类型  
	* @@author zhangxiao
	 */
	@RequestMapping(value = "/b/goods/goodslist", method = RequestMethod.GET)
	public String b_g_listManage(Model model, HttpSession session,
			@RequestParam(required = false) String qry_categoryId, @RequestParam(required = false) String qry_sku,
			@RequestParam(required = false) String qry_barcode, @RequestParam(required = false) String qry_name) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/business/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			GoodsModel goodsModel = new GoodsModel();
			goodsModel.setBusinessId(myBusiness_id);
			if (!Util.isEmpty(qry_categoryId)) {
				goodsModel.setCategoryId(Integer.valueOf(qry_categoryId));
			}
			goodsModel.setSku(qry_sku);
			goodsModel.setBarcode(qry_barcode);
			goodsModel.setName(qry_name);
			List<GoodsModel> _goodsList = service.find_goodsAll(goodsModel);
			model.addAttribute("item", _goodsList);
			
			//商品分类检索部分显示
			String _goodsCategrory = goods_category_service.getGoodsCategorySelect(qry_categoryId);
			model.addAttribute("item_gc", _goodsCategrory);
			
			//商品分类 商品添加修改部分显示
			String _goodsCategrory2 = goods_category_service.getGoodsCategorySelect2();
			model.addAttribute("item_gc2", _goodsCategrory2);
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
			int ok = service.insertGoods(_goods);
			if (ok > 0) {
				return "ok";
			} else if (ok == -1) {
				return "exist";
			} else {
				return "fail";
			}
		}

	}

	@RequestMapping(value = "/osl/goods/{goodsId}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteProduct(@PathVariable(required = true) String goodsId) {
		int ok = service.deleteById(goodsId);
		if (ok > 0) {
			return "ok";
		} else {
			return "fail";
		}
	}

	@RequestMapping(value = "/osl/goods/{goodsId}", method = RequestMethod.GET)
	@ResponseBody
	public GoodsEntity showInfo(@PathVariable(required = true) String goodsId) {
		GoodsEntity _info = new GoodsEntity();
		_info = service.findById(goodsId);
		return _info;
	}

	@RequestMapping(value = "/osl/goods", method = RequestMethod.PUT)
	@ResponseBody
	public String updateProduct(GoodsEntity _goods) {
		if (_goods.getGoodsId() != "0") {
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

	/**
	 * 
	* @Title: showInfoBySku  
	* @Description: 根据商品SKU检索商品
	* @param @param sku
	* @param @return    参数  
	* @return GoodsEntity    返回类型  
	* @@author zhangxiao
	 */
	@RequestMapping(value = "/osl/goods/sku/{sku}", method = RequestMethod.GET)
	@ResponseBody
	public GoodsEntity showInfoBySku(@PathVariable(required = true) String sku) {
		GoodsEntity _info = new GoodsEntity();
		_info = service.findBySku(sku);
		return _info;
	}

	
	/**
	 * 
	* @Title: showBusinessInfoBySku  
	* @Description: 根据商品SKU和商家ID检索商品
	* @param @param sku
	* @param @param session
	* @param @return    参数  
	* @return GoodsModel    返回类型  
	* @@author zhangxiao
	 */
	@RequestMapping(value = "/osl/goods/mysku/{sku}", method = RequestMethod.GET)
	@ResponseBody
	public GoodsModel showBusinessInfoBySku(@PathVariable(required = true) String sku, HttpSession session) {
		this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
		GoodsModel _info = new GoodsModel();
		_info = service.find_goodsBusiness_sku(myBusiness_id, sku);
		return _info;
	}

	@Override
	protected String getPageId() {
		// TODO Auto-generated method stub
		return "test_user_page";
	}

}
