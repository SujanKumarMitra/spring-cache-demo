package com.github.mitrakumarsujan.springcachedemo.service;

import com.github.mitrakumarsujan.springcachedemo.dao.BookDao;
import com.github.mitrakumarsujan.springcachedemo.exception.BookAlreadyExistsException;
import com.github.mitrakumarsujan.springcachedemo.exception.BookNotFoundException;
import com.github.mitrakumarsujan.springcachedemo.model.Book;
import com.github.mitrakumarsujan.springcachedemo.model.BookImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class BookServiceImplUnitTest extends BookServiceUnitTest {
    @MockBean
    private BookDao bookDao;

    @Autowired
    private BookServiceImpl bookServiceImpl;

    @Override
    @BeforeEach
    void setUp() {
        super.serviceUnderTest = bookServiceImpl;
        insertedBook = getRandomBookBuilder()
                .withIsbn("validISBN")
                .build();
        super.setUp();
    }

    @Override
    @Test
    protected void testAddValidBook() {
        when(bookDao.saveBook(
                argThat(
                        argument -> argument
                                .getIsbn()
                                .equals(super.validIsbn))))
                .thenReturn(true)
                .thenThrow(BookAlreadyExistsException.class);
        BookImpl book = new BookImpl();
        book.setISBN(super.validIsbn);
        super.testAddValidBook();
    }

    @Override
    @Test
    protected void testAddInvalidBook() {
        doThrow(BookAlreadyExistsException.class)
                .when(bookDao)
                .saveBook(argThat(argument -> argument.getIsbn().equals(super.invalidIsbn)));
        super.testAddInvalidBook();
    }

    @Override
    @Test
    protected void testGetValidBook() {
        String isbn = insertedBook.getIsbn();
        Book book = getRandomBookBuilder()
                .withIsbn(isbn)
                .build();

        doReturn(Optional.of(book))
                .when(bookDao)
                .getBookByIsbn(isbn);
        super.testGetValidBook();
    }

    @Override
    @Test
    protected void testGetInvalidBook() {
        doReturn(Optional.empty())
                .when(bookDao).getBookByIsbn(super.invalidIsbn);
        super.testGetInvalidBook();
    }

    @Override
    @Test
    protected void testUpdateValidBook() {
        doReturn(insertedBook)
                .when(bookDao)
                .updateBook(argThat(arg -> arg.getIsbn().equals(insertedBook.getIsbn())));

        super.testUpdateValidBook();
    }

    @Override
    @Test
    protected void testUpdateInvalidBook() {
        doThrow(BookNotFoundException.class)
                .when(bookDao)
                .deleteBookByIsbn(eq(invalidIsbn));
        super.testUpdateInvalidBook();
    }

    @Override
    @Test
    protected void testDeleteValidBook() {
        doReturn(insertedBook)
                .when(bookDao)
                .deleteBookByIsbn(eq(insertedBook.getIsbn()));
        super.testDeleteValidBook();
    }

    @Override
    @Test
    protected void testDeleteInvalidBook() {
        doReturn(null)
                .when(bookDao)
                .deleteBookByIsbn(eq(invalidIsbn));
        super.testDeleteInvalidBook();
    }
}
