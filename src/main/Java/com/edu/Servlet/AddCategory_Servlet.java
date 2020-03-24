package com.edu.Servlet;

import com.edu.domain.Book_type;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AddCategory_Servlet")
public class AddCategory_Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book_type> bookTypes = (List<Book_type>)req.getServletContext().getAttribute("bookTypes");
        Book_type bookType = new Book_type();
        bookType.setId(req.getParameter("categoryId"));
        bookType.setName(req.getParameter("categoryName"));
        bookTypes.add(bookType);
        req.getServletContext().setAttribute("bookTypes", bookTypes);
        req.getRequestDispatcher("/categoryList.jsp").forward(req,resp);
    }
}
