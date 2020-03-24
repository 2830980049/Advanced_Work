package com.edu.Servlet;

import com.edu.domain.Book;
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

@WebServlet("/CheckBook_ID_Servlet")
public class CheckBook_ID_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> bookList = (List<Book>)req.getServletContext().getAttribute("bookList");
        String id = req.getParameter("id");
        String flag = req.getParameter("flag");
        if(flag.equals("") == false && flag != null)
            return;
        Pattern pattern1 = Pattern.compile("^book\\d{4}$");
        Matcher m = pattern1.matcher(id);
        String msg = "0";
        if((id.equals("") || id == null)){
            msg = "-1";
            resp.getWriter().write(msg+"");
            return;
        }
                else if(m.find() == false){
                    msg = "-2";
                    resp.getWriter().write(msg+"");
                    return;
                }

        int len = bookList.size();
        for (int i = 0; i < len; i++){
            if(bookList.get(i).getId().equals(id)){
                msg = "1";
                break;
            }
        }

        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write(msg+"");
    }
}
