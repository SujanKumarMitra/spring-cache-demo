package com.github.mitrakumarsujan.springcachedemo.dto;


import com.github.mitrakumarsujan.springcachedemo.model.Book;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class UpdateBookRequestDto implements Book {

    private String isbn;

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotEmpty
    private List<String> authors;

    @NotNull
    @NotBlank
    private String publisher;

    @NotNull
    @NotBlank
    private String category;

    @Override
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    @Override
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
