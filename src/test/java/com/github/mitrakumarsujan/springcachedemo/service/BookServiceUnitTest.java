package com.github.mitrakumarsujan.springcachedemo.service;

import com.github.mitrakumarsujan.springcachedemo.exception.BookAlreadyExistsException;
import com.github.mitrakumarsujan.springcachedemo.exception.BookNotFoundException;
import com.github.mitrakumarsujan.springcachedemo.model.Book;
import com.github.mitrakumarsujan.springcachedemo.model.BookBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.mitrakumarsujan.springcachedemo.BookUtils.isBookEqual;
import static org.junit.jupiter.api.Assertions.*;

abstract class BookServiceUnitTest {

    protected BookService serviceUnderTest;

    /**
     * a valid isbn which can in inserted
     */
    protected String validISBN = "valid";
    /**
     * isbn which is either present or not present
     */
    protected String invalidISBN = "invalid";

    /**
     * book already present in db
     */
    protected Book insertedBook;

    @BeforeEach
    void setUp() {
        assertNotNull(serviceUnderTest);
        assertNotNull(insertedBook);
    }

    @Test
    protected void testAddValidBook() {
        Book book = getRandomBookBuilder()
                .withIsbn(validISBN)
                .build();

        Book savedBook = serviceUnderTest.addBook(book);
        assertNotNull(savedBook);
        assertEquals(book.getIsbn(), savedBook.getIsbn());

        assertThrows(BookAlreadyExistsException.class, () ->
                serviceUnderTest.addBook(savedBook));
    }

    @Test
    protected void testAddInvalidBook() {
        Book invalidBook = getRandomBookBuilder()
                .withIsbn(invalidISBN)
                .build();
        assertThrows(BookAlreadyExistsException.class, () -> serviceUnderTest.addBook(invalidBook));
    }

    @Test
    protected void testGetValidBook() {
        String isbn = insertedBook.getIsbn();
        Book book = serviceUnderTest.getBook(isbn);
        assertNotNull(book);
        assertEquals(isbn, book.getIsbn());
    }

    @Test
    protected void testGetInvalidBook() {
        assertThrows(BookNotFoundException.class, () -> serviceUnderTest.getBook(invalidISBN));
    }

    @Test
    protected void testUpdateValidBook() {
        Book bookToUpdate = getRandomBookBuilder()
                .withIsbn(insertedBook.getIsbn())
                .withTitle("Updated Title")
                .withAuthor("Updated Author")
                .withCategory("Updated Category")
                .withPublisher("Updated Publisher")
                .build();
        Book bookBeforeUpdate = assertDoesNotThrow(() -> serviceUnderTest.updateBook(bookToUpdate));
        assertTrue(isBookEqual(insertedBook, bookBeforeUpdate));
    }

    @Test
    protected void testUpdateInvalidBook() {
        Book bookToUpdate = getRandomBookBuilder()
                .withIsbn(invalidISBN)
                .build();
        assertThrows(BookNotFoundException.class, () ->
                serviceUnderTest.updateBook(bookToUpdate));
    }

    @Test
    protected void testDeleteValidBook() {
        String isbn = insertedBook.getIsbn();
        Book deletedBook = assertDoesNotThrow(() -> serviceUnderTest.deleteBook(isbn));
        assertEquals(isbn, deletedBook.getIsbn());

        assertTrue(isBookEqual(insertedBook,deletedBook));
    }

    @Test
    protected void testDeleteInvalidBook() {
        assertThrows(BookNotFoundException.class, () ->
                serviceUnderTest.deleteBook(invalidISBN));
    }

    /**
     * @return builder with random values set
     */
    protected BookBuilder getRandomBookBuilder() {
        return BookBuilder.builder()
                .withIsbn("ISBN")
                .withAuthor("Author")
                .withTitle("Title")
                .withPublisher("Publisher")
                .withCategory("Category");
    }
}