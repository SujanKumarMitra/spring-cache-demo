package com.github.mitrakumarsujan.springcachedemo.dto;

import com.github.mitrakumarsujan.springcachedemo.model.Book;

public class DeleteBookResponseDto {

    private String message;
    private Book deletedBook;

    public DeleteBookResponseDto() {
    }

    public DeleteBookResponseDto(String message, Book deletedBook) {
        this.message = message;
        this.deletedBook = deletedBook;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Book getDeletedBook() {
        return deletedBook;
    }

    public void setDeletedBook(Book deletedBook) {
        this.deletedBook = deletedBook;
    }
}
