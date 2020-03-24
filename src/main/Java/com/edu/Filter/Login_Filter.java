package com.edu.Filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class Login_Filter implements Filter {
    public static final String login_page = "/login.jsp";

    //不被拦截页面
    private static ArrayList<String> init_Pages = new ArrayList<String>();
    static {
        init_Pages.add("/login.jsp");
    }



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String url = request.getRequestURI();
        String path = request.getContextPath();
        //取得首页名称
        String targetURL = url.substring(path.length());
        System.out.println("url:"+url);
        System.out.println("path:"+path);
        System.out.println("targeURL:"+targetURL);

        HttpSession session = request.getSession(false);
        System.out.println(session);
        //判断是否为 不被拦截页面
        Boolean isInit_Page = false;
        for(String init_Page : init_Pages){
            System.out.println(init_Page);
            if(init_Page.equals(targetURL))
                isInit_Page = true;
        }
        System.out.println(isInit_Page+" "+!isInit_Page);
        if(!isInit_Page){
            System.out.println(targetURL);
            if(session == null || session.getAttribute("user") == null){
                response.sendRedirect("login.jsp");
                return;
            }
            else{
                filterChain.doFilter(request,response);
                return;
            }
        }
        else{
            filterChain.doFilter(request,response);
            return;
        }
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void destroy() {

    }
}
