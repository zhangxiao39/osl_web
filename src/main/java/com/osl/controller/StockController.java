package com.osl.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.osl.common.web.BaseController;
import com.osl.model.BusinessModel;
import com.osl.model.GoodsModel;
import com.osl.model.StockModel;
import com.osl.service.GoodsService;
import com.osl.service.StockService;

@Controller
public class StockController extends BaseController<StockModel> {
	
	
	@Autowired
	private StockService service;
	
	/*
	 * @des:商家端，获取库存一览
	 * @author：sun-hongyu
	 * @date:2018-12-10
	 * @param:
	 * @return:list<StockModel>
	 */
	@RequestMapping(value = "/b/stock/stock", method = RequestMethod.GET)
	public String cStockManage(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			List<StockModel> _stockList = service.find_stockBusiness_All(this.myBusiness_id);
			model.addAttribute("item", _stockList);
			model.addAttribute("nav_active1", 1);
			return "/c/stock/stock";
		}
	}
	
	/*
	 * @des:商家端，条件查询库存一览
	 * @author：sun-hongyu
	 * @date:2018-12-10
	 * @param:
	 * @return:list<StockModel>
	 */
	@RequestMapping(value = "/b/stock/condition")
	public String cStockListByCondition(Model model, HttpSession session,@RequestParam(required = false) String sku,
				@RequestParam(required = false) String name,
				@RequestParam(required = false) String barCode,
				@RequestParam(required = false) String nums,
				@RequestParam(required = false) String status,
				@RequestParam(required = false) int goodsCategoryId
				) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			StockModel stockModel = new StockModel();
			stockModel.setSku(sku);
			stockModel.setGoodsName(name);
			stockModel.setNums((!nums.equals("")&&nums!=null)?Integer.parseInt(nums):0);
			stockModel.setBarCode(barCode);
			stockModel.setCategoryId(goodsCategoryId);
			if((!status.equals("")&&null!=status))
			{
				stockModel.setGoodsType(Integer.parseInt(status));
			}else {
				stockModel.setGoodsType(1000);
			}
			List<StockModel> _stockList = service.find_stockBusiness_by_condition(this.myBusiness_id,stockModel);
			model.addAttribute("item", _stockList);
			model.addAttribute("nav_active1", 1);
			return "/c/stock/stock::table_refresh";
		}
	}
	
	/*
	 * @des:商家端，获取对应的商品列表
	 * @author：sun-hongyu
	 * @date:2018-12-13
	 * @param:
	 * @return:list<StockModel>
	 */
	@RequestMapping(value = "/a/ModelList", method = RequestMethod.POST)
	@ResponseBody
	public List<StockModel> cGetModelList(HttpSession session) {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			List<StockModel> stockList = service.find_stockBusiness_All(this.myBusiness_id);
			return stockList;
	}
	
	/*
	 * @des:运营商端，获取库存一览
	 * @author：sun-hongyu
	 * @date:2018-12-12
	 * @param:
	 * @return:list<StockModel>
	 */
	@RequestMapping(value = "/a/stock/adminStock")
	public String wAdminStockManage(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			this.myBusiness_id =Integer.valueOf(session.getAttribute("u_bid").toString());
			List<StockModel> _stockList = service.find_adminStock_All(this.myBusiness_id);
			model.addAttribute("item", _stockList);
			model.addAttribute("nav_active1", 1);
			return "/w/stock/stock";
		}
	}
	
	/*
	 * @des:运营商端，条件查询库存一览
	 * @author：sun-hongyu
	 * @date:2018-12-10
	 * @param:
	 * @return:list<StockModel>
	 */
	@RequestMapping(value = "/b/stock/adminCondition")
	public String wAdminStockListByCondition(Model model, HttpSession session,@RequestParam(required = false) String sku,
				@RequestParam(required = false) String name,
				@RequestParam(required = false) String barCode,
				@RequestParam(required = false) String nums,
				@RequestParam(required = false) String status,
				@RequestParam(required = false) int goodsCategoryId,
				@RequestParam(required = false) int businessId) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			StockModel stockModel = new StockModel();
			stockModel.setSku(sku);
			stockModel.setGoodsName(name);
			stockModel.setNums((!nums.equals("")&&nums!=null)?Integer.parseInt(nums):0);
			stockModel.setBarCode(barCode);
			stockModel.setBusinessId(businessId);
			stockModel.setCategoryId(goodsCategoryId);
			if((!status.equals("")&&null!=status))
			{
				stockModel.setGoodsType(Integer.parseInt(status));
			}else {
				stockModel.setGoodsType(1000);
			}
			List<StockModel> _stockList = service.find_adminStock_by_condition(this.myBusiness_id,stockModel);
			model.addAttribute("item", _stockList);
			model.addAttribute("nav_active1", 1);
			return "/w/stock/stock::table_refresh";
		}
	}
	
	/*
	 * @des:运营商端，获取对应的商品列表
	 * @author：sun-hongyu
	 * @date:2018-12-13
	 * @param:
	 * @return:list<StockModel>
	 */
	@RequestMapping(value = "/b/ModelList", method = RequestMethod.POST)
	@ResponseBody
	public List<StockModel> wGetModelList(HttpSession session) {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			List<StockModel> stockList = service.find_adminStock_All(this.myBusiness_id);
			return stockList;
	}
	
	@RequestMapping(value = "/a/stock/goodsDetail/{bid}/{goodsSku}/")
	public String s_goodsDetail(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active1", 1);
			return "/w/stock/goodsDetail";
		}
	}
	
	@RequestMapping(value = "/a/stock/shelves")
	public String s_shelvesDetail(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active1", 2);
			return "/w/stock/shelves";
		}
	}

	@Override
	protected String getPageId() {
		// TODO Auto-generated method stub
		return null;
	}
}
