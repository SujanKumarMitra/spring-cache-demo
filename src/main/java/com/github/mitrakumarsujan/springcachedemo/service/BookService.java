package com.github.mitrakumarsujan.springcachedemo.service;


import com.github.mitrakumarsujan.springcachedemo.exception.BookAlreadyExistsException;
import com.github.mitrakumarsujan.springcachedemo.exception.BookNotFoundException;
import com.github.mitrakumarsujan.springcachedemo.model.Book;

import java.util.List;

public interface BookService {

    /**
     * @param book the book to save
     * @return the book passed
     * @throws BookAlreadyExistsException if a book already exists with same {@link Book#getIsbn()}
     */
    Book addBook(Book book) throws BookAlreadyExistsException;

    /**
     * @param isbn the isbn of the book to find
     * @return the book
     * @throws BookNotFoundException if no book found with supplied isbn
     */
    Book getBook(String isbn) throws BookNotFoundException;

    /**
     * Return all books saved, or empty list if no book saved
     * @return all saved books, or empty list
     */
    List<Book> getBooks();

    /**
     * Searches the book to update using {@link Book#getIsbn()} of param passed
     *
     * @param book the book to update
     * @return book before update
     * @throws BookNotFoundException if no book is found with {@link Book#getIsbn()}
     */
    Book updateBook(Book book) throws BookNotFoundException;

    /**
     * @param isbn the isbn of book to find
     * @return the book deleted
     * @throws BookNotFoundException if no book found with isbn
     */
    Book deleteBook(String isbn) throws BookNotFoundException;
}
