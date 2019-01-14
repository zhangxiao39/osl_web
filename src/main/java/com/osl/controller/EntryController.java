package com.osl.controller;

import com.osl.common.Util;
import com.osl.common.UtilConst;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.osl.common.web.RedisUtils;
import com.osl.mapper.entity.EntryEntity;
import com.osl.mapper.entity.EntrydetailEntity;
import com.osl.mapper.entity.RelationshipEntity;
import com.osl.model.EntryModel;
import com.osl.model.EntrydetailModel;
import com.osl.model.GoodsModel;
import com.osl.model.StockModel;
import com.osl.service.EntryDetailService;
import com.osl.service.EntryService;
import com.osl.service.GoodsService;
import com.osl.service.StockService;

@Controller
public class EntryController extends BaseController<EntryModel> {
	
	@Autowired
	private EntryService service;
	@Autowired
	private EntryDetailService detailService;
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private RedisUtils redisUtils;
	
	/*
	 * @author:sun-hongyu
	 * @date:2018-12-28
	 * @des:纳品申请首页
	 * @param:
	 * @return：
	 * 	
	 */
	@RequestMapping(value = "/b/entry/apply")
	public String b_entrys_Apply(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			List<StockModel> _stockList = stockService.find_Business_All_Goods_Stock(this.myBusiness_id);
			model.addAttribute("item", _stockList);
			model.addAttribute("nav_active1", 2);
			return "/c/entry/apply";
		}
	}
	
	/*
	 * @des:【商家端】，纳品申请，条件查询库存一览
	 * @author：sun-hongyu
	 * @date:2019-1-7
	 * @param:
	 * @return:list<StockModel>
	 */
	@RequestMapping(value = "/b/entryStock/condition")
	public String cEntryApplyStockListByCondition(Model model, HttpSession session,@RequestParam(required = false) String sku,
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
			stockModel.setBusinessId(this.myBusiness_id);
			stockModel.setSku(sku);
			stockModel.setGoodsName(name);
			stockModel.setNums((!nums.equals("")&&nums!=null)?Integer.parseInt(nums):0);
			stockModel.setBarCode(barCode);
			stockModel.setCategoryId(goodsCategoryId);
			stockModel.setGoodsType(Integer.parseInt(status));
			List<String> lis = new ArrayList<String>();
			if(sku!=null && !sku.equals(""))
			{
				lis =Arrays.asList(sku.split(","));	//将拼接好的sku字符串转换成list
			}
			stockModel.setSkuList(lis);
			List<StockModel> _stockList = stockService.find_Business_All_Goods_by_condition(lis,stockModel);
			model.addAttribute("item", _stockList);
			model.addAttribute("nav_active1", 2);
			return "/c/entry/apply::table_refresh";
		}
	}
	
	/*
	 * @des:【商家端】 纳品申请
	 * @author:sun-hongyu
	 * @date:2019-1-7
	 * @param:String
	 * return:String
	 */
	@RequestMapping(value = "/a/entry/addEntry", method = RequestMethod.POST)
	@ResponseBody
	public String addEntry(HttpSession session, String entryParam,String entryDetailParams) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			String entryId = Util.generateTableIdByRedis(redisUtils,UtilConst.TABLE_KEY_TO_ENTRY, this.myBusiness_id);
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			
			//补全entryEntity信息
			EntryEntity entry = JSON.parseObject(entryParam,EntryEntity.class);
			entry.setEntryId(entryId);	//设置纳品id
			entry.setBusinessId(this.myBusiness_id); //设置商家id
			entry.setNewDate(new Timestamp( new Date().getTime()));	//设置新建时间
			entry.setOper(session.getAttribute("u_bname").toString());	//设置操作者
			entry.setOperTime(new Timestamp(new Date().getTime()));	//设置操作时间
			List<RelationshipEntity> relationshipList = (List<RelationshipEntity>)session.getAttribute("u_relationship");
			entry.setWarehouseId(relationshipList.get(0).getWarehouseId());	//设置运营商id
			entry.setStatus(UtilConst.ENTRY_STATUS_APPLYING);
			
			//补全EntryDertailEntity信息
			List<EntrydetailEntity> entryDetailList = JSON.parseArray(entryDetailParams, EntrydetailEntity.class);
			for(EntrydetailEntity entryDetail:entryDetailList)
			{
				String entryDetailId = Util.generateTableIdByRedis(redisUtils,UtilConst.TABLE_KEY_TO_ENTRY_DETAIL, this.myBusiness_id);
				entryDetail.setDetailId(entryDetailId);
				entryDetail.setEntryId(entryId);
				entryDetail.setNewDate(new Timestamp(new Date().getTime()));
				entryDetail.setStatus(UtilConst.ENTRY_STATUS_APPLYING);
			}
			
			if (null != entry && null != entryDetailList) {
				int ok = service.insertEntry(entry,entryDetailList);
				if (ok > 0) {
					return entryId;
				} else if (ok == -1) {
					return "";
				} else {
					return "";
				}
			} else {
				return "";
			}
		}

	}
	
	/**
	 * @des 【商家端】 纳品模板文件下载
	 * @author sun-hongyu
	 */
	@RequestMapping(value = "/b/entry/downloadEntryFile")
	public ResponseEntity<byte[]> downloadEntryFile(HttpServletRequest request) throws Exception{
		ServletContext servletContext = request.getServletContext();
		String fileName="entryl_demo.csv";
		String realPath = servletContext.getRealPath(fileName);//得到文件所在位置
        InputStream in=new FileInputStream(new File(realPath));//将该文件加入到输入流之中
        byte[] body=null;
        body=new byte[in.available()];// 返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数
        in.read(body);//读入到输入流里面
        fileName=new String(fileName.getBytes("gbk"),"iso8859-1");//防止中文乱码
        HttpHeaders headers=new HttpHeaders();//设置响应头
        headers.add("Content-Disposition", "attachment;filename="+fileName);
        HttpStatus statusCode = HttpStatus.OK;//设置响应吗
        ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(body, headers, statusCode);
        return response;
	}
	
	/**
	 * 
	 * @des	 【商家端】纳品文件上传提交
	 * @param EntryModel
	 * @return String
	 * @throws ParseException 
	 */
	
	@SuppressWarnings("resource")
	@RequestMapping(value = "/b/entry/apply/upload")
	public String bEntrysApplyUploadFileSub(Model model, HttpSession session, EntryModel entryModel) throws ParseException {
		//纳品实体
		EntryEntity entry = new EntryEntity();
		
		List<EntrydetailEntity> entryDetailList = new ArrayList<EntrydetailEntity>();
		
		//文件错误相关信息
		int successCount = 0;	//上传成功数量
		int failCount = 0;		//长传失败数量
		//上传失败信息表，int[]数组中，int[0]:代表行，int[....]代表列
		List<int[]> errotInfoList = new ArrayList<int[]>();	
		//文件数据正确标识，默认为true，当发现有一条数据不正确则置为false;
		boolean successFlag = true;	
		
		String entryId = Util.generateTableIdByRedis(redisUtils,UtilConst.TABLE_KEY_TO_ENTRY, this.myBusiness_id);
		this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
		try {
			Reader resder = new InputStreamReader(entryModel.getApplyFile().getInputStream(), "gbk");
			CSVReader csvresder = new CSVReader(resder);
			//上传文件信息列表
			String[] strs;
			int goodsSkuNum = 0;
			int goodsNum = 0;
			for(int i =0;(strs =csvresder.readNext())!=null;i++)
			{
				
				if(i!=0)	//去掉表头
				{
					int[] errorData = new int[7]; //尺寸为7，第一个未行号，其余为列
					errorData[0] = i;	//错误行
					int arrayIndex = 1;
					//校验sku,(1)组合为数字和英文，(2)sku对应的商品存在
					if(Util.isEmpty(strs[0])||!Util.isNumeAndStr(strs[0])||
							(goodsService.find_goodsBusiness_sku(this.myBusiness_id, strs[0])==null))
					{
						errorData[arrayIndex] = 1; 	//第i行，sku，第1列错误
						arrayIndex++;
						successFlag = false;
					}
					//校验纳品数量，正整数
					if(Util.isEmpty(strs[1])||!Util.isNumer(strs[1]))
					{
						errorData[arrayIndex] = 2; 	//第i行，纳品数量，第2列错误
						arrayIndex++;
						successFlag = false;
					}
					//校验尺寸,小数或者正整数
					if(Util.isEmpty(strs[2])||!(Util.isPositiveDecimal(strs[2])||Util.isNumer(strs[2])))
					{
						errorData[arrayIndex] = 3; 	//第i行，包裹尺寸，第3列错误
						arrayIndex++;
						successFlag = false;
					}
					//校验内盒数量，正整数
					if(Util.isEmpty(strs[3])||!Util.isNumer(strs[3]))
					{
						errorData[arrayIndex] = 4; 	//第i行，内盒数量，第4列错误
						arrayIndex++;
						successFlag = false;
					}
					//校验内盒商品数量，正整数
					if(Util.isEmpty(strs[4])||!Util.isNumer(strs[4]))
					{
						errorData[arrayIndex] = 5; 	//第i行，盒内商品数量，第5列错误
						arrayIndex++;
						successFlag = false;
					}
					//校验时间，非空
					if(Util.isEmpty(strs[5]))
					{
						errorData[arrayIndex] = 6; 	//第i行，时间信息，第6列错误
						successFlag = false;
					}
					if(!successFlag)
					{
						errotInfoList.add(errorData);
						failCount++;
						continue;	//当前行数据错误
					}
					successCount++;	//成功数;
					String skuValue = strs[0];
					int numsValue = Integer.parseInt(strs[1]);
					double packageSizeValue = Double.parseDouble(strs[2]);
					int innerNumValue = Integer.parseInt(strs[3]);
					int innerGoodsNumValue = Integer.parseInt(strs[4]);
					Date validateValue = new SimpleDateFormat("yyyy/MM/dd").parse(strs[5]);
					//纳品详情列表
					EntrydetailEntity entryDetailTemp = new EntrydetailEntity();
					String sku = skuValue;
					//获取goods信息
					GoodsModel goods = goodsService.find_goodsBusiness_sku(this.myBusiness_id, sku);
					goodsSkuNum++;
					goodsNum+=numsValue;
					entryDetailTemp.setDetailId(Util.generateTableIdByRedis(redisUtils,UtilConst.TABLE_KEY_TO_ENTRY_DETAIL, this.myBusiness_id));
					entryDetailTemp.setEntryId(entryId);
					entryDetailTemp.setNums(goodsNum);
					entryDetailTemp.setGoodsId(goods.getGoodsId());
					entryDetailTemp.setInnerNums(innerNumValue);
					entryDetailTemp.setInnerGoodsNums(innerGoodsNumValue);
					entryDetailTemp.setPackageSize(packageSizeValue);
					entryDetailTemp.setValidityTime(validateValue);
					entryDetailTemp.setNewDate(new Timestamp(new Date().getTime()));
					entryDetailTemp.setDeleteFlg(0);
					entryDetailTemp.setStatus(UtilConst.ENTRY_STATUS_APPLYING);
					//纳品详情list赋值
					entryDetailList.add(entryDetailTemp);
				}
				
			}
			if(successFlag)
			{
				//纳品实体赋值
				entry.setEntryId(entryId);	//设置纳品id
				entry.setBusinessId(this.myBusiness_id); //设置商家id
				entry.setSkuNums(goodsSkuNum);	//设置sku数量
				entry.setGoodsNums(goodsNum);
				entry.setNewDate(new Timestamp( new Date().getTime()));	//设置新建时间
				entry.setOper(session.getAttribute("u_bname").toString());	//设置操作者
				entry.setOperTime(new Timestamp(new Date().getTime()));	//设置操作时间
				List<RelationshipEntity> relationshipList = (List<RelationshipEntity>)session.getAttribute("u_relationship");
				entry.setWarehouseId(relationshipList.get(0).getWarehouseId());	//设置运营商id
				entry.setStatus(UtilConst.ENTRY_STATUS_APPLYING);
				entry.setDeleteFlg(0);
				csvresder.close();
				//向数据库中插入
				if (null != entry && null != entryDetailList) {
					int ok = service.insertEntry(entry,entryDetailList);
					if (ok > 0) {
						//返回到纳品详情
						return "redirect:/b/entry/detailList/"+entryId;
					}
				} 
			}
			else
			{
				//上传文件错误，返回到申请页面，附带商品库存信息，以及纳品文件错误信息
				List<StockModel> _stockList = stockService.find_Business_All_Goods_Stock(this.myBusiness_id);
				model.addAttribute("item", _stockList);
				model.addAttribute("successCount",successCount);
				model.addAttribute("failCount",failCount);
				model.addAttribute("errorList", errotInfoList);
				model.addAttribute("nav_active1", 2);
				return "/c/entry/apply";
				
			}
	
		} catch (IOException e) {
			e.printStackTrace();
		}
		//返回申请页
		return "redirect:/b/entry/detailList/"+entryId;
	}

	/*
	 * @author:sun-hongyu
	 * @date:2018-12-28
	 * @des:【商家端】，纳品列表一览
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
				EntrydetailModel entryDetailModel = new EntrydetailModel();
				entryDetailModel.setEntryId(entryId);
				entryDetailModel.setStatus(UtilConst.ENTRY_STATUS_CANCLE);
				int ok2 = detailService.bupdateStatusByEntryId(entryDetailModel);
				if(ok2>0)
				{
					return "ok";
				}
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
