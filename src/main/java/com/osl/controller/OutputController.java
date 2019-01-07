package com.osl.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import com.alibaba.fastjson.JSON;
import com.opencsv.CSVReader;
import com.osl.common.ExportUtil;
import com.osl.common.Util;
import com.osl.common.UtilDateTime;
import com.osl.common.web.BaseController;
import com.osl.common.web.RedisUtils;
import com.osl.model.CombinationModel;
import com.osl.model.GoodsModel;
import com.osl.model.OutputModel;
import com.osl.model.OutputdetailModel;
import com.osl.model.SellplatformModel;
import com.osl.model.SellshipModel;
import com.osl.model.StockModel;
import com.osl.service.CombinationService;
import com.osl.service.GoodsService;
import com.osl.service.OutputService;
import com.osl.service.SellplatformService;
import com.osl.service.SellshipService;
import com.osl.service.StockService;

@Controller
public class OutputController extends BaseController<OutputModel>{
	private static final Logger logger = LoggerFactory.getLogger(OutputController.class);

	@Autowired
	private OutputService service;
	
	@Autowired
	private SellplatformService sellplatformService;

	@Autowired
	private SellshipService sellshipService;
	
	@Autowired
	private CombinationService combinationService;
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private StockService stockService;

	@Autowired
	private RedisUtils redisUtils;
	
