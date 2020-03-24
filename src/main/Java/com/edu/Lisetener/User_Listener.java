package com.edu.Lisetener;

import com.edu.domain.Book;
import com.edu.domain.Book_type;
import com.edu.domain.User;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import java.util.ArrayList;
import java.util.List;

public class User_Listener implements ServletContextListener, ServletRequestListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        List<User> list = new ArrayList<User>();
        list.add(new User("susu","123"));
        list.add(new User("aisa","1234"));
        list.add(new User("vn","666"));

        List<Book_type> bookTypes = new ArrayList<Book_type>();
        bookTypes.add(new Book_type("ca0001","计算机类"));
        bookTypes.add(new Book_type("ca0002","土木工程类"));
        bookTypes.add(new Book_type("ca0003","人文类"));

        List<Book> books = new ArrayList<Book>();
        books.add(new Book("book0001","Java基础","计算机类",30,"g1.jpg"));
        books.add(new Book("book0002","工程学","土木工程类",50,"g1.jpg"));
        books.add(new Book("book0003","哲学","人文类",20,"g1.jpg"));
        books.add(new Book("book0004","历史学","人文类",20,"g1.jpg"));

        sce.getServletContext().setAttribute("bookList",books);
        sce.getServletContext().setAttribute("list",list);
        sce.getServletContext().setAttribute("bookTypes", bookTypes);
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {

    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }




}
