package com.github.mitrakumarsujan.springcachedemo.dao;

import com.github.mitrakumarsujan.springcachedemo.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    /**
     * @param book the book to save
     * @return {@code true} if saved successfully else false
     */
    boolean saveBook(Book book);

    /**
     * @param isbn the isbn of book to search
     * @return Optional either containing the book or {@link Optional#empty()}
     */
    Optional<Book> getBookByIsbn(String isbn);


    /**
     * Returns all saved books or empty list if no book saved
     *
     * @return saved books
     */
    List<Book> getSavedBooks();

    /**
     * @param book the book to update
     * @return previously saved book, or {@code null} if no book found
     */
    Book updateBook(Book book);

    /**
     * @param isbn the isbn of book to delete
     * @return the book deleted, else {@code null}
     */
    Book deleteBookByIsbn(String isbn);
}
