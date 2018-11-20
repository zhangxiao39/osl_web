package com.osl.common.web;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseController<T extends BaseModel>  {

    public static final String LOGIN_SESS_ID = "LOGIN_SESS_ID_____";
    
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
    
    protected abstract String getPageId();
}
