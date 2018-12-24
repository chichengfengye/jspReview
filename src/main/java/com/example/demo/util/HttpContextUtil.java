package com.example.demo.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpContextUtil {
    public static HttpServletRequest getHttpServletRequest() {
        return getServletRequestAttributes().getRequest();
    }

    public static HttpServletResponse getHttpServletResponse() {
        return getServletRequestAttributes().getResponse();
    }

    public static HttpServletRequest getHttpServletRequestMayNull() {
        return getServletRequestAttributesMayNull().getRequest();
    }

    public static HttpServletResponse getHttpServletResponseMayNull() {
        return getServletRequestAttributesMayNull().getResponse();
    }

    private static ServletRequestAttributes getServletRequestAttributes() {
        // NOTE 这里不使用getRequestAttributes()是为了防止RequestContextListener没有部署, 返回不可控的null值
        // NOTE currentRequestAttributes()在RequestContextListener未部署，取不到对象时，会直接抛出异常
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            return (ServletRequestAttributes) requestAttributes;
        } else {
            // 一般出现这个异常原因是没有部署RequestContextListener且启用了JSF环境
            throw new IllegalStateException("当前上下文环境非Servlet环境");
        }
    }

    private static ServletRequestAttributes getServletRequestAttributesMayNull() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();//may null
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;//may null pointer
        return servletRequestAttributes;
    }
}
