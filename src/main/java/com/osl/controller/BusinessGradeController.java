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
import com.osl.mapper.entity.BusinessGradeEntity;
import com.osl.model.BusinessGradeModel;
import com.osl.service.BusinessGradeService;

@Controller
public class BusinessGradeController extends BaseController<BusinessGradeModel> {

	@Autowired
	private BusinessGradeService businessGradeService;

	/**
	 * 
	 * @Title: b_gradeManage
	 * @Description: 商家等级一览
	 * @param @param model
	 * @param @param session
	 * @param @return 参数
	 * @return String 返回类型
	 * @@author zhangxiao
	 */
	@RequestMapping(value = "/a/business/grade", method = RequestMethod.GET)
	public String b_gradeManage(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			this.myBusiness_id = Integer.valueOf(session.getAttribute("u_bid").toString());
			List<BusinessGradeModel> buisnessGradeModel = businessGradeService.findAll(myBusiness_id);
			model.addAttribute("item", buisnessGradeModel);
			model.addAttribute("nav_active5", 1);
			return "/w/business/grade";
		}
	}

	/**
	 * 
	 * @Title: addBusinessGrade
	 * @Description: 添加商家等级
	 * @param @param model
	 * @param @param session
	 * @param @param _businessGrade
	 * @param @return 参数
	 * @return String 返回类型
	 * @@author zhangxiao
	 */
	@RequestMapping(value = "/osl/bussinessGrade", method = RequestMethod.POST)
	@ResponseBody
	public String addBusinessGrade(Model model, HttpSession session, BusinessGradeEntity _businessGrade) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/admin/login";
		} else {
			int ok = businessGradeService.insert(_businessGrade);
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
	 * 
	 * @Title: deleteBusinessGrade
	 * @Description: 删除商家等级 逻辑删除
	 * @param @param id
	 * @param @return 参数
	 * @return String 返回类型
	 * @@author zhangxiao
	 */
	@RequestMapping(value = "/osl/bussinessGrade/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteBusinessGrade(@PathVariable(required = true) int id) {
		int ok = businessGradeService.delete(id);
		if (ok > 0) {
			return "ok";
		} else {
			return "fail";
		}
	}

	/**
	 * 
	* @Title: showInfo  
	* @Description: 根据ID显示商家等级信息
	* @param @param id
	* @param @return    参数  
	* @return BusinessGradeModel    返回类型  
	* @@author zhangxiao
	 */
	@RequestMapping(value = "/osl/bussinessGrade/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BusinessGradeModel showInfo(@PathVariable(required = true) int id) {
		BusinessGradeModel _info = new BusinessGradeModel();
		_info = businessGradeService.findById(id);
		return _info;
	}

	/**
	 * 
	* @Title: updateGrade 
	* @Description: 更新商家等级信息
	* @param @param _info
	* @param @return    参数  
	* @return String    返回类型  
	* @@author zhangxiao
	 */
	@RequestMapping(value = "/osl/bussinessGrade", method = RequestMethod.PUT)
	@ResponseBody
	public String updateGrade(BusinessGradeEntity _info) {
		if (_info.getId() > 0) {
			int ok = businessGradeService.update(_info);
			if (ok > 0) {
				return "ok";
			} else {
				return "fail";
			}
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
