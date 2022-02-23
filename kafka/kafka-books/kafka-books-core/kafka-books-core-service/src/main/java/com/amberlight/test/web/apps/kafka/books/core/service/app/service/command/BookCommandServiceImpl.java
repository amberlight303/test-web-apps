package com.amberlight.test.web.apps.kafka.books.core.service.app.service.command;

import com.amberlight.test.web.apps.kafka.books.core.api.struct.api.command.CreateBookCommand;
import com.amberlight.test.web.apps.kafka.books.core.api.struct.api.document.CreateBookDocument;
import com.amberlight.test.web.apps.kafka.books.core.api.struct.dto.book.BookDto;
import com.amberlight.test.web.apps.kafka.books.core.domain.entity.book.Book;
import com.amberlight.test.web.apps.kafka.books.core.service.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service("bookCommandService")
public class BookCommandServiceImpl implements BookCommandService {

    @Autowired
    private BookService bookService;

    @Autowired
    private ConversionService conversionService;

    /**
     * {@inheritDoc}
     */
    @Override
    public CreateBookDocument process(CreateBookCommand command) {
        Book createdBook = bookService.createBook(command.getBook());
        createdBook = bookService.findBook(createdBook.getId());
        BookDto createdBookDto = conversionService.convert(createdBook, BookDto.class);
        return CreateBookDocument.builder().book(createdBookDto).build();
    }

}
