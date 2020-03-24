package com.edu.Servlet;

import com.alibaba.fastjson.JSON;
import com.edu.domain.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SearchBook_Servlet")
public class SearchBook_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String value = req.getParameter("value");
        List<Book> bookList = (List<Book>)req.getServletContext().getAttribute("bookList");
        List<Book> books = new ArrayList<Book>();
        System.out.println(value);
        Book book = new Book();
        book.setBook_name(value);
        book.setType(value);
        book.setId(value);
        String json = "";
        for (int i = 0; i < bookList.size(); i++){
            if(bookList.get(i).getId().equals(book.getId()) || bookList.get(i).getBook_name().equals(book.getBook_name()) || bookList.get(i).getType().equals(book.getType())){
                books.add(bookList.get(i));
            }
        }
        json = JSON.toJSONString(books);
        System.out.println(json);
        resp.getWriter().println(json);
    }
}
