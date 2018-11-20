package com.osl.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 权限处理拦截器
 * 
 * @author
 *
 */
public class AuthorCtrlInterceptor implements HandlerInterceptor {
	private static final String no_login = "no_login";

    private final static Logger logger = LoggerFactory.getLogger(AuthorCtrlInterceptor.class);
 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("进入 preHandle 方法..." + request.getRequestURL().toString() + "," + request.getRequestURI());
        logger.info("response.getStatus():" + response.getStatus());
//        if (response.getStatus() == 404) {
//        	logger.error("404错误:" + request.getRequestURL().toString() + "," + request.getRequestURI());
//        } else {
//            if (!"/osl/testUser".equals(request.getRequestURI())) {
//            	response.sendRedirect("/osl/testUser");
//            	return false;
//            }
//        }
        return true;
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("进入 postHandle 方法..." + request.getRequestURL().toString() + "," + request.getRequestURI());

//		if(response.getStatus() == 500) {
//			logger.error("500错误:" + request.getRequestURL().toString() + "," + request.getRequestURI());
//			/*
//			 * setViewName(String viewName);
//			 * 为此ModelAndView设置视图名称，由DispatcherServlet通过ViewResolver解析。 将覆盖任何预先存在的视图名称或视图。
//			 */
//		} else if (response.getStatus() == 404) {
//			logger.error("404错误:" + request.getRequestURL().toString() + "," + request.getRequestURI());
//		}
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("进入 afterCompletion 方法..." + request.getRequestURL().toString() + "," + request.getRequestURI());
    }
}
