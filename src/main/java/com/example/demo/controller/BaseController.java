package com.example.demo.controller;

import com.example.demo.util.HttpContextUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class BaseController {
    protected HttpServletRequest getRequest() {
        return HttpContextUtil.getHttpServletRequest();
    }

    protected HttpServletResponse getResponse() {
        return HttpContextUtil.getHttpServletResponse();
    }

    protected void setModel(String key, Object val) {
        getRequest().setAttribute(key, val);
    }

    protected void setModelMap(Map<String, Object> objs) {
        ServletRequest request = getRequest();
        for (String key : objs.keySet()) {
            request.setAttribute(key, objs.get(key));
        }
    }

}
