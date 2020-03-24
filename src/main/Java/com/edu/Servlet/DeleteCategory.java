package com.edu.Servlet;

import com.edu.domain.Book_type;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/DeleteCategory")
public class DeleteCategory extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book_type> bookTypes = (List<Book_type>)req.getServletContext().getAttribute("bookTypes");
        String id = req.getParameter("id");
        int len = bookTypes.size();
        for (int i = 0; i < len; i++){
            if(bookTypes.get(i).getId().equals(id)){
                bookTypes.remove(i);
                break;
            }
        }
        req.getServletContext().setAttribute("bookTypes", bookTypes);
        req.getRequestDispatcher("/categoryList.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
