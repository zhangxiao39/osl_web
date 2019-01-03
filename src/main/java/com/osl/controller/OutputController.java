package com.osl.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.alibaba.fastjson.JSON;
import com.osl.common.Util;
import com.osl.common.UtilConst;
import com.osl.common.web.BaseController;
import com.osl.common.web.RedisUtils;
import com.osl.model.InputModel;
import com.osl.model.InputdetailModel;
import com.osl.model.OutputModel;
import com.osl.model.OutputdetailModel;
import com.osl.service.InputService;
import com.osl.service.OutputService;

@Controller
public class OutputController extends BaseController<OutputModel>{
	private static final Logger logger = LoggerFactory.getLogger(OutputController.class);

	@Autowired
	private OutputService service;

	@Autowired
	private RedisUtils redisUtils;
	

	@RequestMapping(value = "/b/output/request")
	public String b_outputRequest(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/business/login";
		} else {
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active1", 5);
			return "/c/output/request";
		}
	}
	
	/**
	 * 商家获取出库一览
	 * @author zhangzy
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/b/output/list")
	public String bOutputList(Model model, HttpSession session, @RequestParam(required = false) String params) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/business/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			// 参数组装
			if(params == null) {
				params = "{'status':-1}";
			}
			OutputModel outputModel = JSON.parseObject(params, OutputModel.class);
			outputModel.setBusinessId(this.myBusiness_id);
			outputModel.setWarehouseId(-1);
			// 查询入库列表
			List<OutputModel> _outputList = service.findOutputAll(outputModel);
			model.addAttribute("item", _outputList);
			model.addAttribute("nav_active1", 6);
			if (Util.isEmpty(outputModel.getSearchFlag())) {
				return "/c/output/list";
			} else {
				return "/c/output/list::table_refresh";
			}
		}
	}
	
	/**
	 * 商家获取出库详情列表
	 * @author zhangzy
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/b/output/detail")
	public String cOutputDetail(Model model, HttpSession session, @RequestParam(required = false) String params) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/business/login";
		} else {
			// 参数组装
			if(params == null) {
				params = "{'outputId':-1}";
			}
			OutputdetailModel outputdetailModel = JSON.parseObject(params, OutputdetailModel.class);
			// 查询入库详情列表
			List<OutputdetailModel> outputDetialsList = service.findDetailListById(outputdetailModel);
			if (outputDetialsList == null) {
				outputDetialsList = new ArrayList<OutputdetailModel>();
			}
			model.addAttribute("item", outputDetialsList);
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active1", 6);
			if (Util.isEmpty(outputdetailModel.getSearchFlag())) {
				return "/c/output/detail";
			} else {
				return "/c/output/detail::table_refresh";
			}
		}
	}
	
	/**
	 * 商家-删除出库信息
	 * 
	 * @author zhangzy
	 */
	@RequestMapping(value = "/b/delete/output/{outputId}", method = RequestMethod.DELETE)
	@ResponseBody
	public String cDeleteOutput(Model model, HttpSession session, @PathVariable(required = true) String outputId) {
		// 逻辑删除入库总表信息
		int ok = service.deleteOutputById(outputId);
		if (ok > 0) {
			return "ok";
		} else {
			return "fail";
		}
	}
	
	/**
	 * 商家-删除出库详情信息
	 * 
	 * @author zhangzy
	 */
	@RequestMapping(value = "/b/delete/output/detail/{detailId}", method = RequestMethod.DELETE)
	@ResponseBody
	public String cDeleteOutputDetail(Model model, HttpSession session, @PathVariable(required = true) String detailId) {
		// 逻辑删除入库总表信息
		int ok = service.deleteOutputDetailByDetailId(detailId);
		if (ok > 0) {
			return "ok";
		} else {
			return "fail";
		}
	}
	
	/**
	 * 商家-取消出库信息
	 * 
	 * @author zhangzy
	 */
	@RequestMapping(value = "/b/cancel/output/{outputId}", method = RequestMethod.DELETE)
	@ResponseBody
	public String cCancelOutput(Model model, HttpSession session, @PathVariable(required = true) String outputId) {
		// 逻辑删除入库总表信息
		int ok = service.cancelOutputById(outputId);
		if (ok > 0) {
			return "ok";
		} else {
			return "fail";
		}
	}
	
	/**
	 * 商家-取消出库详情信息
	 * 
	 * @author zhangzy
	 */
	@RequestMapping(value = "/b/cancel/output/detail/{detailId}", method = RequestMethod.DELETE)
	@ResponseBody
	public String cCancelOutputDetail(Model model, HttpSession session, @PathVariable(required = true) String detailId) {
		// 逻辑删除入库总表信息
		int ok = service.cancelOutputDetailByDetailId(detailId);
		if (ok > 0) {
			return "ok";
		} else {
			return "fail";
		}
	}
	
	/**
	 * 商家-查询出库详情对象
	 * 
	 * @author zhangzy
	 */
	@RequestMapping(value = "/b/outputDetail/detail/{detailId}", method = RequestMethod.POST)
	@ResponseBody
	public OutputdetailModel wInputDetail(Model model, HttpSession session,
			@PathVariable(required = true) String detailId) {
		// 查询出库详情
		OutputdetailModel inputdetailModel = service.findById(detailId);
		return inputdetailModel;
	}
	
	/**
	 * 商家-修改出库详情数量信息
	 * 
	 * @author zhangzy
	 */
	@RequestMapping(value = "/b/save/outputdetail", method = RequestMethod.POST)
	@ResponseBody
	public String cUpdateOutputDetail(Model model, HttpSession session, OutputdetailModel outputdetailModel) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			Timestamp tmpTimestamp = new Timestamp(new Date().getTime());
			int ok = 0;
			outputdetailModel.setUpdateDate(tmpTimestamp);
			ok = service.updateOutputDetail(outputdetailModel);
			if (ok > 0) {
				return "ok";
			} else if (ok == -1) {
				return "exist";
			} else if (ok == 2) {
				return "overflow";
			} else {
				return "fail";
			}
		}
	}
	
	/**
	 * 运营商获取出库一览
	 * 
	 * @author zhangzy
	 * @param model
	 * @param session
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/a/output/list")
	public String wOutputList(Model model, HttpSession session, @RequestParam(required = false) String params) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/business/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			// 参数组装
			if(params == null) {
				params = "{'status':-1 , 'businessId':-1}";
			}
			OutputModel outputModel = JSON.parseObject(params, OutputModel.class);
			outputModel.setWarehouseId(this.myBusiness_id);
			// 查询入库列表
			List<OutputModel> _outputList = service.findOutputAll(outputModel);
			model.addAttribute("item", _outputList);
			model.addAttribute("nav_active1", 6);
			if (Util.isEmpty(outputModel.getSearchFlag())) {
				return "/w/output/list";
			} else {
				return "/w/output/list::table_refresh";
			}
		}
	}
	
	/**
	 * 运营商获取出库详情列表
	 * @author zhangzy
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/a/output/detail")
	public String wOutputDetail(Model model, HttpSession session, @RequestParam(required = false) String params) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/business/login";
		} else {
			// 参数组装
			if(params == null) {
				params = "{'outputId':-1}";
			}
			OutputdetailModel outputdetailModel = JSON.parseObject(params, OutputdetailModel.class);
			// 查询入库详情列表
			List<OutputdetailModel> outputDetialsList = service.findDetailListById(outputdetailModel);
			if (outputDetialsList == null) {
				outputDetialsList = new ArrayList<OutputdetailModel>();
			}
			model.addAttribute("item", outputDetialsList);
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active1", 6);
			if (Util.isEmpty(outputdetailModel.getSearchFlag())) {
				return "/w/output/detail";
			} else {
				return "/w/output/detail::table_refresh";
			}
		}
	}
	
	/**
	 * 运营商-修改出库详情数量信息
	 * 
	 * @author zhangzy
	 */
	@RequestMapping(value = "/a/save/outputdetail", method = RequestMethod.POST)
	@ResponseBody
	public String wUpdateOutputDetail(Model model, HttpSession session, OutputdetailModel outputdetailModel) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			Timestamp tmpTimestamp = new Timestamp(new Date().getTime());
			int ok = 0;
			outputdetailModel.setUpdateDate(tmpTimestamp);
			ok = service.updateOutputDetail(outputdetailModel);
			if (ok > 0) {
				return "ok";
			} else if (ok == -1) {
				return "exist";
			} else {
				return "fail";
			}
		}
	}
	

	@Override
	protected String getPageId() {
		// TODO Auto-generated method stub
		return "test_user_page";
	}
}
