package com.github.mitrakumarsujan.springcachedemo.dao;

import com.github.mitrakumarsujan.springcachedemo.model.Book;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Collections.synchronizedMap;

@Service
public class InMemoryBookDao implements BookDao {

    Map<String, Book> bookMap;

    public InMemoryBookDao() {
        this(synchronizedMap(new LinkedHashMap<>()));
    }

    public InMemoryBookDao(Map<String, Book> bookMap) {
        this.bookMap = bookMap;
    }

    @Override
    public boolean saveBook(Book book) {
        Book returnedBook = bookMap.putIfAbsent(book.getIsbn(), book);
        return returnedBook == null;
    }

    @Override
    public Optional<Book> getBookByIsbn(String isbn) {
        Book book = bookMap.get(isbn);
        return book == null ? Optional.empty() : Optional.of(book);
    }

    @Override
    public List<Book> getSavedBooks() {
        return new ArrayList<>(bookMap.values());
    }

    @Override
    public Book updateBook(Book book) {
        return bookMap.replace(book.getIsbn(), book);
    }

    @Override
    public Book deleteBookByIsbn(String isbn) {
        return bookMap.remove(isbn);
    }
}
