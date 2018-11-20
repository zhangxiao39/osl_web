package com.osl.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.osl.exception.ApplException;

@ControllerAdvice
public class ErrorExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(ErrorExceptionHandler.class);
	
    /**
            * 处理运行时异常的方法
     * @param exception 运行时异常
     * @return ModelAndView实例对象
     */
    @ExceptionHandler({ApplException.class})
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView processException(ApplException exception) {
        logger.info("自定义异常处理-RuntimeException");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", exception.getMessage());
        modelAndView.setViewName("500");
        return modelAndView;
    }
}
