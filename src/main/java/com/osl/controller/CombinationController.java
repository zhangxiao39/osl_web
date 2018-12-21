package com.osl.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.osl.common.web.BaseController;
import com.osl.mapper.entity.CombinationEntity;
import com.osl.mapper.entity.GoodsEntity;
import com.osl.model.CombinationModel;
import com.osl.service.CombinationService;

@Controller
public class CombinationController extends BaseController<CombinationModel> {

	@Autowired
	private CombinationService service;

	@RequestMapping(value = "/b/goods/combinationlist")
	public String b_g_combinationlistManage(Model model, HttpSession session,
			@RequestParam(required = false) String qry_combinationId, @RequestParam(required = false) String qry_sku2,
			@RequestParam(required = false) String qry_barcode, @RequestParam(required = false) String qry_name) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/business/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			CombinationModel combinationModel = new CombinationModel();
			combinationModel.setBusinessId(myBusiness_id);
			combinationModel.setSku(qry_sku2);
			combinationModel.setName(qry_name);
			combinationModel.setCombinationId(qry_combinationId);
			combinationModel.setGoodsBarcode(qry_barcode);
			List<CombinationModel> _combinationList = service.find_combination_All(combinationModel);
			model.addAttribute("item", _combinationList);
			model.addAttribute("nav_active2", 3);
			return "/c/goods/combinationlist";
		}
	}

	@RequestMapping(value = "/osl/combination", method = RequestMethod.POST)
	@ResponseBody
	public String addCombination(Model model, HttpSession session, @RequestBody String _json)
			throws JsonParseException, JsonMappingException, IOException {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			ObjectMapper mapper = new ObjectMapper();
			JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, CombinationEntity.class);
			List<CombinationEntity> _combinationEntitys = mapper.readValue(_json, javaType);
			if (_combinationEntitys.size() > 0) {
				int ok = service.doInsertCombinations(_combinationEntitys);
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

	@RequestMapping(value = "/osl/combination", method = RequestMethod.PUT)
	@ResponseBody
	public String updateCombination(Model model, HttpSession session, @RequestBody String _json)
			throws JsonParseException, JsonMappingException, IOException {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			ObjectMapper mapper = new ObjectMapper();
			JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, CombinationEntity.class);
			List<CombinationEntity> _combinationEntitys = mapper.readValue(_json, javaType);
			if (_combinationEntitys.size() > 0) {
				if (service.updateCombinations(_combinationEntitys) > 0) {
					return "ok";
				} else {
					return "fail";
				}
			} else {
				return "fail";
			}
		}
	}

	@RequestMapping(value = "/osl/combinationshow/{combinationId}", method = RequestMethod.GET)
	@ResponseBody
	public List<CombinationModel> showInfoByCombnationId(@PathVariable(required = true) String combinationId) {
		this.myBusiness_id = Integer.valueOf(this.getSessAttr("u_bid").toString());
		List<CombinationModel> _info = service.find_combinationByCode(combinationId, this.myBusiness_id);
		return _info;
	}

	@RequestMapping(value = "/osl/combination/{combinationId}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteProduct(@PathVariable(required = true) String combinationId) throws IOException {
		this.myBusiness_id = Integer.valueOf(this.getSessAttr("u_bid").toString());
		int ok = service.deleteByCode(combinationId, this.myBusiness_id);
		if (ok > 0) {
			return "ok";
		} else {
			return "fail";
		}
	}

	@Override
	protected String getPageId() {
		// TODO Auto-generated method stub
		return null;
	}
}
