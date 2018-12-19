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
import com.osl.mapper.entity.ShelvesEntity;
import com.osl.model.DepotModel;
import com.osl.model.ShelvesModel;
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
