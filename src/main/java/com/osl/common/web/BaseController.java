package com.osl.common.web;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.osl.exception.ApplException;

@Component
public abstract class BaseController<T extends BaseModel> {

	public static final String LOGIN_SESS_ID = "LOGIN_SESS_ID_____";

	public String ViewUrl = "";
	public int myBusiness_id = 0;

	@Autowired
	protected HttpServletRequest request;

	private HttpSession getSession() {
		HttpServletRequest request = getRequest();
		if (request != null) {
			return request.getSession();
		} else {
			return null;
		}
	}

	private HttpServletRequest getRequest() {
		return this.request;
	}

	public void setReqAttr(String key, Object value) {
		HttpServletRequest request = getRequest();
		if (request != null) {
			request.setAttribute(key, value);
		}
	}

	public Object getReqAttr(String key) {
		HttpServletRequest request = getRequest();
		if (request != null) {
			return request.getAttribute(key);
		} else {
			return null;
		}
	}

	public void setSessAttr(String key, Object value) {
		HttpSession session = getSession();
		if (session != null) {
			session.setAttribute(key, value);
		}
	}

	public Object getSessAttr(String key) {
		HttpSession session = getSession();
		if (session != null) {
			return session.getAttribute(key);
		} else {
			return null;
		}
	}

	public void removeSessAttr(String key) {
		HttpSession session = getSession();
		if (session != null) {
			session.removeAttribute(key);
		}
	}

	public void clearSess() {
		HttpSession session = getSession();
		if (session != null) {
			Enumeration<String> names = session.getAttributeNames();

			if (names != null) {
				while (names.hasMoreElements()) {
					String name = names.nextElement();
					session.removeAttribute(name);
				}
			}
		}
	}

	/**
	 * 处理运行时异常的方法
	 * 
	 * @param exception 运行时异常
	 * @return ModelAndView实例对象
	 */
	@ExceptionHandler({ ApplException.class })
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView processException(ApplException exception) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", exception.getMessage());
		modelAndView.addObject("url", this.ViewUrl);
		BaseModel t = new BaseModel();
		t.setErrorMsg(exception.getMessage());
		modelAndView.addObject("usermodel", t);
		modelAndView.setViewName(this.ViewUrl);
		return modelAndView;
	}

	protected abstract String getPageId();
}
