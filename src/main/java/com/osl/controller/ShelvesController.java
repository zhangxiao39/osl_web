package com.osl.controller;

import java.util.Arrays;
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

import com.alibaba.fastjson.JSON;
import com.osl.common.web.BaseController;
import com.osl.mapper.entity.ShelvesEntity;
import com.osl.model.DepotModel;
import com.osl.model.ShelvesModel;
import com.osl.model.StockModel;
import com.osl.service.ShelvesService;

@Controller
public class ShelvesController extends BaseController<ShelvesModel> {

	@Autowired
	private ShelvesService service;

	@RequestMapping(value = "/a/sys/shelvesManage")
	public String shelvesManage(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			List<ShelvesModel> _shelvesInfo = service.findShelvesAll(myBusiness_id);
			model.addAttribute("item", _shelvesInfo);
			model.addAttribute("nav_active6", 4);
			return "/w/sys/shelvesManage";
		}
	}
	
	/*
	 * @des:运营商端，获取货架一览
	 * @author：sun-hongyu
	 * @date:2018-12-25
	 * @param:
	 * @return:list<ShelvesModel>
	 */
	@RequestMapping(value = "/a/shelves/list", method = RequestMethod.GET)
	public String aShelvesList(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			List<ShelvesModel> shelvesList = service.queryShelvesGoodsByBid(this.myBusiness_id);
			model.addAttribute("item", shelvesList);
			model.addAttribute("nav_active1", 2);
			return "/w/shelves/shelves";
		}
	}
	
	
	/*
	 * @des:运营商端，条件获取货架信息
	 * @author：sun-hongyu
	 * @date:2018-12-25
	 * @param:
	 * @return:list<ShelvesModel>
	 */
	@RequestMapping(value = "/a/shelvesList/condition")
	public String aShelvesByCondition(Model model, HttpSession session,@RequestParam String params) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			ShelvesModel shelvesModel = JSON.parseObject(params, ShelvesModel.class);
			shelvesModel.setWarehouseId(myBusiness_id);
			List<ShelvesModel> shelvesList = service.queryShelvesGoodsCondition(shelvesModel);
			model.addAttribute("item", shelvesList);
			model.addAttribute("nav_active1", 2);
			return "/w/shelves/shelves::table_refresh";
		}
	}
	
	/*
	 * @des:运营商端，获取货架详情
	 * @author：sun-hongyu
	 * @date:2018-12-25
	 * @param:
	 * @return:list<ShelvesModel>
	 */
	@RequestMapping(value = "/a/shelves/shelvesDetail/{shelvesId}")
	public String aShelvesDetail(Model model, HttpSession session,@PathVariable(required = true) String shelvesId) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			this.myBusiness_id =Integer.valueOf(session.getAttribute("u_bid").toString());
			ShelvesModel shelvesModel = new ShelvesModel();
			shelvesModel.setWarehouseId(myBusiness_id);
			shelvesModel.setShelvesId(shelvesId);
			List<ShelvesModel> shelvesList = service.queryShelvesDetail(shelvesModel);
			model.addAttribute("item", shelvesList);
			model.addAttribute("nav_active1", 2);
			return "/w/shelves/shelvesDetail";
		}
	}
	
	/*
	 * @des:运营商端，条件获取货架详情
	 * @author：sun-hongyu
	 * @date:2018-12-25
	 * @param:
	 * @return:list<ShelvesModel>
	 */
	@RequestMapping(value = "/a/shelves/condition")
	public String aShelvesModelListByCondition(Model model, HttpSession session,@RequestParam String params) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			ShelvesModel shelvesModel = JSON.parseObject(params, ShelvesModel.class);
			shelvesModel.setWarehouseId(myBusiness_id);
			List<ShelvesModel> shelvesList = service.queryShelvesDetailByCondition(shelvesModel);
			model.addAttribute("item", shelvesList);
			model.addAttribute("nav_active1", 2);
			return "/w/shelves/shelvesDetail::table_refresh";
		}
	}
	
	
	/**
	 * 查询仓库list对象
	 * 返回前台展示
	 *  
	 * @author zhangzy
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/a/search/shelves/{depotId}" ,  method = RequestMethod.POST)
	@ResponseBody
	public List<ShelvesEntity> wSearchShelvesEntityListByDepotId(Model model, HttpSession session,@PathVariable(required = true) String depotId) {
		//查询仓库List信息对象
		List<ShelvesEntity> depotModelList = service.queryShelvesEntityListByDepotId(depotId);
		return depotModelList;
	}

	@Override
	protected String getPageId() {
		// TODO Auto-generated method stub
		return null;
	}

}