	/**
	 * 读取文件生成请求列表
	 * @author zhangzy
	 * @param model
	 * @param session
	 * @param outputdetailModel
	 * @return
	 */
	@RequestMapping(value = "/b/output/request/upload")
	public String b_entrys_Apply_upload(Model model, HttpSession session, OutputdetailModel outputdetailModel) {
		List<OutputdetailModel> tmpList = new ArrayList<OutputdetailModel>();
		int successNum = 0;
		int errorNum = 0;
		List<Long> errorList = new ArrayList<Long>(); 
		try {
			Reader resder = new InputStreamReader(outputdetailModel.getRequestFile().getInputStream(), "gbk");
			CSVReader csvresder = new CSVReader(resder);
			if (csvresder != null) {
				//售卖平台
				int myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
				List<SellplatformModel> sellplatformList = sellplatformService.find_sellplatform_All(myBusiness_id);
				//售卖列表
				SellshipModel sellshipModel = new SellshipModel();
				sellshipModel.setBusinessId(myBusiness_id);
				List<SellshipModel> sellshipInfoList = sellshipService.find_sellshipBusiness_All(sellshipModel);
				//组合品列表
				CombinationModel combinationModel = new CombinationModel();
				combinationModel.setBusinessId(myBusiness_id);
				List<CombinationModel> combinationList = combinationService.find_combination_All(combinationModel);
				//商品列表
				GoodsModel goodsModel = new GoodsModel();
				goodsModel.setBusinessId(myBusiness_id);
				List<GoodsModel> goodsList = goodsService.find_goodsAll(goodsModel);
				//库存信息列表
				List<StockModel> stockList = stockService.find_stockBusiness_All(myBusiness_id);
				//临时库存校验map
				Map<String,Integer> stockMap = new HashMap<String,Integer>();
				String[] csvRow = csvresder.readNext();	// row
				while ((csvRow = csvresder.readNext()) != null) {
					/*
					//无需上传，后台生成
					 * ×出库明细ID
					 * √商品ID
					 * √所属仓库ID
					 * √所属货架ID
					 * ×内盒数量
					 * ×内盒商品数量
					 * ×出库类型（贩卖，废弃，商品返回）
					 * ×商品类型（良品，不良品等）
					 * ×出库ID
					 * ×出库状态
					 * ×返品ID
					 * ×返品发货番号
					 * ×添加时间
					 * ×最后更新时间
					 * ×是否删除标识
					 * √是否是组合品
					 * √组合品ID
					 * √关联ID
					//需要上传
					 * √销售平台
					 * √贩卖id
					 * √出库数量
					 * √订单ID
					 * √买家姓名
					 * √邮编
					 * √地址1（都道府郡）
					 * √地址2
					 * √电话
					 * √发送类型
					 * √发货番号
					 * √运输模式（普通、加急）
					*/
					try {
						//新建临时出库详情对象
						OutputdetailModel tmpOutputdetailModel = new OutputdetailModel();
						//标识
						int i = 0;
						//获取销售平台
						String sellplatformCode = csvRow[i++];
						//对比销售平台list，得到销售平台id\name
						SellplatformModel tmpSellplatformModel = sellplatformList.stream().filter((SellplatformModel s) -> sellplatformCode.equals(s.getPlatformId())).collect(Collectors.toList()).get(0);
						tmpOutputdetailModel.setSellplatformName(tmpSellplatformModel.getName());
						tmpOutputdetailModel.setSellplatformId(String.valueOf(tmpSellplatformModel.getId()));
						//获取贩卖id
						String sellId = csvRow[i++];
						//对比贩卖列表，获得售卖具体信息（由于贩卖id有可能重复，有可能需要双重确认（贩卖id + 销售平台id））
						List<SellshipModel> tmpSellshipModelList = sellshipInfoList.stream().filter((SellshipModel s) -> sellId.equals(s.getSellId())).collect(Collectors.toList());
						SellshipModel tmpSellshipModel = new SellshipModel();
						if(tmpSellshipModelList.size() > 1) {
							tmpSellshipModel = tmpSellshipModelList.stream().filter((SellshipModel s) -> tmpSellplatformModel.getId() == s.getPlatformId()).collect(Collectors.toList()).get(0);
						}else {
							tmpSellshipModel = tmpSellshipModelList.get(0);
						}
						tmpOutputdetailModel.setShipId(tmpSellshipModel.getSellId());
						//商品信息
						String tmpGoodsId = tmpSellshipModel.getGoodsId();
						if(tmpSellshipModel.getType() == 0) {
							//非组合品
							tmpOutputdetailModel.setIscombination(tmpSellshipModel.getType());
							//商品信息
							GoodsModel tmpGoodsModel = goodsList.stream().filter((GoodsModel g) -> tmpGoodsId.equals(g.getGoodsId())).collect(Collectors.toList()).get(0);
							tmpOutputdetailModel.setGoodsId(tmpGoodsModel.getGoodsId());
							tmpOutputdetailModel.setSku(tmpGoodsModel.getSku());
							tmpOutputdetailModel.setCategoryName(tmpGoodsModel.getCategoryName());
							tmpOutputdetailModel.setGoodsName(tmpGoodsModel.getName());
							//库存信息
							StockModel tmpStockModel = stockList.stream().filter((StockModel s) -> tmpGoodsId.equals(s.getGoodsId())).collect(Collectors.toList()).get(0);
							tmpOutputdetailModel.setRequestInputGoodsNum(String.valueOf(tmpStockModel.getNums()));
							//仓库
							tmpOutputdetailModel.setDepotId(tmpStockModel.getDepotId());
							//货架
							tmpOutputdetailModel.setShelvesId(tmpStockModel.getShelvesId());
							//出库数量
							int nums = Integer.parseInt(csvRow[i++]);
							tmpOutputdetailModel.setNums(nums);
							tmpOutputdetailModel.setRequestOutputGoodsNum(String.valueOf(nums));
							if(stockMap.get(tmpGoodsId) == null) {
								stockMap.put(tmpGoodsId, nums);
							}else {
								stockMap.put(tmpGoodsId, stockMap.get(tmpGoodsId) + nums);
							}
						}else {
							//组合品
							tmpOutputdetailModel.setIscombination(tmpSellshipModel.getType());
							tmpOutputdetailModel.setCombinationId(tmpGoodsId);
							List<OutputdetailModel> tmpRequestOutputdetailModelList = new ArrayList<OutputdetailModel>();
							List<CombinationModel> tmpCombinationList = combinationList.stream().filter((CombinationModel c) -> tmpGoodsId.equals(c.getCombinationId())).collect(Collectors.toList());
							int nums = Integer.parseInt(csvRow[i++]);
							String requestOutputGoodsNum = "";
							String requestInputGoodsNum = "";
							String combinationModelName = "";
							for(CombinationModel tmpCombinationModel : tmpCombinationList) {
								combinationModelName = tmpCombinationModel.getName();
								String combinationGoodsId = tmpCombinationModel.getGoodsId();
								OutputdetailModel requestTmpOutputdetailModel = new OutputdetailModel();
								//商品信息
								GoodsModel tmpGoodsModel = goodsList.stream().filter((GoodsModel g) -> combinationGoodsId.equals(g.getGoodsId())).collect(Collectors.toList()).get(0);
								requestTmpOutputdetailModel.setGoodsId(tmpGoodsModel.getGoodsId());
								requestTmpOutputdetailModel.setSku(tmpGoodsModel.getSku());
								requestTmpOutputdetailModel.setCategoryName(tmpGoodsModel.getCategoryName());
								requestTmpOutputdetailModel.setGoodsName(tmpGoodsModel.getName());
								//库存信息
								StockModel tmpStockModel = stockList.stream().filter((StockModel s) -> combinationGoodsId.equals(s.getGoodsId())).collect(Collectors.toList()).get(0);
								requestTmpOutputdetailModel.setRequestInputGoodsNum(String.valueOf(tmpStockModel.getNums()));
								requestInputGoodsNum += tmpStockModel.getNums() + "/";
								//仓库
								requestTmpOutputdetailModel.setDepotId(tmpStockModel.getDepotId());
								//货架
								requestTmpOutputdetailModel.setShelvesId(tmpStockModel.getShelvesId());
								//出库数量
								requestOutputGoodsNum += tmpCombinationModel.getNums() * nums + "/";
								if(stockMap.get(combinationGoodsId) == null) {
									stockMap.put(combinationGoodsId, tmpCombinationModel.getNums() * nums);
								}else {
									stockMap.put(combinationGoodsId, stockMap.get(combinationGoodsId) + tmpCombinationModel.getNums() * nums);
								}
								requestTmpOutputdetailModel.setNums(tmpCombinationModel.getNums() * nums);
								requestTmpOutputdetailModel.setRequestOutputGoodsNum(String.valueOf(tmpCombinationModel.getNums() * nums));
								tmpRequestOutputdetailModelList.add(requestTmpOutputdetailModel);
							}
							if(requestOutputGoodsNum.length() > 0) {
								requestOutputGoodsNum = requestOutputGoodsNum.substring(0,requestOutputGoodsNum.length()-1);
							}
							if(requestInputGoodsNum.length() > 0) {
								requestInputGoodsNum = requestInputGoodsNum.substring(0,requestInputGoodsNum.length()-1);
							}
							tmpOutputdetailModel.setGoodsName(combinationModelName);
							tmpOutputdetailModel.setRequestInputGoodsNum(requestInputGoodsNum);
							tmpOutputdetailModel.setRequestOutputGoodsNum(requestOutputGoodsNum);
							tmpOutputdetailModel.setRequestOutputdetailModelList(tmpRequestOutputdetailModelList);
						}
						//订单ID
						String orderId = csvRow[i++];
						tmpOutputdetailModel.setOrderId(orderId);
						//买家姓名
						String customer = csvRow[i++];
						tmpOutputdetailModel.setCustomer(customer);
						//邮编
						String postcode = csvRow[i++];
						tmpOutputdetailModel.setPostcode(postcode);
						//地址1（都道府郡）
						String address1 = csvRow[i++];
						tmpOutputdetailModel.setAddress1(address1);
						//地址2
						String address2 = csvRow[i++];
						tmpOutputdetailModel.setAddress2(address2);
						//电话
						String tele = csvRow[i++];
						tmpOutputdetailModel.setTele(tele);
						//发送类型
						int sendType = Integer.parseInt(csvRow[i++]);
						tmpOutputdetailModel.setSendType(sendType);
						//发货番号
						String sendId = csvRow[i++];
						tmpOutputdetailModel.setSendId(sendId);
						//运输模式
						int transportMode = Integer.parseInt(csvRow[i++]);
						tmpOutputdetailModel.setTransportMode(transportMode);
						//增加到返回list中
						tmpList.add(tmpOutputdetailModel);
						//成功数量累计
						successNum ++;
					}catch(Exception e) {
						//失败数量累计
						errorNum ++;
						errorList.add(csvresder.getLinesRead());
					}
				}
				resder.close();
				csvresder.close();
				//库存比对
				for(OutputdetailModel tmpOutputdetailModel : tmpList) {
					boolean flag = false;
					for(String key : stockMap.keySet()) {
						//单品对比
						if(key.equals(tmpOutputdetailModel.getGoodsId())) {
							if(stockMap.get(key) >= Integer.parseInt(tmpOutputdetailModel.getRequestInputGoodsNum())) {
								flag = true;
							}
						}else if(tmpOutputdetailModel.getRequestOutputdetailModelList() != null && tmpOutputdetailModel.getRequestOutputdetailModelList().size() > 0){
							//拥有子商品
							//组合品对比
							List<OutputdetailModel> childRequestOutputdetailModelList = tmpOutputdetailModel.getRequestOutputdetailModelList();
							for(OutputdetailModel child : childRequestOutputdetailModelList) {
								if(key.equals(child.getGoodsId())) {
									if(stockMap.get(key) >= Integer.parseInt(child.getRequestInputGoodsNum())) {
										child.setInsufficient(0);
										flag = true;
									}else {
										child.setInsufficient(1);
									}
								}
							}
						}
					}
					if(flag) {
						tmpOutputdetailModel.setInsufficient(0);
					}else {
						tmpOutputdetailModel.setInsufficient(1);
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("item", tmpList);
		model.addAttribute("successNum", successNum);
		model.addAttribute("errorNum", errorNum);
		model.addAttribute("errorList", errorList);
		model.addAttribute("uploadFlag", 0);
		model.addAttribute("uname", session.getAttribute("u_login"));
		model.addAttribute("bname", session.getAttribute("u_bname"));
		model.addAttribute("burl", session.getAttribute("u_burl"));
		model.addAttribute("nav_active1", 5);
		return "/c/output/request";
	}
	
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
	
	/**
	 * 导出入库详情信息
	 * @author zhangzy
	 */
	@RequestMapping(value = "/all/export/output" ,method = RequestMethod.GET)
	public void exportInputDetail(Model model, HttpSession session, HttpServletResponse response,
			@RequestParam String params) {
		List<Map<String, Object>> dataList = null;
		// 参数组装
		if(params == null) {
			params = "{'status':-1}";
		}
		OutputModel outputModel = JSON.parseObject(params, OutputModel.class);
		outputModel.setBusinessId(this.myBusiness_id);
		outputModel.setWarehouseId(-1);
		// 查询入库列表
		List<OutputModel> _outputList = service.findOutputAll(outputModel);
		String sTitle = "出库ID,请求日期,出库日期,商品类型数量,商品总数量,状态";
		String fName = "output_detail_";
		String mapKey = "outputId,outputTime,requestDate,skuNums,goodsNums,status";
		dataList = new ArrayList<>();
		Map<String, Object> map = null;
		for (OutputModel tmpOutputlModel : _outputList) {
			map = new HashMap<String, Object>();
			map.put("outputId", tmpOutputlModel.getOutputId());
			map.put("outputTime", UtilDateTime.getStrByDate(UtilDateTime.DATE_TIME_PAT_19, tmpOutputlModel.getOutputTime()));
			map.put("requestDate", UtilDateTime.getStrByDate(UtilDateTime.DATE_TIME_PAT_19, tmpOutputlModel.getRequestDate()));
			map.put("skuNums", tmpOutputlModel.getSkuNums());
			map.put("goodsNums", tmpOutputlModel.getGoodsNums());
			map.put("status", tmpOutputlModel.getStatus() == 0 ? "出库中" : tmpOutputlModel.getStatus() == 1 ? "入库完了" : tmpOutputlModel.getStatus() == 2 ? "请求中" : tmpOutputlModel.getStatus() == 3 ? "请求处理中" : "请求取消");
			dataList.add(map);
		}
		try (final OutputStream os = response.getOutputStream()) {
			ExportUtil.responseSetProperties(fName, response);
			ExportUtil.doExport(dataList, sTitle, mapKey, os);
		} catch (Exception e) {
			logger.error("生成CSV失败", e);
		}
	}

	@Override
	protected String getPageId() {
		// TODO Auto-generated method stub
		return "test_user_page";
	}
}
