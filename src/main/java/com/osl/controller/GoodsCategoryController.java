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

import com.osl.common.web.BaseController;
import com.osl.mapper.entity.GoodsCategoryEntity;
import com.osl.model.GoodsCategoryModel;
import com.osl.model.StockModel;
import com.osl.service.GoodscategoryService;

@Controller
public class GoodsCategoryController extends BaseController<GoodsCategoryModel>{

	@Autowired
	private GoodscategoryService service;

	@RequestMapping(value = "/a/goods/category")
	public String g_categoryeManage(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			String _html = service.setCategoryHtml();
			model.addAttribute("item_html", _html);
			model.addAttribute("nav_active2", 1);
			return "/w/goods/category";
		}
	}

	@RequestMapping(value = "/osl/goods/categoryList", method = RequestMethod.GET)
	@ResponseBody
	public List<GoodsCategoryModel> showInfo() {
		List<GoodsCategoryModel> _info = service.getCategoryByC1();
		return _info;
	}
	
	/*@des:获取叶子节点分类列表
	 * @author：sun-hongyu
	 * @date:2018-12-14
	 * @param:
	 * @return:list<GoodsCategoryModel>
	 */
	@RequestMapping(value = "/osl/goods/categoryMinList", method = RequestMethod.POST)
	@ResponseBody
	public List<GoodsCategoryModel> getCategoryMin(HttpSession session) {
			List<GoodsCategoryModel> list = service.getCategoryMin();
			return list;
	}
	
	@RequestMapping(value = "/osl/goodscategory", method = RequestMethod.POST)
	@ResponseBody
	public String addGoodsCatrgory(Model model, HttpSession session, GoodsCategoryEntity _goodscategory) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			if (_goodscategory.getId() == 0) {
				int ok = service.insertGoodsCategory(_goodscategory);
				if (ok > 0) {
					return "ok";
				} else {
					return "fail";
				}
			} else {
				return "fail";
			}
		}
	}
	
	@RequestMapping(value = "/osl/goodscategory", method = RequestMethod.PUT)
	@ResponseBody
	public String updateGoodsCatrgory(GoodsCategoryEntity _goodscategory) {
		if (_goodscategory.getId() > 0) {
			int ok = service.updateGoodscategoryName(_goodscategory);
			if (ok > 0) {
				return "ok";
			} else {
				return "fail";
			}
		} else {
			return "fail";
		}
	}
	
	@RequestMapping(value = "/osl/goodscategory/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteGoodsCatrgory(@PathVariable(required = true) int id) {
		int ok = service.deleteById(id);
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
