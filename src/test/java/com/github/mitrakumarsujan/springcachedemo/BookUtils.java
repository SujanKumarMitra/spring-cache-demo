package com.github.mitrakumarsujan.springcachedemo;

import com.github.mitrakumarsujan.springcachedemo.model.Book;

import java.util.Objects;


public class BookUtils {

    public static boolean isBookEqual(Book book1, Book book2) {
        return Objects.equals(book1.getISBN(), book2.getISBN()) &&
                Objects.equals(book1.getAuthors(), book2.getAuthors()) &&
                Objects.equals(book1.getTitle(), book2.getTitle()) &&
                Objects.equals(book1.getPublisher(), book2.getPublisher()) &&
                Objects.equals(book1.getCategory(), book2.getCategory());
    }
}