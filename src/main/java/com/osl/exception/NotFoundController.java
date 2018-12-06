package com.osl.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class NotFoundController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }
    @RequestMapping(value = "/error")
    public Object error(HttpServletResponse response, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        
    	modelAndView.addObject("errorMsg", "404错误");
    	modelAndView.setViewName("/Err404.html");
        
        return modelAndView;
    }
}
