package com.amberlight.test.web.apps.kafka.books.core.service.app.web;

import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.command.CreateBookCommand;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.document.CreateBookDocument;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.model.book.BookDto;
import com.amberlight.test.web.apps.kafka.books.core.domain.entity.book.Book;
import com.amberlight.test.web.apps.kafka.books.core.service.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/book")
public class BookResource {

    @Autowired
    private BookService bookService;

    @Autowired
    private ConversionService conversionService;

//    @PostMapping("/create")
//    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
//        Book book = bookService.createBook(bookDto);
//        Book createdBook = bookService.findBook(book.getId());
//        BookDto createdBookDto = conversionService.convert(createdBook, BookDto.class);
//        return ResponseEntity.ok(createdBookDto);
//    }

    @PostMapping("/create")
    public ResponseEntity<CreateBookDocument> createBook(@RequestBody CreateBookCommand command) {
        Book book = bookService.createBook(bookDto);
        Book createdBook = bookService.findBook(book.getId());
        BookDto createdBookDto = conversionService.convert(createdBook, BookDto.class);
        return ResponseEntity.ok(createdBookDto);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<BookDto> findBook(@PathVariable Long id) {
        Book book = bookService.findBook(id);
        BookDto bookDto = conversionService.convert(book, BookDto.class);
        return ResponseEntity.ok(bookDto);
    }

}
