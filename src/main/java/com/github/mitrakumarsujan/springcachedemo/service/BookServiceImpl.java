package com.github.mitrakumarsujan.springcachedemo.service;

import com.github.mitrakumarsujan.springcachedemo.dao.BookDao;
import com.github.mitrakumarsujan.springcachedemo.exception.BookAlreadyExistsException;
import com.github.mitrakumarsujan.springcachedemo.exception.BookNotFoundException;
import com.github.mitrakumarsujan.springcachedemo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.text.MessageFormat.format;

@Service
public class BookServiceImpl implements BookService {

    protected BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        Objects.requireNonNull(bookDao, () -> "bookDao can't be null");
        this.bookDao = bookDao;
    }

    public void setBookDao(BookDao bookDao) {
        Objects.requireNonNull(bookDao, () -> "bookDao can't be null");
        this.bookDao = bookDao;
    }

    @Override
    public Book addBook(Book book) throws BookAlreadyExistsException {
        boolean result = bookDao.saveBook(book);
        if (!result)
            throw new BookAlreadyExistsException(
                    format("book already exists with isbn [{0}]"
                            , book.getIsbn())
            );
        return book;
    }

    @Override
    public Book getBook(String isbn) throws BookNotFoundException {
        Optional<Book> mightHaveBook = bookDao.getBookByIsbn(isbn);
        return mightHaveBook.orElseThrow(() -> getBookNotFoundException(isbn));
    }

    @Override
    public List<Book> getBooks() {
        return bookDao.getSavedBooks();
    }

    @Override
    public Book updateBook(Book book) throws BookNotFoundException {
        Book previousBook = bookDao.updateBook(book);
        if (previousBook == null)
            throw getBookNotFoundException(book.getIsbn());
        return previousBook;
    }

    @Override
    public Book deleteBook(String isbn) throws BookNotFoundException {
        Book deletedBook = bookDao.deleteBookByIsbn(isbn);
        if (deletedBook == null)
            throw getBookNotFoundException(isbn);
        return deletedBook;
    }

    private BookNotFoundException getBookNotFoundException(String ISBN) {
        return new BookNotFoundException(
                format("no book found with isbn [{0}]", ISBN));
    }
}
