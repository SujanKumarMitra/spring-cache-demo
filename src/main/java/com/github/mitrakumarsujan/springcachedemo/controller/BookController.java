package com.github.mitrakumarsujan.springcachedemo.controller;


import com.github.mitrakumarsujan.springcachedemo.dto.*;
import com.github.mitrakumarsujan.springcachedemo.model.Book;
import com.github.mitrakumarsujan.springcachedemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(@Qualifier("cacheEnabledBookService") BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<GetBooksResponseDto> getBooks() {
        List<Book> books = bookService.getBooks();
        return ResponseEntity.ok(new GetBooksResponseDto("OK", books));
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<GetBookResponseDto> getBook(@PathVariable("isbn") String isbn) {
        Book book = bookService.getBook(isbn);
        GetBookResponseDto response = new GetBookResponseDto("OK", book);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreateBookResponseDto> updateBook(
            @RequestBody @Valid CreateBookRequestDto book) {
        Book createdBook = bookService.addBook(book);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CreateBookResponseDto("Book created successfully.", createdBook));
    }

    @PutMapping
    public ResponseEntity<UpdateBookResponseDto> updateBook(
            @RequestBody @Valid UpdateBookRequestDto book) {
        Book previous = bookService.updateBook(book);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new UpdateBookResponseDto("Book updated successfully.", previous));
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<DeleteBookResponseDto> deleteBook(@PathVariable("isbn") String isbn) {
        Book deletedBook = bookService.deleteBook(isbn);
        return ResponseEntity.ok(new DeleteBookResponseDto("Book deleted successfully", deletedBook));
    }
}
