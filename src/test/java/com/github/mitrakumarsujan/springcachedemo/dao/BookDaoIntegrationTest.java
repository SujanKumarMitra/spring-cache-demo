package com.github.mitrakumarsujan.springcachedemo.dao;

import com.github.mitrakumarsujan.springcachedemo.utils.BookUtils;
import com.github.mitrakumarsujan.springcachedemo.model.Book;
import com.github.mitrakumarsujan.springcachedemo.model.BookBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;

abstract class BookDaoIntegrationTest {

    protected static final String ISBN_NOT_PRESENT = "random";
    protected BookDao daoUnderTest;
    protected List<Book> insertedBooks;

    @BeforeEach
    protected void setUp() {
        this.daoUnderTest = requireNonNull(daoUnderTest);
        this.insertedBooks = getBooksToInsert();
        boolean allSaved = this.insertedBooks
                .stream()
                .map(daoUnderTest::saveBook)
                .allMatch(Boolean::valueOf);

        assertTrue(allSaved);
    }

    @AfterEach
    protected void tearDown() {
        boolean allDeleted = insertedBooks
                .stream()
                .map(Book::getIsbn)
                .map(daoUnderTest::deleteBookByIsbn)
                .allMatch(Objects::nonNull);

        assertTrue(allDeleted);

    }

    protected List<Book> getBooksToInsert() {
        List<Book> dummyBooks = new LinkedList<>();
        String titlePrefix = "Book";
        String authorPrefix = "Author";
        String publisherPrefix = "Publisher";
        String categoryPrefix = "Category";

        for (int i = 0; i < 3; i++) {
            String randomId = UUID.randomUUID().toString();
            Book book = BookBuilder
                    .builder()
                    .withIsbn(randomId)
                    .withTitle(titlePrefix.concat(randomId))
                    .withAuthor(authorPrefix.concat(randomId))
                    .withPublisher(publisherPrefix.concat(randomId))
                    .withCategory(categoryPrefix.concat(randomId))
                    .build();
            dummyBooks.add(book);
        }
        return dummyBooks;
    }

    @Test
    protected void testSaveBook() {
        Book book = BookBuilder
                .builder()
                .withIsbn(ISBN_NOT_PRESENT)
                .withTitle("Title")
                .withPublisher("Publisher")
                .withAuthor("Author")
                .withCategory("Category")
                .build();

        assertTrue(daoUnderTest.saveBook(book));
        this.insertedBooks.add(book);

        Optional<Book> shouldBeSavedBook = daoUnderTest.getBookByIsbn(ISBN_NOT_PRESENT);

        assertTrue(shouldBeSavedBook.isPresent());
        Book savedBook = assertDoesNotThrow(() -> shouldBeSavedBook.get());

        assertEquals(book.getIsbn(), savedBook.getIsbn());
    }

    @Test
    protected void testGetBookByISBN() {
        Book book = insertedBooks.get(0);
        Optional<Book> mightHaveBook = daoUnderTest.getBookByIsbn(book.getIsbn());

        assertTrue(mightHaveBook.isPresent());
        Book savedBook = mightHaveBook.get();

        assertTrue(BookUtils.isBookEqual(book, savedBook));

        Optional<Book> shouldNotContainBook = daoUnderTest.getBookByIsbn(ISBN_NOT_PRESENT);

        assertFalse(shouldNotContainBook.isPresent());
    }

    @Test
    protected void testUpdateBook() {
        Book book = insertedBooks.get(0);
        BookBuilder bookBuilder = BookBuilder
                .builder()
                .withIsbn(book.getIsbn())
                .withTitle("Updated Title")
                .withAuthor("Updated Author")
                .withPublisher("Updated Publisher")
                .withCategory("Updated Category");

        Book bookToUpdate = bookBuilder.build();
        Book previousBook = daoUnderTest.updateBook(bookToUpdate);
        assertNotNull(previousBook);

        Optional<Book> shouldHaveUpdatedBook = daoUnderTest.getBookByIsbn(bookToUpdate.getIsbn());
        assertTrue(shouldHaveUpdatedBook.isPresent());

        Book updatedBook = assertDoesNotThrow(shouldHaveUpdatedBook::get);
        assertTrue(BookUtils.isBookEqual(bookToUpdate, updatedBook));

        assertNull(daoUnderTest.updateBook(bookBuilder
                .withIsbn("invalid")
                .build()));
    }

    @Test
    protected void testDeleteBook() {
        Book bookToDelete = insertedBooks.get(0);

        Book deletedBook = daoUnderTest.deleteBookByIsbn(bookToDelete.getIsbn());
        assertNotNull(deletedBook);

        assertTrue(BookUtils.isBookEqual(bookToDelete, deletedBook));

        insertedBooks.remove(0);

        assertNull(daoUnderTest.deleteBookByIsbn(ISBN_NOT_PRESENT));
    }

}