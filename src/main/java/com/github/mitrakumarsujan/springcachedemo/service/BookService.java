package com.github.mitrakumarsujan.springcachedemo.service;


import com.github.mitrakumarsujan.springcachedemo.exception.BookAlreadyExistsException;
import com.github.mitrakumarsujan.springcachedemo.exception.BookNotFoundException;
import com.github.mitrakumarsujan.springcachedemo.model.Book;

public interface BookService {

    /**
     * @param book the book to save
     * @return the book passed
     * @throws BookAlreadyExistsException if a book already exists with same {@link Book#getISBN()}
     */
    Book addBook(Book book) throws BookAlreadyExistsException;

    /**
     * @param ISBN the isbn of the book to find
     * @return the book
     * @throws BookNotFoundException if no book found with supplied ISBN
     */
    Book getBook(String ISBN) throws BookNotFoundException;

    /**
     * Searches the book to update using {@link Book#getISBN()} of param passed
     *
     * @param book the book to update
     * @return book before update
     * @throws BookNotFoundException if no book is found with {@link Book#getISBN()}
     */
    Book updateBook(Book book) throws BookNotFoundException;

    /**
     * @param ISBN the isbn of book to find
     * @return the book deleted
     * @throws BookNotFoundException if no book found with isbn
     */
    Book deleteBook(String ISBN) throws BookNotFoundException;
}
