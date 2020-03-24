package com.edu.Filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.LogRecord;

public class ContentType implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        req.setCharacterEncoding("UTF-8");
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        resp.setContentType("text/html;charset=utf-8");
        filterChain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }
}
