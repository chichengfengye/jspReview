package com.example.demo.controller;

import com.example.demo.util.HttpContextUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public String handle(Exception e) {
        HttpServletRequest httpServletRequest = HttpContextUtil.getHttpServletRequest();
        httpServletRequest.setAttribute("username","费德勒");
        httpServletRequest.setAttribute("password","林俊杰");
//        e.printStackTrace();
        return "err";
    }
}
