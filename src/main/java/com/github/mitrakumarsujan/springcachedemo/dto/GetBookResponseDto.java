package com.github.mitrakumarsujan.springcachedemo.dto;

import com.github.mitrakumarsujan.springcachedemo.model.Book;

public class GetBookResponseDto {
    private String message;
    private Book book;

    public GetBookResponseDto() {
    }

    public GetBookResponseDto(String message, Book book) {
        this.message = message;
        this.book = book;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
