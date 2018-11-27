package com.osl.interceptor;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.osl.exception.ApplException;

@ControllerAdvice
public class ErrorExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(ErrorExceptionHandler.class);
    @Autowired
    private MessageSource messageSource;
	
    /**
     * 处理运行时异常的方法
     * @param exception 运行时异常
     * @return ModelAndView实例对象
     */
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView processException(Exception exception, HttpServletRequest req) {
        logger.info("自定义异常处理-RuntimeException");
        ModelAndView modelAndView = new ModelAndView();
        
        modelAndView.addObject("url", req.getRequestURL());
        if (exception instanceof NoHandlerFoundException) {
        	modelAndView.addObject("errorMsg", "404错误");
        	modelAndView.setViewName("/Err404.html");
        } else {
        	//Locale locale = LocaleContextHolder.getLocale();
        	Locale local = new Locale("zh_CN");
        	modelAndView.addObject("errorMsg", messageSource.getMessage("system.message.error", null, local));
        	//modelAndView.addObject("errorMsg", "系统错误");
        	modelAndView.setViewName("/Err500.html");
        }
        
        return modelAndView;
    }
	
//	@ExceptionHandler(ApplException.class) // (1)
//	@ResponseStatus(HttpStatus.CONFLICT) // (2)
//	public ModelAndView handleBusinessException(ApplException e) {
//	    ExtendedModelMap modelMap = new ExtendedModelMap();                 // (3)
//	    modelMap.addAttribute(e.getMessage());                       // (4)
//	    //String viewName = top(modelMap);                                    // (5)
//	    return new ModelAndView("/test/testUser.html", modelMap);                        // (6)
//	}
}
