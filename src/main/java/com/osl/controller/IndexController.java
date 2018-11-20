package com.osl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;

import com.osl.model.Users;
import com.osl.service.UsersService;


@Controller
@RequestMapping("/")

public class IndexController {
	
	@Autowired
	private UsersService service;
	
	@RequestMapping("/")
	public String index(Model model, Users user) {
		
		return "/login";
	}

}
