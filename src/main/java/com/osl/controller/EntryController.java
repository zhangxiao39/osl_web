package com.osl.controller;

import java.io.FileReader;
import com.osl.common.Util;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.opencsv.CSVReader;
import com.osl.common.web.BaseController;
import com.osl.mapper.entity.EntrydetailEntity;
import com.osl.model.EntryModel;
import com.osl.model.GoodsModel;
import com.osl.model.StockModel;
import com.osl.service.EntryDetailService;
import com.osl.service.EntryService;
import com.osl.service.TestUserService;

@Controller
public class EntryController extends BaseController<EntryModel> {
	
	@Autowired
	private EntryService service;
	@Autowired
	private EntryDetailService detailService;
	
	/*
	 * @author:sun-hongyu
	 * @date:2018-12-28
	 * @des:纳品申请
	 * @param:
	 * @return：
	 * 	
	 */
	@RequestMapping(value = "/b/entry/apply")
	public String b_entrys_Apply(Model model, HttpSession session) {
//		if (session.getAttribute("u_login") == null) {
//			return "redirect:/business/login";
//		} else {
//			model.addAttribute("uname", session.getAttribute("u_login"));
//			model.addAttribute("bname", session.getAttribute("u_bname"));
//			model.addAttribute("burl", session.getAttribute("u_burl"));
//			model.addAttribute("nav_active1", 2);
//			return "/c/entry/apply";
//		}
		model.addAttribute("uname", session.getAttribute("u_login"));
		model.addAttribute("bname", session.getAttribute("u_bname"));
		model.addAttribute("burl", session.getAttribute("u_burl"));
		model.addAttribute("nav_active1", 2);
		return "/c/entry/apply";
	}
	

