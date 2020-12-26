package com.github.mitrakumarsujan.springcachedemo.dao;

import com.github.mitrakumarsujan.springcachedemo.model.Book;

import java.util.Optional;

public interface BookDao {

    /**
     * @param book the book to save
     * @return {@code true} if saved successfully else false
     */
    boolean saveBook(Book book);

    /**
     * @param ISBN the ISBN of book to search
     * @return Optional either containing the book or {@link Optional#empty()}
     */
    Optional<Book> getBookByISBN(String ISBN);

    /**
     * @param book the book to update
     * @return previously saved book, or {@code null} if no book found
     */
    Book updateBook(Book book);

    /**
     * @param ISBN the isbn of book to delete
     * @return the book deleted, else {@code null}
     */
    Book deleteBookByISBN(String ISBN);
}
