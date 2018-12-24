package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class PingController extends BaseController{
    @RequestMapping("/ping")
    @ResponseBody
    public String ping() {
        return "pong";
    }
}