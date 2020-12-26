package com.github.mitrakumarsujan.springcachedemo.service;


import com.github.mitrakumarsujan.springcachedemo.exception.BookAlreadyExistsException;
import com.github.mitrakumarsujan.springcachedemo.exception.BookNotFoundException;
import com.github.mitrakumarsujan.springcachedemo.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
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
    private Cache cache;

    @Autowired
    public CacheEnabledBookService(
            @Qualifier("bookServiceImpl") BookService proxyBookService,
            CacheManager cacheManager) {
        this.proxyBookService = proxyBookService;
        this.cache = cacheManager.getCache(CACHE_NAME);
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

    public void invalidateCacheEntries() {
        LOGGER.info("attempting to clear all cache entries");
        boolean allInvalidated = cache.invalidate();
        if(allInvalidated) {
            LOGGER.info("all cache entries cleared");
        } else {
            LOGGER.warn("all cache entries not cleared");
        }

    }
}