	@RequestMapping(value = "/b/entry/apply/upload")
	public String b_entrys_Apply_upload(Model model, HttpSession session, EntryModel userModel) {
		model.addAttribute("uname", session.getAttribute("u_login"));
		model.addAttribute("bname", session.getAttribute("u_bname"));
		model.addAttribute("burl", session.getAttribute("u_burl"));
		model.addAttribute("nav_active1", 2);
		System.out.println(userModel.getApplyFile().getOriginalFilename());
		
		try {
			Reader resder = new InputStreamReader(userModel.getApplyFile().getInputStream(), "gbk");
			CSVReader csvresder = new CSVReader(resder);
			String[] strs;
			while ((strs = csvresder.readNext()) != null) {
				System.out.println(Arrays.deepToString(strs));
			}
			csvresder.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.entryUpload(userModel);
		
		return "/c/entry/apply";
	}

	/*
	 * @author:sun-hongyu
	 * @date:2018-12-28
	 * @des:纳品列表一览
	 * @param:
	 * @return：
	 * 	
	 */
	@RequestMapping(value = "/b/entry/list")
	public String bEntrysList(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/business/login";
		} else {
			if (session.getAttribute("u_login") == null) {
				return "redirect:/admin/login";
			} else {
				this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
				List<EntryModel> entryList = service.bQueryEntryListByBusinessId(this.myBusiness_id);
				model.addAttribute("item", entryList);
				model.addAttribute("nav_active1", 3);
				return "/c/entry/list";
			}
			
		}
	}
	/*
	 * @des:【商家端】，条件查询纳品一览
	 * @author：sun-hongyu
	 * @date:2018-12-10
	 * @param:
	 * @return:list<StockModel>
	 */
	@RequestMapping(value = "b/entry/listCondition")
	public String bEntryListCondition(Model model, HttpSession session,@RequestParam(required = false) String params) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			EntryModel entry = JSON.parseObject(params,EntryModel.class);
			entry.setBusinessId(this.myBusiness_id);
			//拆解entryId列表
			List<String> entryIdLis = new ArrayList<String>();
			if(entry.getEntruIdStr()!=null && !entry.getEntruIdStr().equals(""))
			{
				entryIdLis =Arrays.asList(entry.getEntruIdStr().split(","));	//将拼接好的sku字符串转换成list
			}
			entry.setEntruIdList(entryIdLis);
			//拆解sku列表
			List<String> skuLis = new ArrayList<String>();
			if(entry.getSkuStr()!=null && !entry.getSkuStr().equals(""))
			{
				skuLis =Arrays.asList(entry.getSkuStr().split(","));	//将拼接好的sku字符串转换成list
			}
			entry.setSkuList(skuLis);
			List<EntryModel> entryList = service.bQueryEntryListByCondition(entry);
			model.addAttribute("item", entryList);
			model.addAttribute("nav_active1", 3);
			return "/c/entry/list::table_refresh";
		}
	}
	
	/*
	 * @des:【商家端】，取消纳品
	 * @author：sun-hongyu
	 * @date:2019-01-01
	 * @param:
	 * @return:int
	 */
	@RequestMapping(value = "/b/entry/updateEntryStatus", method = RequestMethod.POST)
	@ResponseBody
	public String bUpdateEntryStatus(String entryId) {
		if (!Util.isEmpty(entryId)) {
			int ok1 = service.bupdateEntryStatusById(entryId);
			if (ok1 > 0) {
				int ok2 = detailService.bupdateStatusByEntryId(entryId);
				if(ok2>0)
				{
					return "ok";
				}
//				return "ok";
				else {
					return "fail";
				}
			} else {
				return "fail";
			}
		} else {
			return "fail";
		}
	}
	
	/**
	 * @des 获取商家的所有商品
	 * @param session
	 * @return List<GoodsModel>
	 */
	@RequestMapping(value = "/osl/entry/entryList", method = RequestMethod.POST)
	@ResponseBody
	public List<EntryModel> getBusinessEntryList(HttpSession session) {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			List<EntryModel> list = service.bQueryEntryListByBusinessId(this.myBusiness_id);
			return list;
	}
	@RequestMapping(value = "/b/entry/detail/{id}")
	public String b_entrys_Detail(Model model, HttpSession session, @PathVariable(required = false) String id) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/business/login";
		} else {
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active1", 3);
			return "/c/entry/detail";
		}
	}

	@RequestMapping(value = "/a/entry/list")
	public String w_entrys_List(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active1", 3);
			return "/w/entry/list";
		}
	}

	@RequestMapping(value = "/a/entry/detail/{bid}/{eid}")
	public String w_entrys_Detail(Model model, HttpSession session, @PathVariable(required = false) String bid,
			@PathVariable(required = false) String eid) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/business/login";
		} else {
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active1", 3);
			return "/w/entry/detail";
		}
	}
	
	/**
	 * ajax请求，为入库时，查询还未入库的纳品信息
	 * 按照纳品ID 分组，纳品详细信息作为子元素的Map结构
	 * 返回前台，展现于search 控件中，用于查询展示
	 * 
	 * @author zhangzy
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/a/search/entiydetail/{status}" ,  method = RequestMethod.POST)
	@ResponseBody
	public Map<String , List<EntrydetailEntity>> wEntryDetailBySearch(Model model, HttpSession session, @PathVariable(required = true) int status) {
		Map<String , List<EntrydetailEntity>> map = new HashMap<String , List<EntrydetailEntity>>();
		List<EntrydetailEntity> returnList = new ArrayList<EntrydetailEntity>();
		//按照查询纳品详细信息列表
		List<EntrydetailEntity> tmpList = service.queryEntrydetailEntityListByStatus(status);
		//重写返回的map，按照纳品分组
		for(EntrydetailEntity tmpEntry : tmpList) {
			if(map.get(tmpEntry.getEntryId()) == null) {
				//如果当前纳品id在map中没有对象，则新建当前纳品id对应的返回纳品详情list对象
				returnList = new ArrayList<EntrydetailEntity>();
			}else {
				//如果map有对象，则将当前纳品id对应的纳品详情list实例
				returnList = map.get(tmpEntry.getEntryId());
			}
			//增加纳品详情list实例
			returnList.add(tmpEntry);
			//更新当前纳品id对应的信息
			map.put(tmpEntry.getEntryId(), returnList);
		}
		return map;
	}
	
	/**
	 * 按照纳品详细ID 查询纳品详细对象
	 * 返回前台展示
	 *  
	 * @author zhangzy
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/a/entiydetail/{detailId}" ,  method = RequestMethod.POST)
	@ResponseBody
	public EntrydetailEntity wEntryDetailByDetailId(Model model, HttpSession session, @PathVariable(required = true) String detailId) {
		//按照查询纳品详细信息对象
		EntrydetailEntity entrydetailEntity = service.queryEntrydetailEntityByDetailId(detailId);
		return entrydetailEntity;
	}

	@Override
	protected String getPageId() {
		// TODO Auto-generated method stub
		return "entry_page_id";
	}

}
