package com.edu.Servlet;

import com.edu.domain.Book_type;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/Check_Id_Servlet")
public class Check_Id_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book_type> bookTypes = (List<Book_type>)req.getServletContext().getAttribute("bookTypes");
        String id = req.getParameter("id");
        Pattern pattern = Pattern.compile("^ca\\d{4}$");
        Matcher m = pattern.matcher(id);
        String msg = "0";
        if(id.equals("") || id == null){
            msg = "-1";
            resp.getWriter().write(msg+"");
            return;
        }
        if(m.find() == false){
            msg = "-2";
            resp.getWriter().write(msg+"");
            return;
        }

        int len = bookTypes.size();
        for (int i = 0; i < len; i++){
            if(bookTypes.get(i).getId().equals(id)){
                msg = "1";
                break;
            }
        }

        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write(msg+"");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
