package com.github.mitrakumarsujan.springcachedemo.service;


import com.github.mitrakumarsujan.springcachedemo.exception.BookAlreadyExistsException;
import com.github.mitrakumarsujan.springcachedemo.exception.BookNotFoundException;
import com.github.mitrakumarsujan.springcachedemo.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CacheEnabledBookService implements BookService {

    private static final String CACHE_NAME = "books";
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheEnabledBookService.class);
    private BookService proxyBookService;

    @Autowired
    public CacheEnabledBookService(@Qualifier("bookServiceImpl") BookService proxyBookService) {
        this.proxyBookService = proxyBookService;
    }

    public void setProxyBookService(BookService proxyBookService) {
        this.proxyBookService = proxyBookService;
    }

    @Override
    @CachePut(cacheNames = CACHE_NAME, key = "#book.isbn")
    public Book addBook(Book book) throws BookAlreadyExistsException {
        return proxyBookService.addBook(book);
    }

    @Override
    @Cacheable(cacheNames = CACHE_NAME, key = "#isbn")
    public Book getBook(String isbn) throws BookNotFoundException {
        return proxyBookService.getBook(isbn);
    }

    @Override
    public List<Book> getBooks() {
        return proxyBookService.getBooks();
    }

    @Override
    @CacheEvict(cacheNames = CACHE_NAME, key = "#book.isbn")
    public Book updateBook(Book book) throws BookNotFoundException {
        return proxyBookService.updateBook(book);
    }

    @Override
    @CacheEvict(cacheNames = CACHE_NAME, key = "#isbn")
    public Book deleteBook(String isbn) throws BookNotFoundException {
        return proxyBookService.deleteBook(isbn);
    }

    @CacheEvict(cacheNames = CACHE_NAME, allEntries = true)
    public void invalidateCacheEntries() {
        LOGGER.info("clearing all cache entries");
    }
}
