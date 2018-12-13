package com.osl.controller;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.opencsv.CSVReader;
import com.osl.common.web.BaseController;
import com.osl.model.EntryModel;
import com.osl.service.EntryService;
import com.osl.service.TestUserService;

@Controller
public class EntryController extends BaseController<EntryModel> {
	
	@Autowired
	private EntryService service;
	
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

	@RequestMapping(value = "/b/entry/list")
	public String b_entrys_List(Model model, HttpSession session) {
		if (session.getAttribute("u_login") == null) {
			return "redirect:/business/login";
		} else {
			model.addAttribute("uname", session.getAttribute("u_login"));
			model.addAttribute("bname", session.getAttribute("u_bname"));
			model.addAttribute("burl", session.getAttribute("u_burl"));
			model.addAttribute("nav_active1", 3);
			return "/c/entry/list";
		}
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

	@Override
	protected String getPageId() {
		// TODO Auto-generated method stub
		return "entry_page_id";
	}

}
