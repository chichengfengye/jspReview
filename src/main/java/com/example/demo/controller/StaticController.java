package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * 这个是用于测试，在配置了jsp情况下返回静态资源的实验
 *
 * 测试结果：
 * 1, 直接返回一个字符串不能实现，因为字符串被认为文件名，这样导致去寻找这个文件，
 *    可是静态文件不是jsp结尾，所以不可以用这种方式来做。
 * 2. 利用重定向可以实现，其实本质就是等于让浏览器直接访问了一个静态地址，这个地址
 *    是被server允许的（在WEB-INF外边），所以能够访问到。
 */
@Controller
public class StaticController extends BaseController{
    /**
     * negative
     *
     * 由于已经配置了jsp的解析器（见application-dev.yml）
     * 所以，这里这样返回的话，spring会去找/WEB-INF/views/jf.html.jsp
     * 从而404了
     * @return
     */
    @RequestMapping("/jf")
    public String jf() {
        return "jf.html";
    }

    /**
     * positive
     *
     * 这种方式是可以的。
     * 在地址栏输入"localhost/jd"就会进入这个controller，随后这个“/static/jd.html”就
     * 会把地址重定向到“localhost/static/jd.html”
     * @throws IOException
     */
    @RequestMapping("/jd")
    public void jd() throws IOException {
        getResponse().sendRedirect("/static/jd.html");
    }

    //TODO
    //用Sting的方式重定向"redirect:1.html"


    /**
     * ask
     *      为什么转发后spring还会去寻找awe1.jsp?
     *      貌似跟session有关，spring会在response commit之后创建session
     *
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/awe1")
    public void aew1() throws ServletException, IOException {
        getRequest().getRequestDispatcher("/static/announce1.html").forward(getRequest(),getResponse());
    }

    /**
     * negative & ask
     *      为什么转发后spring还会去寻找awe2.jsp?
     *      貌似跟session有关，spring会在response commit之后创建session
     *
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/awe2")
    public void aew2() throws ServletException, IOException {
        getRequest().getRequestDispatcher("/WEB-INF/static2/announce2.html").forward(getRequest(),getResponse());
    }


}
