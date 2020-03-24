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

@WebServlet("/DeleteBook_Servlet")
public class DeleteBook_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> bookList = (List<Book>)req.getServletContext().getAttribute("bookList");
        String id = req.getParameter("id");
        int len = bookList.size();
        for (int i = 0; i < len; i++){
            if(bookList.get(i).getId().equals(id)){
                bookList.remove(i);
                break;
            }
        }
        req.getServletContext().setAttribute("bookList", bookList);
        req.getRequestDispatcher("/bookList.jsp").forward(req,resp);
    }
}
