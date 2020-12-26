package com.github.mitrakumarsujan.springcachedemo.dto;


import com.github.mitrakumarsujan.springcachedemo.model.Book;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CreateBookResponseDto {
    private String message;
    private Book createdBook;

    public CreateBookResponseDto() {
    }

    public CreateBookResponseDto(String message, Book createdBook) {
        this.message = message;
        this.createdBook = createdBook;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Book getCreatedBook() {
        return createdBook;
    }

    public void setCreatedBook(Book createdBook) {
        this.createdBook = createdBook;
    }
}
