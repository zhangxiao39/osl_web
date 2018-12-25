package com.osl.controller;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.osl.common.ExportUtil;
import com.osl.common.Util;
import com.osl.common.UtilConst;
import com.osl.common.web.BaseController;
import com.osl.common.web.RedisUtils;
import com.osl.model.InputdetailModel;
import com.osl.model.InputModel;
import com.osl.service.InputService;

@Controller
public class InputController extends BaseController<InputdetailModel> {
	private static final Logger logger = LoggerFactory.getLogger(InputController.class);

	@Autowired
	private InputService service;

	@Autowired
	private RedisUtils redisUtils;

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
	public String wInputList(Model model, HttpSession session, @RequestParam(required = false) String params) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			// 参数组装
			//InputModel iputModel = new InputModel();
			if(params == null) {
				params = "{}";
			}
			InputModel inputModel = JSON.parseObject(params, InputModel.class);
			inputModel.setWarehouseId(this.myBusiness_id);
			// 查询入库列表
			List<InputModel> _inputList = service.findInputAll(inputModel);
			model.addAttribute("item", _inputList);
			model.addAttribute("nav_active1", 4);
			if (Util.isEmpty(inputModel.getSearchFlag())) {
				return "/w/input/list";
			} else {
				return "/w/input/list::table_refresh";
			}
		}
	}

	/**
	 * 运营商-查询入库详情一览页
	 * 
	 * @author zhangzy
	 */
	@RequestMapping(value = "/a/input/detail")
	public String wInputDetailList(Model model, HttpSession session, @RequestParam(required = false) String inputId) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			// 查询入库详情列表
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
	 * 运营商-查询入库详情
	 * 
	 * @author zhangzy
	 */
	@RequestMapping(value = "/a/inputDetail/detail/{detailId}", method = RequestMethod.POST)
	@ResponseBody
	public InputdetailModel wInputDetail(Model model, HttpSession session,
			@PathVariable(required = true) String detailId) {
		// 查询入库详情
		InputdetailModel inputdetailModel = service.findById(detailId);
		return inputdetailModel;
	}

	/**
	 * 运营商-新建入库信息
	 * 
	 * @author zhangzy
	 */
	@RequestMapping(value = "/a/save/inputdetail", method = RequestMethod.POST)
	@ResponseBody
	public String wSaveNewInput(Model model, HttpSession session, InputdetailModel inputdetailModel) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			Timestamp tmpTimestamp = new Timestamp(new Date().getTime());
			int ok = 0;
			int tmpBusinessId = Integer.valueOf(session.getAttribute("u_bid").toString());
			inputdetailModel.setWarehouseId(tmpBusinessId);
			if (Util.isEmpty(inputdetailModel.getDetailId())) {
				String inputDetailId = Util.generateTableIdByRedis(redisUtils, UtilConst.TABLE_KEY_TO_INPUT_DETAIL,
						tmpBusinessId);
				String inputId = Util.generateTableIdByRedis(redisUtils, UtilConst.TABLE_KEY_TO_INPUT, tmpBusinessId);
				inputdetailModel.setDetailId(inputDetailId);
				inputdetailModel.setInputId(inputId);
				inputdetailModel.setNewDate(tmpTimestamp);
				inputdetailModel.setUpdateDate(tmpTimestamp);
				inputdetailModel.setShipId(inputdetailModel.getEntryId());
				ok = service.saveNewInputDetail(inputdetailModel);
			} else {
				ok = service.updateInputDetail(inputdetailModel);
			}
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
	 * 运营商-删除入库详情信息
	 * 
	 * @author zhangzy
	 */
	@RequestMapping(value = "/a/delete/detail/{detailId}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteInputDetail(Model model, HttpSession session, @PathVariable(required = true) String detailId) {
		InputdetailModel newModel = new InputdetailModel();
		newModel.setDetailId(detailId);
		// 逻辑删除入库总表信息
		int ok = service.deleteInputDetail(newModel);
		if (ok > 0) {
			return "ok";
		} else {
			return "fail";
		}
	}

	/**
	 * 运营商-删除入库信息
	 * 
	 * @author zhangzy
	 */
	@RequestMapping(value = "/a/delete/input/{inputId}", method = RequestMethod.DELETE)
	@ResponseBody
	public String wDeleteInput(Model model, HttpSession session, @PathVariable(required = true) String inputId) {
		// 逻辑删除入库总表信息
		int ok = service.deleteInputById(inputId);
		if (ok > 0) {
			return "ok";
		} else {
			return "fail";
		}
	}

	/**
	 * 导出入库信息
	 * @author zhangzy
	 */
	@RequestMapping(value = "/all/export/input" ,method = RequestMethod.GET)
	public void exportInput(Model model, HttpSession session, HttpServletResponse response,
			@RequestParam String params) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String firstday, lastday;
		// 获取前月的第一天
		Calendar cale = Calendar.getInstance();
		cale.add(Calendar.MONTH, 0);
		cale.set(Calendar.DAY_OF_MONTH, 1);
		firstday = format.format(cale.getTime());
		// 获取前月的最后一天
		cale = Calendar.getInstance();
		cale.add(Calendar.MONTH, 1);
		cale.set(Calendar.DAY_OF_MONTH, 0);
		lastday = format.format(cale.getTime());
		List<Map<String, Object>> dataList = null;
		InputModel inputModel = JSON.parseObject(params, InputModel.class);
		inputModel.setStartNewDate(firstday);
		inputModel.setEndNewDate(lastday);
		List<InputModel> _inputList = service.findInputAll(inputModel);
		String sTitle = "商家,入库编号,入库日期,商品类型数量,商品总数量,状态";
		String fName = "input_";
		String mapKey = "businessName,inputId,inputTime,skuNums,goodsNums,status";
		dataList = new ArrayList<>();
		Map<String, Object> map = null;
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (InputModel tmpInputModel : _inputList) {
			map = new HashMap<String, Object>();
			map.put("businessName", tmpInputModel.getBusinessName());
			map.put("inputId", tmpInputModel.getInputId());
			map.put("inputTime", format.format(tmpInputModel.getInputTime()));
			map.put("skuNums", tmpInputModel.getSkuNums());
			map.put("goodsNums", tmpInputModel.getGoodsNums());
			map.put("status", tmpInputModel.getStatus() == 1 ? "入库完了" : "入库中");
			dataList.add(map);
		}
		try (final OutputStream os = response.getOutputStream()) {
			ExportUtil.responseSetProperties(fName, response);
			ExportUtil.doExport(dataList, sTitle, mapKey, os);
		} catch (Exception e) {
			logger.error("生成CSV失败", e);
		}
	}
	
	/**
	 * 导出入库详情信息
	 * @author zhangzy
	 */
	@RequestMapping(value = "/all/export/inputDetail" ,method = RequestMethod.GET)
	public void exportInputDetail(Model model, HttpSession session, HttpServletResponse response,
			@RequestParam String params) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Map<String, Object>> dataList = null;
		InputdetailModel inputDetailModel = JSON.parseObject(params, InputdetailModel.class);
		List<InputdetailModel> inputdetailModelList = service.findDetailListById(inputDetailModel.getInputId());
		String sTitle = "商家,明细编号,SKU,分类,名称,入库数量,仓库位置,入库编号,商品类型,入库类型,有效期,状态";
		String fName = "input_detail_";
		String mapKey = "businessName,detailId,sku,goodscategoryName,goodsName,nums,depotAddress,inputId,goodsType,type,validityTime,status";
		dataList = new ArrayList<>();
		Map<String, Object> map = null;
		for (InputdetailModel tmpInputdetailModel : inputdetailModelList) {
			map = new HashMap<String, Object>();
			map.put("businessName", tmpInputdetailModel.getBusinessName());
			map.put("detailId", tmpInputdetailModel.getDetailId());
			map.put("sku", tmpInputdetailModel.getSku());
			map.put("goodscategoryName", tmpInputdetailModel.getGoodscategoryName());
			map.put("goodsName", tmpInputdetailModel.getGoodsName());
			map.put("nums", tmpInputdetailModel.getNums());
			map.put("depotAddress", tmpInputdetailModel.getDepotAddress());
			map.put("inputId", tmpInputdetailModel.getInputId());
			map.put("goodsType", tmpInputdetailModel.getGoodsType());
			map.put("type", tmpInputdetailModel.getType());
			map.put("validityTime", format.format(tmpInputdetailModel.getValidityTime()));
			map.put("status", tmpInputdetailModel.getStatus() == 1 ? "入库完了" : "入库中");
			dataList.add(map);
		}
		try (final OutputStream os = response.getOutputStream()) {
			ExportUtil.responseSetProperties(fName, response);
			ExportUtil.doExport(dataList, sTitle, mapKey, os);
		} catch (Exception e) {
			logger.error("生成CSV失败", e);
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
	public String cInputList(Model model, HttpSession session, @RequestParam(required = false) String params) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			// 参数组装
			//InputModel iputModel = new InputModel();
			if(params == null) {
				params = "{}";
			}
			InputModel inputModel = JSON.parseObject(params, InputModel.class);
			inputModel.setBusinessId(this.myBusiness_id);
			// 查询入库列表
			List<InputModel> _inputList = service.findInputAll(inputModel);
			model.addAttribute("item", _inputList);
			model.addAttribute("nav_active1", 4);
			if (Util.isEmpty(inputModel.getSearchFlag())) {
				return "/c/input/list";
			} else {
				return "/c/input/list::table_refresh";
			}
		}
	}

	/**
	 * 商家-查询入库详情列表
	 * 
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
			// 查询入库详情列表
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
