package com.example.demo.controller;

import com.example.demo.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 返回jsp的controller
 */
@Controller
public class JspController extends BaseController {

    /**
     * negative
     * 因为这样是让浏览器再次请求这个接口返回的地址，而这个地址是被保护的，所以无法被访问到
     *
     * @return
     */
    @RequestMapping("/asd4")
    public String asd4() {
        return "redirect:/WEB-INF/views/page.jsp";
    }

    /**
     * positive
     * 用ModelAndView实现forward
     *
     * @return
     */
    @RequestMapping("/asd3")
    public ModelAndView asd3() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/hello");
        return modelAndView;
    }

    /**
     * positive
     * 直接重定向到某个jsp上
     */
    @RequestMapping("/asd5")
    public String asd5() {
        return "forward:/WEB-INF/views/page.jsp";
    }

    /**
     * positive
     * 字符串转发
     * 这样可以替代在里面写getDispatcher().forward()的转发方式，实现转发的
     * 同时还可以避免报错。
     * @return
     */
    @RequestMapping("/asd2")
    public String page2(){
        return "forward:/hello";
    }

    /**
     * positive
     *
     * 在配置了resolver后，只要写了controller，就算不返回String，resolver也会解析的，不会报任何错误。
     *
     */
    @RequestMapping("/page")
    public void page3() {
        System.out.println("page3 controller trigger...");
    }

    /**
     * 请求直接转发给jsp
     * A. 没在yml中配置resolver jsp解析器信息的时候，positive ，但是报错；
     * 1.  Cannot forward to error page for request [/forpage] as the response has already been committed.
     * 2.  Circular view path [forpage]: would dispatch back to the current handler URL [/forpage] again. Check your
     * ViewResolver setup! (Hint: This may be the result of an unspecified view, due to default view name generation.)
     * B. 配yml解析器后，positive， 只是java有报错：
     * 1. Cannot forward to error page for request [/forpage] as the response has already been committed
     * 2. File [&#47;WEB-INF&#47;views&#47;forpage.jsp] not found
     *
     * @param type
     * @throws IOException
     * @throws ServletException
     */
    @RequestMapping("/forpage")
    public void page2(Integer type) throws IOException, ServletException {
        getRequest().getRequestDispatcher("/WEB-INF/views/page.jsp").forward(getRequest(), getResponse());
    }

    /**
     * 请求转发
     * A. 没在yml中配置resolver的时候，negative->反馈404,而且报错：
     * 1. 如果“/hello”接口的返回值是“/hello”的时候：Cannot forward to error page for request [/asd] as the response has already been committed
     * 2. 如果“/hello”接口的返回值是“/hello1”的时候：上面的1错误 以及这个死循环错误 -> Circular view path [asd]
     * <p>
     * B. 配置了解析器后，可以positive返回hello页面，但是java有报错：
     * 1. Cannot forward to error page for request [/asd] as the response has already been committed.
     * 2.  File [&#47;WEB-INF&#47;views&#47;asd.jsp] not found
     *
     * 解决方案：
     *  1. 针对gerRequestDispatcher().forward的实在controller方法里面调用的，但是controller包含在servlet里面，而servlet在最后回去寻找
     * @param type
     * @throws IOException
     * @throws ServletException
     */
    @RequestMapping("/asd")
    public void page(Integer type) throws IOException, ServletException {
        getRequest().getRequestDispatcher("/hello").forward(getRequest(), getResponse());
    }

    /**
     * 响应包含User对象属性的jsp
     * A. 在没有配置resolver的时候，negative, 会报错：
     * Forwarding to error page from request [/hello] due to exception
     * [Circular view path [hello]: would dispatch back to the current handler URL [/hello] again. Check your ViewResolver setup!
     * 答案： 原因是因为返回的地址“/hello”和该接口的地址一样("hello")，由于没有自定义配置resolver，spring的默认机制是去请求指定地址的资源，这样一来又是请求
     * "/hello"的地址，就形成了死循环。如果我换成返回hello1，就不会是死循环（因为去请求“/hello1”的地址去了），而是页面上404，并且java爆这个错误：
     * Cannot forward to error page for request [/hello] as the response has already been committed
     * <p>
     * B. 配置了自定的解析器后就正常了
     *
     * @param type
     * @return
     */
    @RequestMapping("/hello")
    public String index(Integer type) {
        User user = new User();
        if (type == null || type == 0) {//user
            user.setAge(1);
            user.setUsername("federer");
            user.setType("user");
        } else {//admin
            user.setAge(2);
            user.setUsername("asdasd");
            user.setType("admin");
        }

        super.setModel("user", user);

        return "hello";
    }

    /**
     * 此controller触发外面的ExceptionController
     *
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    @RequestMapping("/errortest")
    public String error(String username, String password) throws Exception {
        throw new Exception();
    }

    /**
     * 直接返回login.jsp
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 该controller内部的所有接口执行前都会先执行这个接口
     *
     * @param request
     */
    @ModelAttribute
    public void preHandleController(HttpServletRequest request) {
        System.out.println("preHandleController:" + request.getRequestURI());
    }
}
