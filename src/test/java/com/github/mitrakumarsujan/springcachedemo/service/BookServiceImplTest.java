package com.github.mitrakumarsujan.springcachedemo.service;

import com.github.mitrakumarsujan.springcachedemo.dao.BookDao;
import com.github.mitrakumarsujan.springcachedemo.exception.BookAlreadyExistsException;
import com.github.mitrakumarsujan.springcachedemo.exception.BookNotFoundException;
import com.github.mitrakumarsujan.springcachedemo.model.Book;
import com.github.mitrakumarsujan.springcachedemo.model.BookImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class BookServiceImplTest extends BookServiceTest {
    @Mock
    private BookDao bookDao;

    @InjectMocks
    private BookServiceImpl bookServiceImpl;

    @Override
    @BeforeEach
    void setUp() {
        openMocks(this);
        super.serviceUnderTest = bookServiceImpl;
        insertedBook = getRandomBookBuilder()
                .withISBN("validISBN")
                .build();
        super.setUp();
    }

    @Override
    @Test
    protected void testAddValidBook() {
        when(bookDao.saveBook(
                argThat(
                        argument -> argument
                                .getISBN()
                                .equals(super.validISBN))))
                .thenReturn(true)
                .thenThrow(BookAlreadyExistsException.class);
        BookImpl book = new BookImpl();
        book.setISBN(super.validISBN);
        super.testAddValidBook();
    }

    @Override
    @Test
    protected void testAddInvalidBook() {
        doThrow(BookAlreadyExistsException.class)
                .when(bookDao)
                .saveBook(argThat(argument -> argument.getISBN().equals(super.invalidISBN)));
        super.testAddInvalidBook();
    }

    @Override
    @Test
    protected void testGetValidBook() {
        String isbn = insertedBook.getISBN();
        Book book = getRandomBookBuilder()
                .withISBN(isbn)
                .build();

        doReturn(Optional.of(book))
                .when(bookDao)
                .getBookByISBN(isbn);
        super.testGetValidBook();
    }

    @Override
    @Test
    protected void testGetInvalidBook() {
        doReturn(Optional.empty())
                .when(bookDao).getBookByISBN(super.invalidISBN);
        super.testGetInvalidBook();
    }

    @Override
    @Test
    protected void testUpdateValidBook() {
        doReturn(insertedBook)
                .when(bookDao)
                .updateBook(argThat(arg -> arg.getISBN().equals(insertedBook.getISBN())));

        super.testUpdateValidBook();
    }

    @Override
    @Test
    protected void testUpdateInvalidBook() {
        doThrow(BookNotFoundException.class)
                .when(bookDao)
                .deleteBookByISBN(eq(invalidISBN));
        super.testUpdateInvalidBook();
    }

    @Override
    @Test
    protected void testDeleteValidBook() {
        doReturn(insertedBook)
                .when(bookDao)
                .deleteBookByISBN(eq(insertedBook.getISBN()));
        super.testDeleteValidBook();
    }

    @Override
    @Test
    protected void testDeleteInvalidBook() {
        doReturn(null)
                .when(bookDao)
                .deleteBookByISBN(eq(invalidISBN));
        super.testDeleteInvalidBook();
    }
}