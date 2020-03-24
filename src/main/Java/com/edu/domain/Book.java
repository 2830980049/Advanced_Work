package com.edu.domain;

import java.io.File;
import java.io.IOException;

public class Book {
    private String id;
    private String book_name;
    private String type;
    private double price;
    private String img;

    public Book(String id, String book_name, String type, double price, String img) {
        this.id = id;
        this.book_name = book_name;
        this.type = type;
        this.price = price;
        this.img = img;
    }

    public Book() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", book_name='" + book_name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", img='" + img + '\'' +
                '}';
    }
}
