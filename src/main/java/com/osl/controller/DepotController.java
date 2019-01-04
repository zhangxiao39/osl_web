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

import com.osl.common.UtilConv;
import com.osl.common.web.BaseController;
import com.osl.mapper.entity.DepotEntity;
import com.osl.model.DepotModel;
import com.osl.service.DepotService;

@Controller
public class DepotController extends BaseController<DepotModel> {

	@Autowired
	private DepotService service;

	@RequestMapping(value = "/a/sys/warehouseManage")
	public String wareHouseManage(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			this.myBusiness_id = UtilConv.str2Int(session.getAttribute("u_bid").toString());
			List<DepotModel> _depotInfo = service.findDepotAll(myBusiness_id);
			model.addAttribute("item", _depotInfo);
			model.addAttribute("nav_active6", 3);
			return "/w/sys/warehouseManage";
		}
	}

	@RequestMapping(value = "/osl/depot", method = RequestMethod.POST)
	@ResponseBody
	public String addDepot(Model model, HttpSession session, DepotEntity _depot) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			int ok = service.insertDepot(_depot);
			if (ok > 0) {
				return "ok";
			} else if (ok == -1) {
				return "exist";
			} else {
				return "fail";
			}
		}

	}

	@RequestMapping(value = "/osl/depot/{depotId}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteDepot(@PathVariable(required = true) String depotId) {
		int ok = service.deleteById(depotId);
		if (ok > 0) {
			return "ok";
		} else {
			return "fail";
		}
	}

	@RequestMapping(value = "/osl/depot/{depotId}", method = RequestMethod.GET)
	@ResponseBody
	public DepotEntity showInfo(@PathVariable(required = true) String depotId) {
		DepotEntity _info = new DepotEntity();
		_info = service.findById(depotId);
		return _info;
	}

	@RequestMapping(value = "/osl/depot", method = RequestMethod.PUT)
	@ResponseBody
	public String updateDepot(DepotEntity _depot) {
		if (_depot.getDepotId() != "0") {
			int ok = service.updateDepot(_depot);
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
	 * 查询仓库list对象 返回前台展示
	 * 
	 * @author zhangzy
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/a/search/depot")
	@ResponseBody
	public List<DepotModel> wSearchDepotEntityList(Model model, HttpSession session) {
		// 查询仓库List信息对象
		this.myBusiness_id = UtilConv.str2Int(session.getAttribute("u_bid").toString());
		List<DepotModel> depotModelList = service.findDepotAll(this.myBusiness_id);
		return depotModelList;
	}

	@Override
	protected String getPageId() {
		// TODO Auto-generated method stub
		return null;
	}

}
