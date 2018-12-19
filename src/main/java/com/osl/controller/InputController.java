package com.osl.controller;

import java.sql.Date;
import java.util.ArrayList;
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
import com.osl.model.InputdetailModel;
import com.osl.model.IputModel;
import com.osl.service.InputService;

@Controller
public class InputController extends BaseController<InputdetailModel> {

	@Autowired
	private InputService service;

	/**
	 * 运营商-查询入库列表
	 * 
	 * @author zhangzy
	 * 
	 * @param model
	 * @param session
	 * @param inputId
	 * 
	 *            (model中已标识以下字段信息)
	 * @param sku
	 * @param barcode
	 * @param fNewDate
	 * @param lNewDate
	 * @return
	 */
	@RequestMapping(value = "/a/input/list")
	public String wInputList(Model model, HttpSession session, @RequestParam(required = false) String searchFlag,
			@RequestParam(required = false) String inputId, @RequestParam(required = false) String sku,
			@RequestParam(required = false) String barcode, @RequestParam(required = false) String goodsName,
			@RequestParam(required = false) String status) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			//参数组装
			IputModel iputModel = new IputModel();
			iputModel.setWarehouseId(this.myBusiness_id);
			iputModel.setSearchFlag(searchFlag);
			iputModel.setInputId(inputId);
			iputModel.setSku(sku);
			iputModel.setGoodsName(goodsName);
			iputModel.setBarcode(barcode);
			//查询入库列表
			List<IputModel> _inputList = service.findInputAll(iputModel);
			model.addAttribute("item", _inputList);
			model.addAttribute("nav_active1", 4);
			if (Util.isEmpty(iputModel.getSearchFlag())) {
				return "/w/input/list";
			} else {
				return "/w/input/list::table_refresh";
			}
		}
	}

	/**
	 * 运营商-查询入库详情一览页
	 * @author zhangzy
	 */
	@RequestMapping(value = "/a/input/detail")
	public String wInputDetail(Model model, HttpSession session, @RequestParam(required = false) String inputId) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			//查询入库详情列表
			List<InputdetailModel> _inputDetialsList = service.findDetailListById(inputId);
			if (_inputDetialsList == null) {
				_inputDetialsList = new ArrayList<InputdetailModel>();
			}
			model.addAttribute("item", _inputDetialsList);
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active1", 4);
			return "/w/input/detail";
		}
	}
	
	/**
	 * 运营商-新建入库信息
	 * @author zhangzy
	 */
	@RequestMapping(value = "/a/save/inputdetail", method = RequestMethod.POST)
	@ResponseBody
	public String wSaveNewInput(Model model, HttpSession session, InputdetailModel inputdetailModel) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			int tmpBusinessId = Integer.valueOf(session.getAttribute("u_bid").toString());
			String inputDetailId = Util.generateTableIdByDB(UtilConst.TABLE_KEY_TO_INPUT_DETAIL, tmpBusinessId, 1);
			String inputId = Util.generateTableIdByDB(UtilConst.TABLE_KEY_TO_INPUT, tmpBusinessId, 1);
			Date now = new Date(new java.util.Date().getTime());
			inputdetailModel.setDetailId(inputDetailId);
			inputdetailModel.setInputId(inputId);
			inputdetailModel.setNewDate(now);
			inputdetailModel.setUpdateDate(now);
			inputdetailModel.setShipId(inputdetailModel.getEntryId());
			int ok = service.saveNewInputDetail(inputdetailModel);
			if (ok > 0) {
				return "ok";
			} else if (ok == -1) {
				return "exist";
			} else {
				return "fail";
			}
		}
	}
	
	/**
	 * 运营商-删除入库信息
	 * @author zhangzy
	 */
	@RequestMapping(value = "/a/delete/input/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String wDeleteInput(Model model, HttpSession session, @PathVariable(required = true) int id) {
		//逻辑删除入库总表信息
		int ok = service.deleteInputById(id);
		if (ok > 0) {
			return "ok";
		} else {
			return "fail";
		}
	}
	

	/**
	 * 商家-查询入库列表
	 * 
	 * @author zhangzy
	 * 
	 * @param model
	 * @param session
	 * @param inputId
	 * 
	 *            (model中已标识以下字段信息)
	 * @param sku
	 * @param barcode
	 * @param fNewDate
	 * @param lNewDate
	 * @return
	 */
	@RequestMapping(value = "/b/input/list")
	public String cInputList(Model model, HttpSession session, @RequestParam(required = false) String searchFlag,
			@RequestParam(required = false) String inputId, @RequestParam(required = false) String sku,
			@RequestParam(required = false) String barcode, @RequestParam(required = false) String goodsName) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			//参数组装
			IputModel iputModel = new IputModel();
			iputModel.setBusinessId(this.myBusiness_id);
			iputModel.setSearchFlag(searchFlag);
			iputModel.setInputId(inputId);
			iputModel.setSku(sku);
			iputModel.setGoodsName(goodsName);
			iputModel.setBarcode(barcode);
			//查询入库列表
			List<IputModel> _inputList = service.findInputAll(iputModel);
			model.addAttribute("item", _inputList);
			model.addAttribute("nav_active1", 4);
			if (Util.isEmpty(iputModel.getSearchFlag())) {
				return "/c/input/list";
			} else {
				return "/c/input/list::table_refresh";
			}
		}
	}

	/**
	 * 商家-查询入库详情列表
	 * @author zhangzy
	 * 
	 * @param model
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/b/input/detail")
	public String cInputDetail(Model model, HttpSession session, @RequestParam(required = false) String inputId) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			//查询入库详情列表
			List<InputdetailModel> _inputDetialsList = service.findDetailListById(inputId);
			if (_inputDetialsList == null) {
				_inputDetialsList = new ArrayList<InputdetailModel>();
			}
			model.addAttribute("item", _inputDetialsList);
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active1", 4);
			return "/c/input/detail";
		}
	}

	@Override
	protected String getPageId() {
		// TODO Auto-generated method stub
		return "test_user_page";
	}
}
