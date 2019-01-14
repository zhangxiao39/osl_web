package com.osl.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.osl.common.Util;
import com.osl.common.UtilConst;
import com.osl.common.web.BaseController;
import com.osl.common.web.RedisUtils;
import com.osl.model.EntryModel;
import com.osl.model.EntrydetailModel;
import com.osl.service.EntryDetailService;
import com.osl.service.EntryService;

@Controller
public class EntryDetailController extends BaseController<EntrydetailModel> {
	
	@Autowired
	private EntryDetailService service;
	
	@Autowired
	private EntryService enteryService;
	
	@Autowired
	private RedisUtils redisUtils;
	
	/*
	 * @des:【商家端】，根据纳品id条件查询纳品详情
	 * @author：sun-hongyu
	 * @date:2019-01-02
	 * @param:
	 * @return:list<StockModel>
	 */
	@RequestMapping(value = "/b/entry/detailList/{entryId}")
	public String bEntryDetailList(Model model, HttpSession session,@PathVariable(required = true) String entryId) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			List<EntrydetailModel> detailList = service.bQueryEntryDetailListByEntryId(entryId);
			model.addAttribute("nav_active1", 3);
			model.addAttribute("item", detailList);
			return "/c/entry/detail";
		}
	}
	
	/*
	 * @des:【商家端】，取消纳品详情
	 * @author：sun-hongyu
	 * @date:2019-01-02
	 * @param:
	 * @return:int
	 */
	@RequestMapping(value = "/b/entry/updateEntryDetailStatus", method = RequestMethod.POST)
	@ResponseBody
	public String bUpdateEntryDetailStatus(String entryId) {
		if (!Util.isEmpty(entryId)) {
			EntrydetailModel entryDetailModel = new EntrydetailModel();
			entryDetailModel.setEntryId(entryId);
			entryDetailModel.setStatus(UtilConst.ENTRY_STATUS_CANCLE);
			int ok1 = service.bupdateStatusByEntryId(entryDetailModel);
			if (ok1 > 0) {
				return "ok";
			} else {
				return "fail";
			}
		} else {
			return "fail";
		}
	}

	/*
	 * @des:【商家端】，逻辑删除纳品详情
	 * @author：sun-hongyu
	 * @date:2019-01-02
	 * @param:
	 * @return:int
	 */
	@RequestMapping(value = "/b/entryDetail/delete", method = RequestMethod.POST)
	@ResponseBody
	public String bDeleteEntryDetail(String entryDetailId) {
		if (entryDetailId != null) {
			int ok = service.bDeleteEntryDetailById(entryDetailId);
			if (ok > 0) {
				return "ok";
			} else {
				return "fail";
			}
		} else {
			return "fail";
		}
	}
	
	/*
	 * @des:【商家端】，物理删除纳品详情
	 * @author：sun-hongyu
	 * @date:2019-01-03
	 * @param:
	 * @return:int
	 */
	@RequestMapping(value = "/b/entryDetail/physicsDelete", method = RequestMethod.POST)
	@ResponseBody
	public String bPhysicsDeleteEntryDetail(HttpSession session,String entryDetailId) {
		if (entryDetailId != null) {
			//获取对应的纳品详情信息
			EntrydetailModel param1 = new EntrydetailModel();
			param1.setDetailId(entryDetailId);
			EntrydetailModel entryDetailModel = service.bQueryEntryDetailListByCondition(param1).get(0);
			//获取对应的纳品信息
			EntryModel param2 = new EntryModel();
			param2.setEntryId(entryDetailModel.getEntryId());
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			param2.setBusinessId(this.myBusiness_id);
			param2.setStatus(entryDetailModel.getStatus());
			EntryModel entryModel = enteryService.bQueryEntryListByCondition(param2).get(0);
			
			//物理删除‘纳品详情表中的数据’
			int ok = service.bPhysicsDeleteEntryDetailById(entryDetailId);
			if (ok > 0) {
				//根据删除的纳品详情信息，重新更新纳品信息
				entryModel.setSkuNums(entryModel.getSkuNums()-1);
				entryModel.setInputSkuNums(entryModel.getInputSkuNums()-1);
				entryModel.setGoodsNums(entryModel.getGoodsNums()-entryDetailModel.getNums());
				entryModel.setInputGoodsNums(entryModel.getInputGoodsNums()-entryDetailModel.getInputNums());
				entryModel.setOper(session.getAttribute("u_bname").toString());
				//时间格式
				Timestamp updateTime =  new Timestamp(new Date().getTime());
				entryModel.setUpdateDate(updateTime);
				//更新数据库纳品表中sku_nums，以及goods_nums,oper,updata_date
				if(enteryService.bupdateEntryNums(entryModel)>0)
				{
					return "ok";
				}else {
					return "fail";
				}
				
			} else {
				return "fail";
			}
		} else {
			return "fail";
		}
	}

	/*
	 * @des:根据entryDetailId获取纳品详情一览
	 * @author：sun-hongyu
	 * @date:2019-01-03
	 * @param:id
	 * @return:EntrydetailModel
	 */
	@RequestMapping(value = "/b/entryDetail/getById", method = RequestMethod.POST)
	@ResponseBody
	public EntrydetailModel bGetEntrydetailModelById(HttpSession session,String entryDetailId)
	{
		EntrydetailModel param = new EntrydetailModel();
		param.setDetailId(entryDetailId);
		EntrydetailModel model = service.bQueryEntryDetailListByCondition(param).get(0);
		return model;
	}
	
	/*
	 * @des:【商家端】，根据纳品id条件查询纳品详情
	 * @author：sun-hongyu
	 * @date:2019-01-02
	 * @param:
	 * @return:list<StockModel>
	 */
	@RequestMapping(value = "b/entryDetail/getEntryDetaiListByEntryId/")
	@ResponseBody
	public List<EntrydetailModel> bEntryDetaiListByEntryId(Model model, HttpSession session,String entryId) {
		this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
		List<EntrydetailModel> detailList = service.bQueryEntryDetailListByEntryId(entryId);
		return detailList;
		
	}
	/*
	 * @des:【商家端】，更新纳品数量以及货运单号
	 * @author：sun-hongyu
	 * @date:2019-01-03
	 * @param:EntrydetailModel
	 * @return:EntrydetailModel
	 */
	@RequestMapping(value = "/b/entryDetail/updateInputNumsAndNumbers", method = RequestMethod.POST)
	@ResponseBody
	public String bUpdateEntryInputNumsAndSendId(HttpSession session,String params)
	{
		EntrydetailModel entryDetailModel = JSON.parseObject(params, EntrydetailModel.class);
		this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
		String oper = session.getAttribute("u_bname").toString();
		entryDetailModel.setBusinessId(this.myBusiness_id);
		entryDetailModel.setOper(oper);
		if("ok".equals(service.bupdateInputNumsAndNumbers(entryDetailModel)))
		{
			return "ok";
		}
		else {
			return "fail";
		}
	}
	
	/*
	 * @des:【商家端】，"申请中"，“商家承认完了”两种状态，商家追加纳品商品
	 * @author：sun-hongyu
	 * @date:2019-01-09
	 * @param:EntrydetailModel，newFlag
	 * @return:EntrydetailModel
	 */
	@RequestMapping(value = "/b/entryDetail/additionalEntryGoods", method = RequestMethod.POST)
	@ResponseBody
	public String bUpdateEntryInputNumsAndSendId(HttpSession session,String params,String newFlag)
	{
		EntrydetailModel entryDetailModel = JSON.parseObject(params, EntrydetailModel.class);
		this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
		String oper = session.getAttribute("u_bname").toString();
		entryDetailModel.setBusinessId(this.myBusiness_id);
		entryDetailModel.setOper(oper);
		//新追加商品
		if("yes".equals(newFlag))
		{
			entryDetailModel.setDetailId(Util.generateTableIdByRedis(redisUtils,UtilConst.TABLE_KEY_TO_INPUT_DETAIL, this.myBusiness_id));
		}
		return service.additionalEntryGoods(entryDetailModel,newFlag);
		
	}
	
	@Override
	protected String getPageId() {
		return null;
	}
	
	
}