package com.github.mitrakumarsujan.springcachedemo.dto;


import com.github.mitrakumarsujan.springcachedemo.model.Book;

public class UpdateBookResponseDto {

    private String message;
    private Book bookBeforeUpdate;

    public UpdateBookResponseDto() {
    }

    public UpdateBookResponseDto(String message, Book bookBeforeUpdate) {
        this.message = message;
        this.bookBeforeUpdate = bookBeforeUpdate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Book getBookBeforeUpdate() {
        return bookBeforeUpdate;
    }

    public void setBookBeforeUpdate(Book bookBeforeUpdate) {
        this.bookBeforeUpdate = bookBeforeUpdate;
    }
}
