package com.osl.controller;

import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.osl.common.ExportUtil;
import com.osl.common.Util;
import com.osl.common.UtilConst;
import com.osl.common.web.BaseController;
import com.osl.common.web.RedisUtils;
import com.osl.mapper.entity.StockEntity;
import com.osl.model.StockModel;
import com.osl.service.StockService;


@Controller
public class StockController extends BaseController<StockModel> {
	
	
	@Autowired
	private StockService service;
	
	@Autowired
	private RedisUtils redisUtils;
	
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
				@RequestParam(required = false) int status,
				@RequestParam(required = false) int goodsCategoryId,
				@RequestParam(required = false) int businessId) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			StockModel stockModel = new StockModel();
			stockModel.setWarehouseId(this.myBusiness_id);
			stockModel.setSku(sku);
			stockModel.setGoodsName(name);
			stockModel.setNums((!nums.equals("")&&nums!=null)?Integer.parseInt(nums):0);
			stockModel.setBarCode(barCode);
			stockModel.setBusinessId(businessId);
			stockModel.setCategoryId(goodsCategoryId);
			stockModel.setGoodsType(status);
			List<String> lis = Arrays.asList(sku.split(","));	//将拼接好的sku字符串转换成list
			List<StockModel> _stockList = service.find_adminStock_by_condition(lis,stockModel);
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
	
	/*
	 * @des:条件库存商品列表
	 * @author：sun-hongyu
	 * @date:2018-12-20
	 * @param:
	 * @return:list<StockModel>
	 */
	@RequestMapping(value = "/a/stockListConditon", method = RequestMethod.POST)
	@ResponseBody
	public List<StockModel> getStockListByCondition(HttpSession session,String goodsId,String inputDetailId,int goodsType) {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			StockModel stock = new StockModel();
			stock.setGoodsId(goodsId);
			stock.setInputDetailId(inputDetailId);
			stock.setGoodsType(goodsType);
			stock.setWarehouseId(this.myBusiness_id);
			List<StockModel> stockList = service.find_stock_detail(stock);
			return stockList;
	}
	
	
	/*
	 * @des:运营商端，获取库存详情
	 * @author：sun-hongyu
	 * @date:2018-12-18
	 * @param:
	 * @return:list<StockModel>
	 */
	@RequestMapping(value = "/a/stock/stockDetail/{goodsId}")
	public String wStockDetail(Model model, HttpSession session,@PathVariable(required = true) String goodsId) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			this.myBusiness_id =Integer.valueOf(session.getAttribute("u_bid").toString());
			StockModel stockModel = new StockModel();
			stockModel.setWarehouseId(myBusiness_id);
			stockModel.setGoodsId(goodsId);
			stockModel.setGoodsType(-1);
			List<StockModel> _stockList = service.find_stock_detail(stockModel);
			model.addAttribute("item", _stockList);
			model.addAttribute("nav_active1", 1);
			return "/w/stock/stockDetail";
		}
	}
	
	
	/*
	 * @des:运营商端，条件查询库存详情
	 * @author：sun-hongyu
	 * @date:2018-12-18
	 * @param:
	 * @return:list<StockModel>
	 */
	@RequestMapping(value = "/a/stock/adminStockDetailCondition")
	public String wStockDetailByCondition(Model model, HttpSession session,@RequestParam(required = false) String goodsId,
				@RequestParam(required = false) String manageId,
				@RequestParam(required = false) String shevelsId,
				@RequestParam(required = false) String nums,
				@RequestParam(required = false) String startDate,
				@RequestParam(required = false) String endDate) throws ParseException {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			StockModel stockModel = new StockModel();
			stockModel.setGoodsId(goodsId);
			stockModel.setWarehouseId(myBusiness_id);
			stockModel.setManageId(manageId);
			stockModel.setShelvesId(shevelsId);
			stockModel.setNums((!nums.equals("")&&nums!=null)?Integer.parseInt(nums):0);
			if(null!=startDate&&!startDate.equals(""))
			{
				stockModel.setStartDate(new Timestamp(sdf.parse(startDate).getTime()));
			}
			if(null!=endDate&&!endDate.equals(""))
			{
				stockModel.setEndDate(new Timestamp(sdf.parse(endDate).getTime()));
			}
			List<StockModel> _stockList = service.find_stock_detail_condition(stockModel);
			model.addAttribute("item", _stockList);
			model.addAttribute("nav_active1", 1);
			return "/w/stock/stockDetail::table_refresh";
		}
	}
	
	/*
	 * @des:运营商端，添加库存
	 * @author：sun-hongyu
	 * @date:2018-12-17
	 * @param:
	 * @return:int
	 */
	@RequestMapping(value = "/a/stock/insertStock", method = RequestMethod.POST)
	@ResponseBody
	public String addStock(HttpSession session, String manageId,int nums,int goodsType) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			if (null != manageId) {
				this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
				//获取对应的商品信息
				StockModel condition = new StockModel();
				condition.setManageId(manageId);
				StockModel stock  = service.find_stock_detail_condition(condition).get(0);
				StockEntity newEntity = new StockEntity();
				newEntity.setManageId(Util.generateTableIdByRedis(redisUtils,UtilConst.TABLE_KEY_TO_STOCK, this.myBusiness_id));
				newEntity.setDepotId(stock.getDepotId());
				newEntity.setShelvesId(stock.getShelvesId());
				newEntity.setGoodsId(stock.getGoodsId());
				newEntity.setNums(nums);
				newEntity.setGoodsType(goodsType);
				newEntity.setInputDetailId(stock.getInputDetailId());
				newEntity.setInputTime(stock.getInputTime());
				newEntity.setNewDate(new Timestamp( new Date().getTime()) );
				newEntity.setProduceTime(stock.getProduceTime());
				newEntity.setWarehouseId(stock.getWarehouseId());
				newEntity.setBusinessId(stock.getBusinessId());
				newEntity.setValidityTime(stock.getValidityTime());
				newEntity.setVolumn(stock.getVolumn());
				int ok = service.inset_stock(newEntity);
				if (ok > 0) {
					return "ok";
				} else if (ok == -1) {
					return "exist";
				} else {
					return "fail";
				}
			} else {
				return "fail";
			}
		}

	}
	
	/*
	 * @des:运营商端，更新库存数量
	 * @author：sun-hongyu
	 * @date:2018-12-17
	 * @param:manageId,nums
	 * @return:int
	 */
	@RequestMapping(value = "/a/stock/updateStock", method = RequestMethod.POST)
	@ResponseBody
	public String updateStock(String manageId,int nums) {
		if (manageId != null) {
			int ok = service.update_stock_by_id(manageId,nums);
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
	 * 	根据id获取库存信息
	 */
	@RequestMapping(value = "/osl/stock/getStockById", method = RequestMethod.POST)
	@ResponseBody
	public StockModel getCategoryMin(HttpSession session,@RequestParam(required = false) String manageId) {
			StockModel model = new StockModel();
			model.setManageId(manageId);
			StockModel stock = service.find_stock_detail_condition(model).get(0);
			return stock;
	}
	
	
	/*
	 * @des:商家端导出
	 */
	/**
	 * 导出入库信息
	 */
	@RequestMapping(value = "/b/export/bStock" ,method = RequestMethod.GET)
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
		String sTitle = "商家,入库编号,入库日期,商品类型数量,商品总数量,状态";
		String fName = "input_";
		String mapKey = "businessName,inputId,inputTime,skuNums,goodsNums,status";
		dataList = new ArrayList();
		Map<String, Object> map = null;
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		for (InputModel tmpInputModel : _inputList) {
//			map = new HashMap<String, Object>();
//			map.put("businessName", tmpInputModel.getBusinessName());
//			map.put("inputId", tmpInputModel.getInputId());
//			map.put("inputTime", format.format(tmpInputModel.getInputTime()));
//			map.put("skuNums", tmpInputModel.getSkuNums());
//			map.put("goodsNums", tmpInputModel.getGoodsNums());
//			map.put("status", tmpInputModel.getStatus() == 1 ? "入库完了" : "入库中");
//			dataList.add(map);
//		}
		try (final OutputStream os = response.getOutputStream()) {
			ExportUtil.responseSetProperties(fName, response);
			ExportUtil.doExport(dataList, sTitle, mapKey, os);
		} catch (Exception e) {
//			logger.error("生成CSV失败", e);
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
