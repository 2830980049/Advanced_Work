package com.edu.Servlet;

import com.alibaba.fastjson.JSON;
import com.edu.Service.Impl.UserServiceImpl;
import com.edu.Service.UserService;
import com.edu.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/login.do")
public class Login_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String warn = "请输入账户或者密码";
        req.setAttribute("warn",warn);
        req.getSession().setAttribute("warn",warn);
        req.getRequestDispatcher("/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        List<User> userList = (List<User>)req.getServletContext().getAttribute("list");
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        UserService userService = new UserServiceImpl();
        String code = (String)req.getSession().getAttribute("checkCode");
        String code1 = req.getParameter("verifyCode");
        if(code1.equalsIgnoreCase(code) == false){
            req.setAttribute("msg", "验证码错误！");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }
        else {
            if (userService.login(userList, user) == null) {
                req.setAttribute("msg", "账号或密码错误！");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            } else {
                req.getSession().setAttribute("user", user);
                req.getRequestDispatcher("/categoryList.jsp").forward(req, resp);
            }
        }
    }
}
