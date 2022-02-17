package com.amberlight.test.web.apps.kafka.books.core.service.app.web;

import com.amberlight.test.web.apps.kafka.books.core.domain.dto.book.BookDto;
import com.amberlight.test.web.apps.kafka.books.core.domain.entity.book.Book;
import com.amberlight.test.web.apps.kafka.books.core.service.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
public class BookResource {

    @Autowired
    private BookService bookService;

    @Autowired
    private ConversionService conversionService;

    @PostMapping("/create")
    public ResponseEntity<Book> createBook() {
        Book book = bookService.createBook();
        return ResponseEntity.ok(book);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<BookDto> findBook(@PathVariable Long id) {
        Book book = bookService.findBook(id);
        BookDto bookDto = conversionService.convert(book, BookDto.class);
//        System.out.println(Hibernate.isInitialized(author.getBooks()) + ": Hibernate.isInitialized(author.getBooks() CONTROLLER");
//        System.out.println("CONTROLLER: author.getBooks(): " + author.getBooks());
//        return ResponseEntity.ok(author);
        return ResponseEntity.ok(bookDto);
    }

}
