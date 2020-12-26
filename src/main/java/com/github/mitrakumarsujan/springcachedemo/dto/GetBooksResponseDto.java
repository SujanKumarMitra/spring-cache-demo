package com.github.mitrakumarsujan.springcachedemo.dto;

import com.github.mitrakumarsujan.springcachedemo.model.Book;

import java.util.List;

public class GetBooksResponseDto {
    private int size;
    private String message;
    private List<Book> books;

    public GetBooksResponseDto() {
    }

    public GetBooksResponseDto(String message, List<Book> books) {
        this.size = books.size();
        this.message = message;
        this.books = books;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
